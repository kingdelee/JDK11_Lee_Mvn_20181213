package com.lee.test.jdk11.datastructure.mook.linkedlist;

import com.lee.test.jdk11.datastructure.mook.queue.Deque;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class LinkedList<E> extends AbstractList<E> implements List<E>, Deque<E> {

    Node<E> first, last;

    int size;

    public LinkedList() {

    }


    @Override
    ListIterator listIterator(int index) {
        return new ListItr(index);
    }


    class ListItr implements ListIterator<E> {

        // 仅根据next定位即可，prev只是临时值
        private Node<E> next;
        private Node<E> prev;
        private int index;

        public ListItr(int index) {
            this.index = index;
            next = getNode(index);
        }

        @Override
        public boolean hasPrev() {
            return index > 0;
        }

        @Override
        public E prev() {
            if (!hasPrev()) throw new NoSuchElementException();

            prev = next.prev;
            next = prev;
            index--;

            return prev.data;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            prev = next;
            next = next.next;
            index++;

            return prev.data;
        }
    }

    private class Node<E> {
        E data;
        Node<E> prev, next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null, first);
        first = first == null ? last = newNode : (first.prev = newNode);
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, last, null);
        last = last == null ? first = newNode : (last.next = newNode);
        size++;
    }

    @Override
    public E removeFirst() {
        if (first == null) throw new NoSuchElementException();

        E delData = first.data;
        Node<E> newFirst = first.next;

        first.next = null;
        first.data = null;

        if (newFirst == null) {
            // 已经删完没有节点了, 需要把last置空
            last = null;
        } else {
            newFirst.prev = null;
        }

        first = newFirst;
        size--;

        return delData;
    }

    @Override
    public E removeLast() {
        if (last == null) throw new NoSuchElementException();

        E oldData = last.data;
        Node<E> newLast = last.prev;

        last.prev = null;
        last.data = null;

        if (newLast == null) {
            // 已经删完没有节点了, 需要把first置空
            first = null;
        } else {
            newLast.next = null;
        }

        last = newLast;
        size--;


        return oldData;
    }

    @Override
    public E getFirst() {
        return first.data;
    }

    @Override
    public E getLast() {
        return last.data;
    }

    /**
     * 这个不是deque的接口，是list接口的方法
     * 所以此时是使用链表的方式实现随机访问，效率低下
     * 即便采取从两边选择更近的一边开始找
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    private Node<E> getNode(int index) {
        if (first == null) return null;
        if (index >= size) throw new IndexOutOfBoundsException();

        Node<E> tmpNode;
        if (index < (size >> 1)) {
            int i = 0;
            tmpNode = first;
            while (i++ != index) {
                tmpNode = tmpNode.next;
            }
        } else {
            int lastIndex = size - 1;
            tmpNode = last;
            while (lastIndex-- != index) {
                tmpNode = tmpNode.prev;
            }
        }
        return tmpNode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LinkedList.class.getSimpleName() + "[", "]")
                .add("first=" + first)
                .add("last=" + last)
                .toString();
    }
}
