package com.lee.test.jdk11.java.base_diy.util;

import java.util.Arrays;

public class ArrayDeque<E> {

    transient Object[] elements;
    transient int head;
    transient int tail;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public ArrayDeque() {
        elements = new Object[16];
    }

    public void addLast(E e) {
        if (e == null)
            throw new NullPointerException();
        final Object[] es = elements;
        es[tail] = e;
        if (head == (tail = inc(tail, es.length)))
            grow(1);
    }

    static final int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    static final int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    private void grow(int needed) {
        // overflow-conscious code
        final int oldCapacity = elements.length;
        int newCapacity;
        // Double capacity if small; else grow by 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        if (jump < needed
                || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);
        final Object[] es = elements = Arrays.copyOf(elements, newCapacity);
        // Exceptionally, here tail == head needs to be disambiguated
        if (tail < head || (tail == head && es[head] != null)) {
            // wrap around; slide first leg forward to end of array
            int newSpace = newCapacity - oldCapacity;
            System.arraycopy(es, head,
                    es, head + newSpace,
                    oldCapacity - head);
            for (int i = head, to = (head += newSpace); i < to; i++)
                es[i] = null;
        }
    }

    private int newCapacity(int needed, int jump) {
        final int oldCapacity = elements.length, minCapacity;
        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0)
                throw new IllegalStateException("Sorry, deque too big");
            return Integer.MAX_VALUE;
        }
        if (needed > jump)
            return minCapacity;
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0)
                ? oldCapacity + jump
                : MAX_ARRAY_SIZE;
    }
}
