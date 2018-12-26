package com.lee.test.jdk11.datastructure.mook.queue;

public interface QueueInterface<E> {

    void enQueue(E e);

    E deQueue();

    E getFront();

    int size();

    boolean isEmpty();


}
