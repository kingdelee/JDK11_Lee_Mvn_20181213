package com.lee.test.jdk11.datastructure.mook.speedtest;

import org.junit.jupiter.api.Test;

public class SpeedTest {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        doSth();

        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000.0);

        // 2.471224986
        // 2.605615141
        // 2.696321451
        // 2.797985567
        // 2.770060606
        //
        // 2.597073826
        // 2.590505603
        // 2.583011695
        // 2.678756753

        // 2.743493769
        // 2.68638621
        // 2.630338268
        // 2.557580793
        // 2.658691178
        // 2.592763331

    }

    public void t1() {


    }

    public static void doSth() {
        int size = 500_000_000;
        int[] arr = new int[size];
        int[] brr = new int[size];

        for (int i = 0, len = arr.length; i < len; i++) {
            Object a = arr[i];
            do2(a);
        }
    }

    public static void do2(Object a) {

    }

    @Test
    public void t2() {
        System.out.println(2 << 1 + 1);
        System.out.println(1 + 3 << 1 + 1);
        // 加法优先级是大于移位运算符的
    }

}
