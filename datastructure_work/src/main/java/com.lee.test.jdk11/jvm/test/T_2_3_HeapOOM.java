package com.lee.test.jdk11.jvm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author zzm
 */
public class T_2_3_HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList<>();

        TimeUnit.SECONDS.sleep(30);

        byte[] bytes1 = new byte[1024 * 1024];
        byte[] bytes2 = new byte[2024 * 1024];
        byte[] bytes3 = new byte[3024 * 1024];
        byte[] bytes4 = new byte[4024 * 1024];
        byte[] bytes5 = new byte[5024 * 1024];
        byte[] bytes6 = new byte[5024 * 1024];
        bytes6 = new byte[5024 * 2024];
        bytes6 = new byte[5024 * 3024];
        bytes6 = new byte[5024 * 3024];
        bytes6 = new byte[5024 * 3024];
        bytes6 = new byte[5024 * 3024];
        bytes6 = new byte[5024 * 3024];
        t1();
        list.add(bytes1);

        List<Object> list2 = new ArrayList<>();

        list2.add(bytes2);
        list2.add(bytes3);

        TimeUnit.HOURS.sleep(1);


//        while (true) {
//            list.add(new OOMObject());
//        }

    }

    public static void t1() {
        byte[] bytes4 = new byte[6024 * 1024];

    }
}