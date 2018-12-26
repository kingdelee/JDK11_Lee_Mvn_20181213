package com.lee.test.jdk11.datastructure.mook.tree.segment_tree;

public class SegmentTree<E> {

    private E[] data, tree;

    private Merge<E> merge;

    public SegmentTree(E[] arr, Merge<E> merge) {
        this.merge = merge;

        data = (E[]) new Object[arr.length];
        for (int i = 0, len = arr.length; i < len; i++)
            data[i] = arr[i];

        tree = (E[]) new Object[4 * arr.length];

        buildSegmentTree(0, 0, arr.length - 1);

    }

    /**
     * 在treeIndex的位置创建data区间[l...r]线段树，
     *
     * @param treeIndex 根节点的索引
     * @param l         区间左边边界
     * @param r         区间右边边界
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归的出口
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        // 等价于int mid = (l + r) / 2;
        int mid = l + ((r - l) >>> 1);

        // treeIndex位移到left
        buildSegmentTree(leftIndex, l, mid);
        // treeIndex位移到right
        buildSegmentTree(rightIndex, mid + 1, r);

        // 定义了节点的值，merge方法的结果
        tree[treeIndex] = merge.merge(tree[leftIndex], tree[rightIndex]);

        // 方法出口
    }


    /**
     * 查询一段区间内的数值，并将其merge后的结果返回
     * 默认使用现有的线段树
     *
     * @param ql 左边
     * @param qr 右边
     * @return 返回meger后的值
     */
    public E queryByMerge(int ql, int qr) {

        if (ql < 0 || ql >= data.length
                || qr < 0 || qr >= data.length
                || ql > qr) throw new IllegalArgumentException("Index is illegal.");

        return queryByMergeRC(0, 0, data.length - 1, ql, qr);

    }

    /**
     * @param treeIndex 当前节点的索引
     * @param l         被查询的大区间中左边
     * @param r         被查询的大区间中右边
     * @param ql        查询的小区间左边
     * @param qr        查询的小区间右边
     * @return
     */
    private E queryByMergeRC(int treeIndex, int l, int r, int ql, int qr) {

        // 递归退出条件
        if (ql == l && qr == r) {
            // 返回的这个是在build的时候定义的值
            return tree[treeIndex];
        }

        // 等价于int mid = (l + r) / 2;
        int mid = l + ((r - l) >>> 1);
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);


        // 三种情况

        E result;

        if (qr <= mid) {
            // 情况1：查询的区间全在左边
            result = queryByMergeRC(leftIndex, l, mid, ql, qr);
        } else if (ql >= mid + 1) {
            // 情况2：查询的区间全在右边
            result = queryByMergeRC(rightIndex, mid + 1, r, ql, qr);
        } else {
            // 情况3：查询的区间分布在两边
            E leftResult = queryByMergeRC(leftIndex, l, mid, ql, mid);
            E rightResult = queryByMergeRC(rightIndex, mid + 1, r, mid + 1, qr);
            result = merge.merge(leftResult, rightResult);
        }
        return result;

    }


    public int parent(int i) {
        return (i - 1) >>> 1;
    }

    public int leftChild(int i) {
        return (i << 1) + 1;
    }

    public int rightChild(int i) {
        return (i << 1) + 2;
    }

}
