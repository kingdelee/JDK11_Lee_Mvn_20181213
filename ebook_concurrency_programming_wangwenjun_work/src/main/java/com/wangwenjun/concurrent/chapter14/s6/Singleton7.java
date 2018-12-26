package com.wangwenjun.concurrent.chapter14.s6;

/**
 * Holder方式
 */
public class Singleton7 {
    ;

    private byte[] data = new byte[1024];

    Singleton7() {
    }

    private enum EnumHolder {
        INSTANCE;
        private Singleton7 instance;

        EnumHolder() {
            this.instance = new Singleton7();
        }

        private Singleton7 getInstance() {
            return instance;
        }
    }


    public static Singleton7 getInstance() {
        return EnumHolder.INSTANCE.getInstance();
    }


}