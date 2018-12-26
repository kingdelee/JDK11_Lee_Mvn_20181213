package com.wangwenjun.concurrent.chapter14.s6;

/**
 * Holder方式
 */
public enum Singleton6 {

    INSTANCE;

    private byte[] data = new byte[1024];


    Singleton6() {
    }

    public static Singleton6 getInstance() {
        return INSTANCE;
    }
}