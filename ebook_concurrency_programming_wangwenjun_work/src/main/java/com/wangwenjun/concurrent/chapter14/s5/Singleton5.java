package com.wangwenjun.concurrent.chapter14.s5;

/**
 * Holder方式
 */
public final class Singleton5 {
    //实例变量
    private byte[] data = new byte[1024];

    private static class Holder {
        private static Singleton5 instance = new Singleton5();
    }

    private Singleton5() {
    }


    public static Singleton5 getInstance() {
        return Holder.instance;
    }
}