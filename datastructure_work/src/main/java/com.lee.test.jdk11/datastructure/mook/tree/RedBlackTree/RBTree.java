package com.lee.test.jdk11.datastructure.mook.tree.RedBlackTree;

import com.lee.test.jdk11.datastructure.mook.tree.avl.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 定义：
 * 1.非黑即红
 * 2.根是黑
 * 3.叶子节点是黑的
 * 4.红节点的子节点是黑的
 * 5.节点到子节点的黑数是一样的
 * <p>
 * 技巧设定：
 * 1.新添加的节点一定是红的，再根据具体形态进行反转或者修改
 * 2.所有红色的节点都是左倾斜的
 * 3.黑色的左边可能是红的，黑色的右节点一定是黑的
 * 4.对于2-3树来说，只有融合的左节点才是红色的
 * 5.红黑树是保持黑平衡的二叉树，不是平衡二叉树
 * 6.红黑树最大高度是2logn, AVL是logn，红黑树添加删除元素比AVL快，查询比AVL慢，但是整体效率更优选
 * <p>
 * 案例：
 * 1.新添加节点时，该节点一定是红色的，如果在左边，则可以融合；如果在右边，则不能融合，需要做左旋
 * <p>
 * 2-3树的添加元素：
 * 先融合，融合之后越界了则再拆分
 *
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> implements Map<K, V> {

    private static final Logger logger = LogManager.getLogger(AVLTree.class);

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private int size;

    private class Node<K, V> {
        public K key;
        public V value;
        public Node left, right;
        // 节点颜色，非黑即红
        public boolean color = RED;
        // 节点高度
        int height = 1;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public RBTree() {
    }

    @Override
    public void put(K key, V value) {
        logger.info("put:" + key);

        if (root == null) addRoot(key, value);
        addVal(key, value);
        root.color = BLACK;

        logger.info("end:" + key);
        logger.info("-----:");
        StringBuilder sb = new StringBuilder();
        Arrays.asList(getArrByBFS()).forEach(i -> sb.append(i + ","));
        logger.info("-----sb:" + sb.toString());
    }

    private void addVal(K key, V value) {
        root = addValRC(root, key, value);
    }

    public void addRoot(K key, V value) {
        root = new Node(key, value);
//        getHeight(root);
        size++;
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


//        updateHeight(nodeIndex);
        nodeIndex = fixInsertionBalance(nodeIndex);

        // 方法出口
        return nodeIndex;
    }

    /**
     * 维护插入平衡
     *
     * @param node
     * @return 返回平衡后的节点
     */
    private Node<K, V> fixInsertionBalance(Node<K, V> node) {

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;

    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                       x
    //  /   \     左旋转         /   \
    // T1   x   --------->     y    T3
    //     / \               /   \
    //    T2 T3             T1   T2

    /**
     * 对节点y进行向左旋转操作，返回旋转后新的根节点x
     *
     * @param y
     * @return
     */
    private Node<K, V> leftRotate(Node<K, V> y) {

        Node<K, V> x = y.right;

        y.right = x.left;
        x.left = y;

        // 保持初始的父节点颜色一致
        x.color = y.color;
        // 新插入的左节点一定是红色的
        y.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {

        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }


    /**
     * 颜色翻转
     *
     * @param node
     */
    private void flipColors(Node<K, V> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
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

    /**
     * 判断一个节点是否为红色
     *
     * @param node
     */
    public boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color;
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
