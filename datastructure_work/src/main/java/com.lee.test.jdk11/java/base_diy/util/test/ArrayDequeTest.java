package com.lee.test.jdk11.java.base_diy.util.test;

import com.lee.test.jdk11.java.base_diy.util.ArrayDeque;
import org.junit.jupiter.api.Test;

public class ArrayDequeTest {

    @Test
    public void t1() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque();
        for (int i = 0; i < 16; i++) {
            arrayDeque.addLast(i);
        }
    }

}
