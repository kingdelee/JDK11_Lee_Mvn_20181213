package com.wangwenjun.concurrent.chapter14.s1;

/**
 * 饿汉式
 */
public final class Singleton1 {
    //实例变量
    private byte[] data = new byte[1024];

    private static Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}