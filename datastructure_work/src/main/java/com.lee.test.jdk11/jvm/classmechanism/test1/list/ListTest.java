package com.lee.test.jdk11.jvm.classmechanism.test1.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    @Test
    public void t1() {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for (Integer integer : list) {
            System.out.println(integer);
        }

        for (int i = 0, len = list.size(); i < len; i++) {
            Integer integer = list.get(i);
            System.out.println(integer);
        }


    }

}
