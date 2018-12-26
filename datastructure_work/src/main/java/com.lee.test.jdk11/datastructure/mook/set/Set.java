package com.lee.test.jdk11.datastructure.mook.set;

import com.lee.test.jdk11.datastructure.mook.map.BST;

public class Set<E extends Comparable<E>> implements SetInterface<E> {

    private BST<E> bst;

    public Set() {
        this.bst = new BST<>();
    }


    @Override
    public void add(E e) {
        if (!bst.contains(e)) bst.add(e);

    }

    @Override
    public E remove(E e) {
        return bst.remove(e);
    }

    @Override
    public E get(E e) {
        return bst.get(e);
    }

}
