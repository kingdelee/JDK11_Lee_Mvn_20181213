package com.lee.test.jdk11.datastructure.mook.tree.bst;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BST2<E extends Comparable<E>> {

    private int size;
    private Node<E> root;

    private class Node<E> {
        E e;
        Node<E> left, right;

        public Node(E e) {
            this.e = e;
        }

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    public Object[] getArrByPreOrder() {
        Object[] arr = new Object[size];
        preOrder(arr, 0, root);
        return arr;
    }

    private void preOrder(Object[] arr, int i, Node<E> node) {
        if (node == null) return;

        arr[i++] = node.e;
        preOrder(arr, i, node.left);
        preOrder(arr, i, node.right);
    }

    @Test
    public void t1() {
        BST2<Integer> bst2 = new BST2<>();
        Object[] arrByPreOrder = bst2.getArrByPreOrder();
        Arrays.asList(bst2).forEach(i -> System.out.println(i));
    }
}
