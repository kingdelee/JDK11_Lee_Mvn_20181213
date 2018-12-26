package com.lee.test.jdk11.jvm.map.t3;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class HashMapTest2 {

    @Test
    public void t1() {
        Node[] nodes = new Node[3];
        nodes[0] = new Node(0, 0);
        nodes[1] = new Node(1, 1);
        nodes[2] = new Node(2, 2);

//        nodes[0].setNextNode(nodes[1]);
//        nodes[1].setNextNode(nodes[2]);

        HashIterator hashIterator = new HashIterator(nodes);

        while (hashIterator.hasNext()) {
            Node next = hashIterator.next();
            System.out.println(next);
        }

//        A a = new A();
//        for( Iterator iterator = a.iterator(); iterator.hasNext();){
//
//        }

        int i = 0;
        Node node = nodes[i++];
        System.out.println(node.getK());

        C c = new C();
        for (Object o : c) {

        }

        B b = new B();


    }

}

class A implements Iterable {

    @Override
    public Iterator iterator() {
        return null;
    }
}

class B implements Iterator {


    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}

class C implements Iterable, Iterator {

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}

class Node<K, V> {
    Node<K, V> next;
    Node<K, V> current;
    K key;
    V val;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public Node setNextNode(Node next) {
        this.next = next;
        return next;
    }

    public K getK() {
        return this.key;
    }
}

class HashIterator<K, V> implements Iterator<Node<K, V>> {
    Node<K, V> next;        // next entry to return
    Node<K, V> current;     // current entry
    int index;             // current slot
    Node<K, V>[] t;

    HashIterator(Node<K, V>[] table) {
        t = table;
        current = next = null;
        index = 0;
        if (t != null && table.length > 0) { // advance to first entry
            do {
            } while (index < t.length && (next = t[index++]) == null);
        }
    }

    public final boolean hasNext() {
        return next != null;
    }

    @Override
    public Node<K, V> next() {
        Node<K, V> e = next;
        if ((next = (current = e).next) == null && t != null) {
            do {
            } while (index < t.length && (next = t[index++]) == null);
        }
        return e;
    }


}
