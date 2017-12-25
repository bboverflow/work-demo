package cn.trusteye.work.decrorator;

public class PacketBody implements IPacket {
    @Override
    public String handleContent() {
        return "Content of packet";
    }
}
