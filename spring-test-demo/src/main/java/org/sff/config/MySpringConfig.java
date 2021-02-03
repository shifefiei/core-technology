package org.sff.config;

import org.sff.bean.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.sff")
public class MySpringConfig {
    @Bean
    public Account getAccount() {
        return new Account();
    }
}

