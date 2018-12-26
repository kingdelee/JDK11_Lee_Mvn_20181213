package com.lee.test.jdk11.datastructure.mook.stack;

import com.lee.test.jdk11.datastructure.mook.array.Array;
import com.lee.test.jdk11.datastructure.mook.array.ArrayInterface;

public class Stack<E> implements StackInterface<E> {

    private ArrayInterface<E> array;

    public Stack(int capacity) {
        this.array = new Array<>(capacity);
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int size() {
        return array.getSize();
    }
}
