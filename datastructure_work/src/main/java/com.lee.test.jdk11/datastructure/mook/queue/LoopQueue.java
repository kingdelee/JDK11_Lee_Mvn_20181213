package com.lee.test.jdk11.datastructure.mook.queue;

import java.util.Arrays;
import java.util.StringJoiner;

public class LoopQueue<E> implements QueueInterface<E> {

    private E[] data;

    private int size;

    private int head, tail;

    public LoopQueue(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = capacity;
    }

    /**
     * 入队的时候，tail后移
     *
     * @param e
     */
    @Override
    public void enQueue(E e) {
        // 如果满足扩容条件则进行扩容

        doIfExpandSize2();
//        data[tail++] = e;
//        tail %= data.length;

        data[tail + 1 == data.length ? tail = 0 : tail++] = e;

        size++;
    }

    /**
     * 扩容机制，将head从新依次放到新的数组的0索引处
     * 逻辑：先扩容，从头对其，再加入最后一个数
     */
    private void doIfExpandSize() {
        int len = data.length;
        if ((tail + 1) % len == head) {
            E[] newData = (E[]) new Object[len << 1];
            for (int i = 0, range = len - 1; i < range; i++) {
                newData[i] = data[(i + head) % len];
            }
            data = newData;
            head = 0;
            tail = len - 1;
        }

    }

    /**
     * 扩容机制
     * 将扩容逻辑，按tail在head之前与之后分别计算
     */
    private void doIfExpandSize2() {
        int len = data.length;
        if ((tail + 1) % len == head) {
            E[] newData = (E[]) new Object[len << 1];

            int i, range, right;
            if (tail < head) {
                for (i = 0, range = len - head; i < range; i++) {
                    newData[i] = data[head + i];
                }
                right = i;
                for (i = 0, range = tail; i < range; i++) {
                    newData[right + i] = data[i];
                }
                tail = len - 1;
                head = 0;
            } else {
                for (i = 0; i < len; i++) {
                    newData[i] = data[i];
                }
            }

            data = newData;

        }
    }

    @Override
    public E deQueue() {
        E ret = getFront();
        data[head++] = null;
        head %= data.length;
        size--;

        return ret;
    }

    /**
     * 缩容，当元素个数是占容器的1/4时，缩小容器的长度的1/2
     */
    private void doIfShrinkSize() {

        if (size == data.length >>> 2) {
            int len = size >>> 1, i, right, range;
            E[] newData = (E[]) new Object[len];

            if (tail < head) {

                for (i = 0, range = len - head; i < range; i++) {
                    newData[i] = data[head + i];
                }
                right = i;
                for (i = 0, range = tail; i < range; i++) {
                    newData[right + i] = data[i];
                }
                tail = len - 1;
                head = 0;


            } else {
                for (i = 0, range = tail; i < range; i++) {
                    newData[i] = data[head + i];
                }
            }

            data = newData;
        }


    }

    @Override
    public E getFront() {
        return data[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LoopQueue.class.getSimpleName() + "[", "]")
                .add("data=" + Arrays.toString(data))
                .add("size=" + size)
                .add("head=" + head)
                .add("tail=" + tail)
                .toString();
    }
}
