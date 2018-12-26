package com.lee.test.jdk11.datastructure.mook.map;

import com.lee.test.jdk11.datastructure.mook.tree.bst.BSTInterface;

import java.util.ArrayDeque;
import java.util.Deque;

public class BST<E extends Comparable<E>> implements BSTInterface<E> {

    private int size;
    private int tmpI;
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

    @Override
    public void add(E e) {
        if (root == null) addRoot(e);
        addVal(e);
    }

    @Override
    public void addRoot(E e) {
        root = new Node(e);
        size++;
    }

    /**
     * 在6W数据的测试下，addValNR:4s, addValRC:9s, addValRC2:12s
     *
     * @param e
     */
    @Override
    public void addVal(E e) {
        Node<E> nodeIndex = root;
        addValNR(nodeIndex, e);
//        addValRC(nodeIndex, e);
//        root = addValRC2(nodeIndex, e);

    }

    /**
     * @param nodeIndex
     * @param e
     */
    private void addValNR(Node<E> nodeIndex, E e) {

        for (; ; ) {
            if (e.compareTo(nodeIndex.e) > 0) {

                if (nodeIndex.right == null) {
                    nodeIndex.right = new Node<>(e);
                    size++;
                    break;
                } else {
                    nodeIndex = nodeIndex.right;
                }

            } else if (e.compareTo(nodeIndex.e) < 0) {

                if (nodeIndex.left == null) {
                    nodeIndex.left = new Node<>(e);
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

    private void addValNR2(Node<E> nodeIndex, E e) {

        Node<E> father = nodeIndex;
        boolean isRight = false;

        while (nodeIndex != null) {
            father = nodeIndex;
            if (e.compareTo(nodeIndex.e) > 0) {
                nodeIndex = nodeIndex.right;
                isRight = true;
            } else if (e.compareTo(nodeIndex.e) < 0) {
                nodeIndex = nodeIndex.left;
                isRight = false;
            } else {
                return;
            }
        }

        if (isRight) father.right = new Node<>(e);
        else father.left = new Node(e);
        size++;
    }


    /**
     * 不允许插入相同的节点
     * 方法1：虽然不够简洁，但是没有返回值，仅在终点做一次指向.
     *
     * @param nodeIndex
     * @param e
     */
    private void addValRC(Node<E> nodeIndex, E e) {

        if (e.compareTo(nodeIndex.e) > 0) {
            if (nodeIndex.right == null) {
                nodeIndex.right = new Node<>(e);
                size++;
            } else addValRC(nodeIndex.right, e);
        } else if (e.compareTo(nodeIndex.e) < 0) {
            if (nodeIndex.left == null) {
                nodeIndex.left = new Node<>(e);
                size++;
            } else addValRC(nodeIndex.left, e);
        } else {
            return;
        }
    }

    private void addValRC1(Node<E> nodeIndex, E e) {
        Node<E> farther = nodeIndex;
        if (e.compareTo(nodeIndex.e) > 0) {
            nodeIndex = nodeIndex.right;
            if (nodeIndex == null) {
                farther.right = new Node<>(e);
                size++;
            } else addValRC(nodeIndex, e);

        } else if (e.compareTo(nodeIndex.e) < 0) {
            nodeIndex = nodeIndex.left;
            if (nodeIndex == null) {
                farther.left = new Node<>(e);
                size++;
            } else addValRC(nodeIndex, e);
        } else {
            return;
        }
    }

    /**
     * 不允许插入相同的节点
     * 方法2：虽然简洁，但是每次递归都会因为返回值重新指向
     *
     * @param nodeIndex
     * @param e
     */
    // 思路1：该方法必须实现几个逻辑：
    //      1.1 找到null并创建节点
    //      1.2 返回该节点
    //      1.3 接收right作为参数
    private Node<E> addValRC2(Node<E> nodeIndex, E e) {

        if (nodeIndex == null) {
            size++;
            return new Node<>(e);
        }

        if (e.compareTo(nodeIndex.e) > 0) {
            nodeIndex.right = addValRC2(nodeIndex.right, e);

        } else if (e.compareTo(nodeIndex.e) < 0) {
            nodeIndex.left = addValRC2(nodeIndex.left, e);
        }
        return nodeIndex;
    }

    /**
     * 删除，不会删除节点之后的整个链，所以必须把其子链添加进来
     *
     * @return
     */
    @Override
    public E remove(E e) {

        Node<E> node;
        node = getNodeRCAndRemove(root, e);
        if (node == null) return null;

        size--;
        return node.e;

    }

    @Override
    public void removeNoRet(E e) {

    }

    /**
     * 删除，不会删除节点之后的整个链，所以必须把其子链添加进来
     *
     * @return
     */
    @Override
    public E removeMax() {

        removeMaxRS(root);

        return getMinRS(root).e;
    }

    private Node<E> removeMaxRS(Node<E> node) {

        if (node.right == null) {
            Node<E> left = node.left;
            node.left = null;
            size--;
            return left;
        }

        node.right = removeMaxRS(node.right);
        return node;

    }


    @Override
    public E removeMin() {
        return null;
    }

    /**
     * 从第二个节点开始，且已经判断过该节点非删除节点
     *
     * @param node
     * @param e
     * @return
     */
    private Node<E> getNodeRCAndRemove(Node<E> node, E e) {

        if (e.compareTo(node.e) > 0) {

            if (e.compareTo(node.right.e) == 0) {
                Node<E> delNode = node.right;
                node.right = null;
                return delNode;
            } else {
                return getNodeRCAndRemove(node.right, e);
            }

        } else if (e.compareTo(node.e) < 0) {

            if (e.compareTo(node.left.e) == 0) {
                Node<E> delNode = node.left;
                node.left = null;
                return delNode;
            } else {
                return getNodeRCAndRemove(node.left, e);
            }

        } else if (e.compareTo(root.e) == 0) {
            Node<E> delNode = root;
            root = null;
            return delNode;
        }

        return null;
    }

    @Override
    public E get(E e) {
        Node<E> nodeRC = getNodeRC(root, e);
        return nodeRC != null ? nodeRC.e : null;
    }

    /**
     * 最小的节点一定是在左边链的最后一个
     *
     * @return
     */
    @Override
    public E getMin() {
        if (root == null) throw new IllegalArgumentException();
        return getMinRS(root).e;
    }

    private Node<E> getMinRS(Node<E> node) {
        if (node.left == null) return node;
        return getMinRS(node.left);
    }

    /**
     * 最大的节点一定是在右边链的最后一个
     *
     * @return
     */
    @Override
    public E getMax() {
        if (root == null) throw new IllegalArgumentException();
        return getMaxRS(root).e;
    }

    private Node<E> getMaxRS(Node<E> node) {
        if (node.right == null) return node;
        return getMaxRS(node.right);
    }

    /**
     * 当equals时，返回该节点
     *
     * @param node
     * @param e
     * @return
     */
    private Node<E> getNodeRC(Node<E> node, E e) {

        if (node == null) {
            return null;
        }

        if (e.equals(node.e)) {
            return node;
        }

        if (e.compareTo(node.e) > 0) {
            return getNodeRC(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            return getNodeRC(node.left, e);
        } else {
            return null;
        }
    }

    @Override
    public boolean contains(E e) {
        return get(e) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] getArrByPreOrder() {
        Object[] arr = new Object[size];
        tmpI = 0;
        preOrder(arr, root);
        tmpI = 0;
        return arr;
    }

    private void preOrder(Object[] arr, Node<E> node) {
        if (node == null) return;

        arr[tmpI++] = node.e;
        preOrder(arr, node.left);
        preOrder(arr, node.right);
    }

    /**
     * 前序遍历
     * 每次找到一个左边，输出左边，一直到最左；
     * 左边没有了，找右边，每找到一个右边，就输出；
     * 再找这个右边的左边
     *
     * @return
     */
    @Override
    public Object[] getArrByPreOrderNR() {

        if (root == null)
            return null;

        Object[] arr = new Object[size];
        int i = 0;

        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            arr[i++] = cur.e;
//            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }

        return arr;
    }

    @Override
    public Object[] getArrByInOder() {
        Object[] arr = new Object[size];
        tmpI = 0;
        inOrder(arr, root);
        tmpI = 0;
        return arr;
    }

    private void inOrder(Object[] arr, Node<E> node) {
        if (node == null) return;

        inOrder(arr, node.left);
        arr[tmpI++] = node.e;
        inOrder(arr, node.right);
    }


    @Override
    public Object[] getArrByInOderNR() {
        return null;
    }

    @Override
    public Object[] getArrByPostOder() {
        Object[] arr = new Object[size];
        tmpI = 0;
        postOrder(arr, root);
        tmpI = 0;
        return arr;
    }

    private void postOrder(Object[] arr, Node<E> node) {
        if (node == null) return;

        postOrder(arr, node.left);
        arr[tmpI++] = node.e;
        postOrder(arr, node.right);
    }

    @Override
    public Object[] getArrByPostOderNR() {
        return null;
    }

    /**
     * 广度优先
     *
     * @return
     */
    @Override
    public Object[] getArrByBFS() {


        Object[] arr = new Object[size];

        if (root == null) return arr;

        int i = 0;

        Deque<Node> arrayDeque = new ArrayDeque();
        arrayDeque.addLast(root);
        arr[i++] = root.e;

        while (!arrayDeque.isEmpty()) {

            Node node = arrayDeque.removeFirst();
            if (node.left != null) {
                arrayDeque.addLast(node.left);
                arr[i++] = node.left.e;
            }
            if (node.right != null) {
                arrayDeque.addLast(node.right);
                arr[i++] = node.right.e;
            }
        }

        return arr;
    }

    /**
     * 深度优先，前中后序遍历都是其实现
     *
     * @return
     */
    @Override
    public Object[] getArrByDFS() {
        return getArrByPreOrderNR();
    }

    @Override
    public int getSize() {
        return size;
    }


}
