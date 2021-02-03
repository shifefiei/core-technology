package org.sff;

import org.sff.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        User bean = context.getBean("userOne", User.class);
        System.out.println(bean);
    }

}
