package com.lee.test.jdk11.jvm.map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HashMapTest {

    static Node e;

    @Test
    public void t1() {
        Node loHead = null, loTail = null;
        Node hiHead = null, hiTail = null;
        Node next;
        do {
            next = e.next;
            System.out.println(e.hash & 16);
            if ((e.hash & 16) == 0) {
                if (loTail == null)
                    loHead = e;
                else
                    loTail.next = e;
                loTail = e;
            } else {
                if (hiTail == null)
                    hiHead = e;
                else
                    hiTail.next = e;
                hiTail = e;
            }
        } while ((e = next) != null);
        System.out.println(e);
    }

    @BeforeAll
    public static void post() {
        Node node = new Node(1);
        e = node;
        for (int i = 0; i < 16; i++) {
            node = node.setNextNode(new Node(i));
        }


    }

    static class Node {
        Node next;
        Integer val;
        int hash;

        public Node(Integer val) {
            this.val = val;
            setHash();

        }

        public void setHash() {
            hash = val.hashCode() ^ (val.hashCode() >>> 16);
            System.out.println(hash);
        }

        public Node(Node next, Integer val) {
            this(val);
            this.next = next;
        }

        public Node setNextNode(Node node) {
            this.next = node;
            return next;
        }


    }

}
