package com.lee.test.jdk11.datastructure.mook.heap;

public interface Heap<E> {

    boolean add(E e);

    boolean remove(Object e);

    E poll();

    E peek();

    // 取出堆中的最大元素，并且替换成元素e
    E replace(E e);

    boolean contains(E e);

    boolean isEmpty();

    int getSize();

    int parent(int i);

    int leftChild(int i);

    int rightChild(int i);

//    void siftUp(int i, E e);

//    void siftDown(int i, E e);

    // 将任意数组转化成堆的形式
    void heapify();

    // 找到堆中的最大元素
    E findMax();

    // 取出堆中最大元素，并删除
    E extractMax();

}
