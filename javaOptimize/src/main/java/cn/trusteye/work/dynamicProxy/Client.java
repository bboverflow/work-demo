package cn.trusteye.work.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 1、延迟加载
 * 2、避免自己写多个方法实现，可以统一流程控制
 */

public class Client {
    public static void main(String[] args) {
        IDbQuery dbQuery = (IDbQuery) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{IDbQuery.class},
                new DbQueryHandler());
        System.out.println(dbQuery.update());
    }
}
