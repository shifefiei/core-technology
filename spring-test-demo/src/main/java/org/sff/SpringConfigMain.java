package org.sff;

import org.sff.bean.Account;
import org.sff.config.MySpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringConfigMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfig.class);
        Account bean = context.getBean(Account.class);
        System.out.println(bean);
    }
}
