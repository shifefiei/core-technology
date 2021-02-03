package org.sff.proxy;

import java.lang.reflect.Proxy;

public class JDKProxyUtil {
    //被代理的真实对象
    private Object realTarget;

    public JDKProxyUtil(Object realTarget) {
        this.realTarget = realTarget;
    }

    //获取代理对象
    public Object getProxy() {
        Object result = Proxy.newProxyInstance(
                realTarget.getClass().getClassLoader(),
                realTarget.getClass().getInterfaces(),
                //当被代理对象的任何方法被执行时，都会执行invoke方法被调用
                (proxy, method, args) -> {
                    if ("save".equals(method.getName())) {
                        System.out.println("记录日志");
                    }
                    //被代理对象的正常业务逻辑方法
                    return method.invoke(realTarget, args);
                });
        return result;
    }
}
