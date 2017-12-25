package cn.trusteye.work.decrorator;

public class Client {



    public static void main(String[] args) {
        AbstractPacketDecorator pc = new PacketHTTPHeader(new PacketHTMLHeader(new PacketBody()));
        System.out.println(pc.handleContent());

    }
}
