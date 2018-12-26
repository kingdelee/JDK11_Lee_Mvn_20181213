package com.lee.test.jdk11.datastructure.mook.stack;

public interface StackInterface<E> {

    void push(E e);

    E peek();

    E pop();

    boolean isEmpty();

    int size();


}
