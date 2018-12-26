package com.lee.test.jdk11.jvm.map.skeleton;

import org.junit.jupiter.api.Test;

public class HashMapTest {

    @Test
    public void t1() {

        Map<Integer, Integer> hashMap = new HashMap<>();

        Set<Map.Entry<Integer, Integer>> entrySet = hashMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            Integer k = entry.getK();
            Integer v = entry.getV();
            System.out.println("k:" + k + ",v:" + v);
        }

    }

}
