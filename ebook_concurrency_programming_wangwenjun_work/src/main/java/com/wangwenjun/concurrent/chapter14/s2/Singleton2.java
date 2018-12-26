package com.wangwenjun.concurrent.chapter14.s2;

/**
 * 懒汉式
 */
public final class Singleton2 {
    //实例变量
    private byte[] data = new byte[1024];

    private static Singleton2 instance = null;

    private Singleton2() {
    }

    /**
     * 实例会被创建多次，无法保证单例的唯一性
     *
     * @return
     */
    public static Singleton2 getInstance() {
        return null == instance ? instance = new Singleton2() : instance;
    }
}