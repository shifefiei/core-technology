package org.sff.config;

import java.io.InputStream;

public class MyResources {

    /**
     * 加载 mybatis 的配置文件
     */
    public static InputStream getResourceAsStream(String resource) {
        InputStream resourceAsStream = MyResources.class.getClassLoader().getResourceAsStream(resource);
        return resourceAsStream;
    }
}
