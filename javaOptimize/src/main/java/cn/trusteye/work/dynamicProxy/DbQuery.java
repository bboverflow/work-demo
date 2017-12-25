package cn.trusteye.work.dynamicProxy;

import java.util.concurrent.TimeUnit;

public class DbQuery implements IDbQuery {
    @Override
    public String insert() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "insert data";
    }

    @Override
    public String update() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "update data";
    }
}
