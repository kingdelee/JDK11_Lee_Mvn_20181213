package com.lee.test.jdk11.datastructure.mook.queue;

public interface Deque<E> {

    void addFirst(E e);

    void addLast(E e);

    E removeFirst();

    E removeLast();

    E getFirst();

    E getLast();


}
