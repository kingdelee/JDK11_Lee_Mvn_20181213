package com.lee.test.jdk11.datastructure.mook.array;

public class Array<E> implements ArrayInterface<E> {

    private E[] data;

    // 实时元素个数
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0, len = arr.length; i < len; i++)
            data[i] = arr[i];
        size = arr.length;
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        add(size, e);
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Require index > 0 && index <= size, index:" + index);
        }

        // 扩容
        if (size == data.length) {
            resize();
        }

        // index 往中间插，从index开始整体右移
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;

    }

    /**
     * 扩容，创建新的容器比旧容器长度扩大一倍，将旧的元素存储到新的容器中
     */
    private void resize() {
        E[] newData = (E[]) new Object[size << 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 缩容，将旧的容器缩小1/4
     */
    private void shrinkSize() {
        int len = size >>> 2;
        E[] newData = (E[]) new Object[len];
        for (int i = 0; i < len; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 长度
     *
     * @return
     */
    @Override
    public int getCapacity() {
        return data.length;
    }

    /**
     * 元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(size - 1);
    }

    @Override
    public E get(int index) {
        return data[index];
    }

    @Override
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void set(int index, E e) {
        data[index] = e;
    }

    @Override
    public boolean contains(E e) {
        return find(e) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 删除指定元素，即遍历找到该元素，并将将该元素后面的所有元素依次左移
     *
     * @param e
     * @return
     */
    @Override
    public E removeElement(E e) {
        return remove(find(e));
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E remove(int index) {

        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        size--;
        data[size] = null;

        // 判断是否满足缩容条件
        if (size == data.length >>> 2 && data.length > 10) {
            shrinkSize();
        }

        return ret;
    }

    /**
     * 交换两个索引位置的元素
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        E a = data[j];
        data[j] = data[i];
        data[i] = a;
    }


}
