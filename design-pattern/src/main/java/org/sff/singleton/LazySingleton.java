package org.sff.singleton;

/**
 * 懒汉式单例模式
 */
public class LazySingleton {

    private volatile static LazySingleton singleton = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {

        if (null != singleton) {
            synchronized (LazySingleton.class) {
                if (null != singleton) {
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }
}
