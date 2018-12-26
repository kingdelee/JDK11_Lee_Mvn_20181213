package com.lee.test.jdk11.exam;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestClient {

    @Test
    public void t1() {

        // 定义两个数组，若arr中元素有与brr的元素相同，则按顺序追加，不同，则按顺序输出到末尾
        int[] arr = {4, 8, 6, 4, 2, 5, 2, 7, 6, 5, 8, 2};
        int[] brr = {8, 2, 5, 10};


        // 输出结果，如下
        int[] result = {8, 8, 2, 2, 5, 5, 4, 4, 6, 7};


        int[] crr = new int[arr.length];

        // <brr的val, brr的下标>
        Map<Integer, Integer> hashMap = new LinkedHashMap<>();

        for (int i = 0; i < arr.length; i++) {
            Integer val = hashMap.get(arr[i]);
            if (val != null) {

            }

        }


        // crr[0,0,2,0,0,5,0,0,8]
        //

        for (int i = 0, len = brr.length; i < len; i++) {
            crr[i] = brr[i];
        }

        for (int i = 0, len = arr.length; i < len; i++) {

        }


    }

    @Test
    public void t2() {
        Map<Integer, Integer> hashMap = new HashMap<>();


        String a = "123456789123123123123";
//        String a = "10";
        int b = 30;
        System.out.println(hash(a));
        System.out.println(hash(b));
        System.out.println(place(hash(b)));
        System.out.println(place(hash(a)));
    }


    static final int hash(Object key) {
        int h;
        System.out.println("key.hashcode:" + key.hashCode());
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public int place(int hash) {
        return (16 - 1) & hash;
    }

}
