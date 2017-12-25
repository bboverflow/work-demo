package cn.trusteye.work.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DbQueryHandler implements InvocationHandler {
    private IDbQuery dbQuery = null;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在转调具体目标对象前，可以执行一些功能处理

        if(dbQuery == null){
            dbQuery = new DbQuery();
        }
        return method.invoke(dbQuery, args);

        //在转调具体目标对象前，可以执行一些功能处理
    }
}
