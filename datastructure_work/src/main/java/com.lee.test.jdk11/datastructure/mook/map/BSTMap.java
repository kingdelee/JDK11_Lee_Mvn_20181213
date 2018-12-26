package com.lee.test.jdk11.datastructure.mook.map;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;
    private int size;

    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    @Override
    public void put(K key, V value) {
        if (root == null) addRoot(key, value);
        addVal(key, value);
    }

    private void addVal(K key, V value) {
        Node<K, V> nodeIndex = root;
        addValNR(nodeIndex, key, value);
    }

    private void addValNR(Node<K, V> nodeIndex, K key, V value) {
        for (; ; ) {
            if (key.compareTo(nodeIndex.key) > 0) {

                if (nodeIndex.right == null) {
                    nodeIndex.right = new Node<>(key, value);
                    size++;
                    break;
                } else {
                    nodeIndex = nodeIndex.right;
                }

            } else if (key.compareTo(nodeIndex.key) < 0) {

                if (nodeIndex.left == null) {
                    nodeIndex.left = new Node<>(key, value);
                    size++;
                    break;
                } else {
                    nodeIndex = nodeIndex.left;
                }
            } else {
                return;
            }
        }
    }

    public void addRoot(K key, V value) {
        root = new Node(key, value);
        size++;
    }

    /**
     * 删除，不会删除节点之后的整个链，所以必须把其子链添加进来
     *
     * @return
     */
    @Override
    public V remove(K key) {
        Node<K, V> node;
        node = getNodeRCAndRemove(root, key);
        if (node == null) return null;

        size--;
        return node.value;
    }

    /**
     * 从第二个节点开始，且已经判断过该节点非删除节点
     *
     * @param node
     * @return
     */
    private Node<K, V> getNodeRCAndRemove(Node<K, V> node, K key) {

        if (key.compareTo(node.key) > 0) {

            if (key.compareTo(node.right.key) == 0) {
                Node<K, V> delNode = node.right;
                node.right = null;
                return delNode;
            } else {
                return getNodeRCAndRemove(node.right, key);
            }

        } else if (key.compareTo(node.key) < 0) {

            if (key.compareTo(node.left.key) == 0) {
                Node<K, V> delNode = node.left;
                node.left = null;
                return delNode;
            } else {
                return getNodeRCAndRemove(node.left, key);
            }

        } else if (key.compareTo(root.key) == 0) {
            Node<K, V> delNode = root;
            root = null;
            return delNode;
        }

        return null;
    }

    @Override
    public V getValue(K key) {
        Node<K, V> nodeRC = getNodeRC(root, key);
        return nodeRC != null ? nodeRC.value : null;
    }

    /**
     * 当equals时，返回该节点
     *
     * @param node
     * @return
     */
    private Node<K, V> getNodeRC(Node<K, V> node, K key) {

        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        }

        if (key.compareTo(node.key) > 0) {
            return getNodeRC(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            return getNodeRC(node.left, key);
        } else {
            return null;
        }
    }

    @Override
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() != 0;
    }

    @Override
    public int size() {
        return this.size;
    }
}
