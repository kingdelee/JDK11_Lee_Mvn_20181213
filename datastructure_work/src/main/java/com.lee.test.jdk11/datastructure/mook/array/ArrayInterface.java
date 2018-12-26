package com.lee.test.jdk11.datastructure.mook.array;

public interface ArrayInterface<E> {

    void addFirst(E e);

    void addLast(E e);

    void add(int index, E e);

    void set(int index, E e);

    int getCapacity();

    int getSize();

    E getFirst();

    E getLast();

    E get(int index);

    int find(E e);

    boolean contains(E e);

    boolean isEmpty();

    E removeElement(E e);

    E removeFirst();

    E removeLast();

    E remove(int index);


}
