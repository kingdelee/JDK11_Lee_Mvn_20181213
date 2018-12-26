package com.lee.test.jdk11.datastructure.mook.linkedlist;

import java.util.Iterator;

public abstract class AbstractList<E> implements Iterable<E> {

    @Override
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    abstract ListIterator listIterator(int index);


}
