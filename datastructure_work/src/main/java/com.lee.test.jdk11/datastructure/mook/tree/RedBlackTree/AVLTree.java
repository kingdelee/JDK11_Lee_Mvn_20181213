package com.lee.test.jdk11.datastructure.mook.tree.RedBlackTree;


import com.lee.test.jdk11.datastructure.mook.tree.avl.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1. AVL树，在添加节点的时候，应该计算节点的高度
 *
 * @param <K>
 * @param <V>
 */
public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {
    private Node<K, V> root;
    private int size;

    private static final Logger logger = LogManager.getLogger(AVLTree.class);


    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;
        // 节点高度
        int height = 1;

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
        logger.info("put:" + key);
        if (root == null) addRoot(key, value);
        addVal(key, value);
        logger.info("end:" + key);
        logger.info("-----:");
        StringBuilder sb = new StringBuilder();
        Arrays.asList(getArrByBFS()).forEach(i -> sb.append(i + ","));
        logger.info("-----sb:" + sb.toString());
    }

    private void addVal(K key, V value) {
        root = addValRC(root, key, value);
    }


    /**
     * @param nodeIndex
     * @param key
     * @param value
     * @return 最终递归返回的是root
     */
    private Node<K, V> addValRC(Node<K, V> nodeIndex, K key, V value) {


        if (nodeIndex == null) {
            // 递归出口
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(nodeIndex.key) < 0)
            nodeIndex.left = addValRC(nodeIndex.left, key, value);
        else if (key.compareTo(nodeIndex.key) > 0)
            nodeIndex.right = addValRC(nodeIndex.right, key, value);
        else
            nodeIndex.value = value;


        updateHeight(nodeIndex);
        nodeIndex = fixInsertionBalance(nodeIndex);

        // 方法出口
        return nodeIndex;
    }

    /**
     * 维护插入平衡
     *
     * @param nodeIndex
     * @return 返回平衡后的节点
     */
    private Node<K, V> fixInsertionBalance(Node<K, V> nodeIndex) {

        // 获取平衡因子
        int balanceFactor = getBalanceFactor(nodeIndex);
        logger.info("node:" + nodeIndex.key + ",fact:" + balanceFactor);
        // LL
        if (balanceFactor > 1 && getBalanceFactor(nodeIndex.left) >= 0)
            nodeIndex = rightRotate(nodeIndex);
            // RR
        else if (balanceFactor < -1 && getBalanceFactor(nodeIndex.right) <= 0)
            nodeIndex = leftRotate(nodeIndex);
            // LR
        else if (balanceFactor > 1 && getBalanceFactor(nodeIndex.left) < 0)
            nodeIndex = rightRotate(nodeIndex.left = rightRotate(nodeIndex.left));
            // RL
        else if (balanceFactor < -1 && getBalanceFactor(nodeIndex.right) > 0)
            nodeIndex = leftRotate(nodeIndex.right = rightRotate(nodeIndex.right));

        return nodeIndex;

    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2

    /**
     * 对节点y进行向右旋转操作，返回旋转后新的根节点x
     *
     * @param y 对该节点进行右旋
     */
    private Node<K, V> rightRotate(Node<K, V> y) {

        Node<K, V> x = y.left;
        Node<K, V> t3 = x.right;
        x.right = y;
        y.left = t3;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4

    /**
     * 对节点y进行向左旋转操作，返回旋转后新的根节点x
     *
     * @param y
     * @return
     */
    private Node<K, V> leftRotate(Node<K, V> y) {

        Node<K, V> x = y.right;
        Node<K, V> t2 = x.left;

        x.left = y;
        y.right = t2;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 获取平衡因子，即高度差，左节点的高度-右节点的高度；高度差>1或<-1就失衡
     *
     * @param nodeIndex
     * @return
     */
    private int getBalanceFactor(Node<K, V> nodeIndex) {
        return nodeIndex != null ? getHeight(nodeIndex.left) - getHeight(nodeIndex.right) : 0;
    }

    private void updateHeight(Node<K, V> nodeIndex) {
        nodeIndex.height = Math.max(getHeight(nodeIndex.left), getHeight(nodeIndex.right)) + 1;
        logger.info("nodeIndex:" + nodeIndex.key + ", height:" + nodeIndex.height);
    }

    public void addRoot(K key, V value) {
        root = new Node(key, value);
//        getHeight(root);
        size++;
    }

    /**
     * 在已经更新的height的基础上获取height的值，静态的
     *
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        return node != null ? node.height : 0;
    }

    /**
     * 计算任意节点的高度，用左节点和右节点的高度最大值+1则是当前父节点的高度
     * 在没有height属性的情况下使用，动态的height
     *
     * @param key
     * @return
     */
    public int getAnyNodeHeight(K key) {
        Node<K, V> nodeRC = getNodeRC(root, key);
        if (nodeRC == null) return 0;

        return getAnyNodeHeightRC(nodeRC);
    }

    private int getAnyNodeHeightRC(Node<K, V> node) {
        if (node == null) return 0;
        return Math.max(getAnyNodeHeightRC(node.left), getAnyNodeHeightRC(node.right)) + 1;
    }

    /**
     * 删除，不会删除节点之后的整个链，所以必须把其子链添加进来
     *
     * @return
     */
    @Override
    public V remove(K key) {
        Node<K, V> node = getNodeRC(root, key);

        if (node != null) {
            root = getNodeRCAndRemove(root, key);
            return node.value;
        }
        return null;

    }

    /**
     * 从第二个节点开始，且已经判断过该节点非删除节点
     *
     * @param node
     * @return 最终返回的是递归后的root
     */
    private Node<K, V> getNodeRCAndRemove(Node<K, V> node, K key) {
        Node<K, V> retNode = null;

        if (key.compareTo(node.key) > 0) {
            // 必然返回被删除后的需要指向的子节点，用父节点接住
            node.right = getNodeRCAndRemove(node.right, key);

        } else if (key.compareTo(node.key) < 0) {
            node.left = getNodeRCAndRemove(node.left, key);

        } else {

            // 递归出口

            // node是要被删除的节点，返回需要给父节点接住的节点
            // 通过tmpNode将被删除的节点返回
//            tmpVal = node.e;

            if (node.left == null) {
                // 删除逻辑1：左子树为空，将右边直接接上
                retNode = node.right;
                node.right = null;
                node.key = null;
                size--;

            } else if (node.right == null) {
                //  删除逻辑2：右子树为空，将左边直接接上
                retNode = node.left;
                node.left = null;
                node.key = null;
                size--;

            } else {
                //  删除逻辑3：node是需要被删除的节点，且左右都有子树，需要找到后继【大于当前节点的最小值】，交换两者位置

                Node<K, V> succeed = getSucceed(node);
                // 删除后继，使用node.right去找寻后继并删除，则返回值是node的右子树，而不是后继的右子树
                // 用后继的右子树去接，实现了位置替换
                succeed.right = getNodeRCAndRemove(node.right, succeed.key);
                succeed.left = node.left;

                node.left = node.right = null;

                // return successor;
                retNode = succeed;

            }
        }

        if (retNode == null) return null;

        updateHeight(retNode);
        retNode = fixInsertionBalance(retNode);

        return retNode;
    }

    /**
     * 获取该节点的后继，即大于该节点的最小节点，即右儿子的最左节点
     *
     * @param node
     * @return
     */
    private Node<K, V> getSucceed(Node<K, V> node) {
        if ((node = node.right) != null) {
            while (node.left != null) {
                node = node.left;
            }
        }
        return node;

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

    @Override
    public boolean isBalance() {
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node<K, V> node) {

        if (node == null) return true;
        if (Math.abs(getBalanceFactor(node)) > 1) return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 广度优先
     *
     * @return
     */
    public Object[] getArrByBFS() {


        Object[] arr = new Object[size];

        if (root == null) return arr;

        int i = 0;

        Deque<Node> arrayDeque = new ArrayDeque();
        arrayDeque.addLast(root);
        arr[i++] = root.key;

        while (!arrayDeque.isEmpty()) {

            Node node = arrayDeque.removeFirst();
            if (node.left != null) {
                arrayDeque.addLast(node.left);
                arr[i++] = node.left.key;
            }
            if (node.right != null) {
                arrayDeque.addLast(node.right);
                arr[i++] = node.right.key;
            }
        }

        return arr;
    }
}
