package cn.trusteye.work.decrorator;

public abstract class AbstractPacketDecorator implements IPacket{
    IPacket packet;

    public AbstractPacketDecorator(IPacket packet) {
        this.packet = packet;
    }
}
