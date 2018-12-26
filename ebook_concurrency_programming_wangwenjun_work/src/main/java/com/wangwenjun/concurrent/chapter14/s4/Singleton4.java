package com.wangwenjun.concurrent.chapter14.s4;

import java.net.Socket;
import java.sql.Connection;

/**
 * Double Check
 */
public final class Singleton4 {
    //实例变量
    private byte[] data = new byte[1024];

    private static Singleton4 instance = null;
    Connection conn;
    Socket socket;

    private Singleton4() {
        // 假设创建了两个实例
        this.conn = null;
        this.socket = null;
    }

    /**
     * 问题出现在指令重排序，导致，尽管Singleton实例已经调用了构造方法创建了实例，
     * 使得instance指向了一块内存区域，所以此时instance非空。
     * 但是构造方法中的conn socket实例创建因为指令重排序可能导致即便执行了return instance也未完成。
     *
     * @return
     */
    public static Singleton4 getInstance() {
        if (null == instance) {
            synchronized (Singleton4.class) {
                if (null == instance) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
