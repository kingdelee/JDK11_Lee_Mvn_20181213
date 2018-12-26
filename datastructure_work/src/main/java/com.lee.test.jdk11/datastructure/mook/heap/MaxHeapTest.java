package com.lee.test.jdk11.datastructure.mook.heap;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class MaxHeapTest {

    @Test
    public void t1() {
        MaxHeap<Integer> maxHeap = new MaxHeap();
        // 60,55,30,40,43,10,15,12,20,35,25
        IntStream.of(40, 12, 10, 60, 35, 15, 30, 20, 43, 55, 25).forEach(
                i -> maxHeap.add(i)
        );

        Integer peek = maxHeap.peek();
        System.out.println(peek + ",");

        for (Integer integer : maxHeap) {
            System.out.print(integer + ",");
        }

        System.out.println();

        System.out.println(maxHeap.remove(55));


    }

    @Test
    public void t2() {

        Integer[] arr = new Integer[]{40, 12, 10, 60, 35, 15, 30, 20, 43, 55, 25};

        MaxHeap<Integer> maxHeap = new MaxHeap(arr);

        Integer peek = maxHeap.peek();
        System.out.println(peek);


        for (Integer integer : maxHeap) {
            System.out.print(integer + ",");
        }

        System.out.println();

        System.out.println(maxHeap.remove(55));

        for (Integer integer : maxHeap) {
            System.out.print(integer + ",");
        }

    }

}
