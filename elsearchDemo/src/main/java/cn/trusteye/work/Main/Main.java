package cn.trusteye.work.Main;

import cn.trusteye.work.controller.GetAndInsertRunnable;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/29 11:31
 * @Modified By:
 */
public class Main {
    public static void main(String[] args) {

        Thread worker = new Thread(new GetAndInsertRunnable());
        worker.start();
    }
}
