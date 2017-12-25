package cn.trusteye.work.nio;

import java.nio.ByteBuffer;

public class Demo1 {
    public static void main(String[] args) {
        ByteBuffer b = ByteBuffer.allocate(15);
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());

        for (int i = 0; i < 10; i++) {
            b.put((byte) i);
        }
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());

        b.flip();
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());

        for (int i = 0; i < 5; i++) {
            System.out.println(b.get());
        }
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());

        b.flip();
        System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());

        b.clear();
    }
}
