package cn.trusteye.work.decrorator;

public class PacketHTMLHeader extends AbstractPacketDecorator {
    public PacketHTMLHeader(IPacket packet) {
        super(packet);
    }

    @Override
    public String handleContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<body>");
        sb.append(packet.handleContent());
        sb.append("</body>");
        sb.append("</html");
        return sb.toString();
    }
}
