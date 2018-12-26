package com.wangwenjun.concurrent.chapter14.s3;

/**
 * 懒汉式+同步
 */
public final class Singleton3 {
    //实例变量
    private byte[] data = new byte[1024];

    private static Singleton3 instance = null;

    private Singleton3() {
    }

    /**
     * synchronized 保证了同步创建唯一线程，但是效率低
     *
     * @return
     */
    public static synchronized Singleton3 getInstance() {
        return null == instance ? instance = new Singleton3() : instance;
    }
}