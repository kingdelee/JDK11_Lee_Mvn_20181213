package com.lee.test.jdk11.datastructure.mook.union_find.v6;

import com.lee.test.jdk11.datastructure.mook.union_find.UF;

public class UnionFind6 implements UF {


    // 我们的第二版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
    private int[] parent;

    // 通过层级优化，将层级少的嫁接到层级多的
    private int[] rank;

    // 构造函数
    public UnionFind6(int size) {

        parent = new int[size];
        rank = new int[size];


        // 初始化，定义，每一个元素都是一个独立的集合
        // 当下标对应的值是与下标一致时，表示自己指向自己，自己是root节点
        // 当下标对应的值是与不下标一致时，表示该值指向以该值对应的集合的root节点
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

    }


    @Override
    public boolean isConnected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    /**
     * 连接a b 元素所在的集合
     * 即使a的root与b的root连接在一起，方向设定为a->b
     * 将层级少的嫁接到层级多的
     *
     * @param a
     * @param b
     */
    @Override
    public void unionElements(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);

        if (aRoot == bRoot) return;

        if (rank[aRoot] < rank[bRoot]) {
            parent[aRoot] = bRoot;
        } else if (rank[aRoot] > rank[bRoot]) {
            parent[bRoot] = aRoot;
        } else {
            parent[aRoot] = bRoot;
            rank[bRoot]++;
        }


    }

    /**
     * 找到a元素所在集合的root节点
     *
     * @param a
     * @return root节点的值
     */
    private int findRoot(int a) {

        // 遍历找到root，直到 root值 与[a]下标相等，root即最终的父节点
        int root;
        while ((root = parent[a]) != a) {
            // 设定路径压缩，发生在find过程，仅仅是为了在find的时候优化查询效率
            // 将当前节点指向父亲的父亲，即优化效率提高一倍
            root = parent[parent[a]];
            a = root;
        }

        return root;

    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
