package cn.trusteye.work;

import org.xbill.DNS.*;

import java.io.*;
import java.net.InetAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rayman
 */

public class QueryDNS {

    private static Pattern IPV4_PATTERN = Pattern
            .compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
    private static Pattern DOMAIN_PATTERN = Pattern.compile("[a-z0-9]{12,14}\\.cloudfront\\.net");

    public static void main(String[] args) throws Exception {
        System.setProperty("sun.net.spi.nameservice.provider.1", "dns,dnsjava");
        java.security.Security.setProperty("networkaddress.cache.ttl", "0");

        TreeSet<String> ipaddrs = new TreeSet<String>(new IPComparator());
        TreeSet<String> tuncIpaddrs = new TreeSet<String>(new SubnetComparator());

        ArrayList<String> dnsServers = getDnsServers("dnsserver-all.txt");
        ArrayList<String> domains = getDnsEntry("dns_mail.txt");

        // 循环查询dns
        InetAddress[] addresses = null;
        for (String DNSServer : dnsServers) {
            setSystemDNS(DNSServer);
            getConnNetwork(ipaddrs, tuncIpaddrs, domains);
        }

        Iterator<String> itTuncIPaddrs = tuncIpaddrs.iterator();
        while (itTuncIPaddrs.hasNext()) {
            System.out.println(itTuncIPaddrs.next());
        }
    }

    /**
     * 设置DSN服务器
     *
     * @param server
     */
    private static void setSystemDNS(String server) {
        System.setProperty("sun.net.spi.nameservice.nameservers", server);
        System.out.println("-------------------------");

        Properties p = System.getProperties();
        for (Enumeration e = p.propertyNames(); e.hasMoreElements(); ) {
            String key = (String) e.nextElement();
            System.out.println(key + ":" + p.getProperty(key));
        }
        System.out.println("-------------------------");
    }

    /**
     * 查询所有域名对应的子网地址
     *
     * @param ipaddrs
     * @param tuncIpaddrs
     * @param domains
     */
    private static void getConnNetwork(TreeSet<String> ipaddrs,
                                       TreeSet<String> tuncIpaddrs, ArrayList<String> domains) {
        for (String entry : domains) {
            try {
                InetAddress addr = Address.getByName(entry);
                String tunceIpaddr = getSubnet(addr.getHostAddress(), 2);
                tuncIpaddrs.add(tunceIpaddr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得IP地址对应的子网
     *
     * @param hostAddress
     * @param i
     * @return
     */
    private static String getSubnet(String hostAddress, int i) {
        String[] ipaddress = hostAddress.split("\\.");
        switch (i) {
            case 1:
                return ipaddress[0] + ".0.0.0/8";
            case 2:
                return ipaddress[0] + "." + ipaddress[1] + ".0.0/16";
            case 3:
                return ipaddress[0] + "." + ipaddress[1] + "." + ipaddress[2]
                        + ".0/24";
            default:
                break;
        }
        return null;
    }


    /**
     * 从配置文件中读取所有要查询的域名
     *
     * @param string
     * @return
     * @throws Exception
     */
    private static ArrayList<String> getDnsEntry(String string)
            throws Exception {
        Matcher matcher = null;
        ArrayList<String> entrys = new ArrayList<String>();

        File file = new File(string);
        //BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        String line = null;
        while ((line = br.readLine()) != null) {
            matcher = DOMAIN_PATTERN.matcher(line);
            while (matcher.find()) {
                String entry = matcher.group(0);
                entrys.add(entry);
            }
        }

        br.close();

        return entrys;
    }

    /**
     * 从配置文件中读取所有DNS服务器
     *
     * @param string
     * @return
     * @throws Exception
     */
    private static ArrayList<String> getDnsServers(String string)
            throws Exception {
        Matcher matcher = null;
        ArrayList<String> dnsServers = new ArrayList<String>();
        File f = new File(string);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));

        String line = null;
        while ((line = br.readLine()) != null) {
            matcher = IPV4_PATTERN.matcher(line);
            while (matcher.find()) {
                String server = matcher.group(0);
                dnsServers.add(server);
            }
        }
        br.close();
        return dnsServers;
    }
}
