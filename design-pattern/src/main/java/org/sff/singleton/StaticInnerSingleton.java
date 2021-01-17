package org.sff.singleton;

public class StaticInnerSingleton {
    private StaticInnerSingleton() {
    }

    /**
     * jvm 在类加载的时候是互斥，由此保证了线程安全的
     */
    private static class InnerSingleton {
        private static StaticInnerSingleton s = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return InnerSingleton.s;
    }
}
