package cn.trusteye.work;

import java.io.Serializable;
import java.util.Comparator;

public class SubnetComparator implements Comparator<String>,Serializable {

    @Override
    public int compare(String subnet1, String subnet2) {
        String ip1 = subnet1.substring(0,subnet1.indexOf("/"));
        String ip2 = subnet1.substring(0,subnet2.indexOf("/"));

        return ip1.compareTo(ip2);
    }
}
