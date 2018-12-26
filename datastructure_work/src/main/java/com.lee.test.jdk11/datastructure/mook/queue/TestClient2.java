package com.lee.test.jdk11.datastructure.mook.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

public class TestClient2 {

    /**
     * 测试入队逻辑
     */
    @Test
    public void t1() {
        LoopQueue<Integer> queue = new LoopQueue<>(4);

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);

        System.out.println(queue);

    }

    @Test
    public void t2() {
        LoopQueue<Integer> queue = new LoopQueue<>(4);

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.deQueue();
        System.out.println(queue);
        queue.deQueue();
        System.out.println(queue);
        queue.enQueue(1);
        System.out.println(queue);
        queue.enQueue(2);
        System.out.println(queue);
        queue.enQueue(4);

        System.out.println(queue);

    }

    @Test
    public void t3() {
        LoopQueue<Integer> queue = new LoopQueue<>(4);

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.deQueue();
        System.out.println(queue);
        queue.deQueue();
        System.out.println(queue);
        queue.enQueue(1);
        System.out.println(queue);
        queue.enQueue(2);
        System.out.println(queue);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);

        System.out.println(queue);

    }

    @Test
    public void t4() {
        ArrayDeque<Integer> queue = new ArrayDeque<>(4);

        queue.addLast(1);
        System.out.println(queue);
        queue.addLast(2);
        System.out.println(queue);
        queue.addLast(3);
        System.out.println(queue);
        Integer integer = queue.removeLast();
        System.out.println(integer);
        System.out.println(queue);

        System.out.println(-1 % 5);

    }

    @Test
    public void t5() {
        ArrayDeque<Integer> queue = new ArrayDeque<>(2);

        queue.addLast(1);
        System.out.println(queue);
        queue.addLast(2);
        System.out.println(queue);
        queue.addLast(3);
        System.out.println(queue);
        queue.addLast(4);
        System.out.println(queue);
        queue.removeFirst();
        System.out.println(queue);
        queue.removeFirst();
        System.out.println(queue);
        queue.addLast(5);
        System.out.println(queue);
        queue.addLast(6);
        System.out.println(queue);
        queue.addLast(7);
        System.out.println(queue);


    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> queue = new ArrayDeque<>(2);

        queue.addLast(1);
        queue.addLast(2);
        queue.removeFirst();

    }

}
