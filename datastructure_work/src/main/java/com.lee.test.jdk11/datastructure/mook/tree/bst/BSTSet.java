package com.lee.test.jdk11.datastructure.mook.tree.bst;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    /**
     * 这种使用二叉树，比使用链表的方式删除要快得多。当然最快的还是用hashmap实际上是hash方式删除
     *
     * @param e
     */
    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public void get(E e) {
        bst.get(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
