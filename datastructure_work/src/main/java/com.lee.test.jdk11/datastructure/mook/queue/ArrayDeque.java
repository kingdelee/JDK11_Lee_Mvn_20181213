package com.lee.test.jdk11.datastructure.mook.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 重点1：实际长度应该比预定长度+1，这样可以更好的为扩容逻辑做运算
 * 即给定定长度是4，那么实际data的长度应该为5.
 *
 * @param <E>
 */
public class ArrayDeque<E> implements Deque<E> {

    private E[] data;

    private int head, tail;

    private static final Logger logger = LogManager.getLogger(ArrayDeque.class);

    public ArrayDeque() {
        this.data = (E[]) new Object[16];
    }

    public ArrayDeque(int capacity) {
        this.data = (E[]) new Object[capacity + 1];
    }

    /**
     * 往队头添加，即head++
     * head操作是先移动再赋值
     *
     * @param e
     */
    @Override
    public void addFirst(E e) {
        if (e == null) throw new NullPointerException();

        head = head == 0 ? head = data.length - 1 : --head;
        data[head] = e;
        doIfGrow();
    }

    /**
     * 往队尾添加，即tail++
     * tail操作是先赋值再移动
     *
     * @param e
     */
    @Override
    public void addLast(E e) {
        if (e == null) throw new NullPointerException();

        data[tail++] = e;
        tail = tail == data.length ? tail = 0 : tail;
        doIfGrow();

//        if (head == (tail = inc(tail, data.length))){
//            logger.info("");
//        }

    }

    static final int inc(int i, int modulus) {
        logger.info(i);
        if (++i >= modulus) {
            i = 0;
        }
        return i;
    }


    private void doIfGrow() {
        if (tail == head) {
            E[] newData = (E[]) new Object[((data.length - 1) << 1) + 1];
            System.arraycopy(data, head, newData, 0, data.length - head);
            System.arraycopy(data, 0, newData, data.length - head + 1, head);

            tail = data.length;
            head = 0;
            data = newData;

        }
    }

    /**
     * 从队头出队，head++
     */
    @Override
    public E removeFirst() {
        E e = data[head];

        if (e == null) {
            return null;
        }

        data[head] = null;
        head = head == data.length ? head = 0 : ++head;

        logger.info(this);

        return e;
    }

    /**
     * 从队尾出队
     *
     * @return
     */
    @Override
    public E removeLast() {

        tail = tail == 0 ? data.length : --tail;

        E e = data[tail];
        if (e == null) {
            tail++;
            return null;
        }

        data[tail] = null;
        logger.info(this);

        return e;
    }

    @Override
    public E getFirst() {
        return data[0];
    }

    @Override
    public E getLast() {
        return data[data.length - 1];
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ArrayDeque.class.getSimpleName() + "[", "]")
                .add("data=" + Arrays.toString(data))
                .add("head=" + head)
                .add("tail=" + tail)
                .toString();
    }
}
