package cn.trusteye.work;

import java.util.concurrent.TimeUnit;

/**
 * @author
 * @create 2017-12-25
 **/

public class Demo3 {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello world");
        }).start();


    }
}
