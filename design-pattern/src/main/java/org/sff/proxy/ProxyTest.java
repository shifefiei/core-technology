package org.sff.proxy;

public class ProxyTest {

    public static void main(String[] args) {

        UserService realUserService = new UserServiceImpl();
        JDKProxyUtil jdkProxyUtil = new JDKProxyUtil(realUserService);

        UserService userServiceProxy  = (UserService) jdkProxyUtil.getProxy(); //调用该方法时不会执行 invoke 方法
        userServiceProxy.save();
    }


}
