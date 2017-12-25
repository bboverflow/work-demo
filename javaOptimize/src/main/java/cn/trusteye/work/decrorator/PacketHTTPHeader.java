package cn.trusteye.work.decrorator;

public class PacketHTTPHeader extends AbstractPacketDecorator {
    public PacketHTTPHeader(IPacket packet) {
        super(packet);
    }

    @Override
    public String handleContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("Cache-Control:no-cache\n");
        sb.append("Date:Mon,31Dec201204:25:57GMT\n");
        sb.append(packet.handleContent());
        return sb.toString();
    }
}
