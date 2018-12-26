package com.lee.test.jdk11.pattern.iterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class NodeMap<K, V> implements Iterable<Node<K, V>> {

    private Node<K, V>[] nodes;

    public NodeMap(Node<K, V>[] nodes) {
        this.nodes = nodes;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new NodeIterator(nodes);
    }


}

class MyTest {
    @Test
    public void t1() {
        Node<Integer, Integer>[] nodes = new Node[3];
        nodes[0] = new Node<>(1, 1);
        nodes[1] = new Node<>(2, 2);
        nodes[2] = new Node<>(3, 3);
        NodeMap<Integer, Integer> nodeMap = new NodeMap<>(nodes);
        for (Node<Integer, Integer> node : nodeMap) {
            Integer k = node.getK();
            Integer v = node.getV();
            System.out.println("k:" + k + ",v:" + v);
        }
    }
}


class NodeIterator<K, V> implements Iterator<Node<K, V>> {

    int index;
    Node<K, V>[] nodes;
    int len;

    public NodeIterator(Node<K, V>[] nodes) {
        this.nodes = nodes;
        len = nodes.length;
    }

    @Override
    public boolean hasNext() {
        return index < len;
    }

    @Override
    public Node<K, V> next() {
        return nodes[index++];
    }
}

class Node<K, V> {
    K k;
    V v;

    public Node(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public void setK(K k) {
        this.k = k;
    }

    public void setV(V v) {
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public V getV() {
        return v;
    }
}


