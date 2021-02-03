package org.sff.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyUtil {
    static class CglibProxy implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("之前...");
            Object value = methodProxy.invokeSuper(obj, objects);
            System.out.println("之后...");
            return value;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new CglibProxy());

        UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        userService.save();
    }
}
