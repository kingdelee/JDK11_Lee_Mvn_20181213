package com.lee.test.jdk11.datastructure.mook.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

public class ArrayDequeTest {

    @Test
    public void t1() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(1);
        arrayDeque.add(2);
        arrayDeque.add(3);
        arrayDeque.add(4);

        System.out.println(arrayDeque.toString());
    }

    @Test
    public void t2() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(2);
        arrayDeque.addFirst(3);
        arrayDeque.addFirst(4);

        System.out.println(arrayDeque.toString());

    }

    @Test
    public void t3() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (Integer i = 0; i < 16; i++) {
            arrayDeque.addLast(i);
        }

        int data = 2;
        data = data == 1 ? data = 0 : data++;
        System.out.println(data);


        int a = 1;
        int b = 2;
        int c = 3;
        int d = 0;
        System.out.println();
        System.out.println(d = a = b = c);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);


    }

}
