package org.sff.singleton;

/**
 * 饿汉式单例
 */
public class EagerSingleton {

    private final static EagerSingleton singleton = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return singleton;
    }
}
