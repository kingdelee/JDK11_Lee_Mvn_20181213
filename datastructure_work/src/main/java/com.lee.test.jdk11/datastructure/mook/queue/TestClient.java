package com.lee.test.jdk11.datastructure.mook.queue;

import org.junit.jupiter.api.Test;

public class TestClient {

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
        ArrayDeque<Integer> queue = new ArrayDeque<>(4);

        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        System.out.println(queue);
        queue.addLast(5);
        System.out.println(queue);


    }

    @Test
    public void test_addFirst() {
        ArrayDeque<Integer> queue = new ArrayDeque<>(4);

        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        System.out.println(queue);
        queue.addFirst(5);
        System.out.println(queue);


    }

    @Test
    public void test_removeLast() {
        ArrayDeque<Integer> queue = new ArrayDeque<>(4);

        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        System.out.println(queue);
        queue.addFirst(5);
        System.out.println(queue);

        queue.removeFirst();
        queue.removeLast();
        queue.removeLast();
        queue.removeFirst();
        queue.removeLast();
        queue.removeLast();
        queue.removeFirst();


    }

}
