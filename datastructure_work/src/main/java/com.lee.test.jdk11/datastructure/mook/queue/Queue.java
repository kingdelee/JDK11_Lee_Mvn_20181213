package com.lee.test.jdk11.datastructure.mook.queue;

import com.lee.test.jdk11.datastructure.mook.array.Array;
import com.lee.test.jdk11.datastructure.mook.array.ArrayInterface;

public class Queue<E> implements QueueInterface<E> {

    private ArrayInterface<E> array;

    public Queue(int capacity) {
        this.array = new Array(capacity);
    }

    @Override
    public void enQueue(E e) {
        array.addLast(e);
    }

    @Override
    public E deQueue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int size() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
}
