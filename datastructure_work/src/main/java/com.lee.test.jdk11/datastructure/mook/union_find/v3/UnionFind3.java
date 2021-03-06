package com.lee.test.jdk11.datastructure.mook.union_find.v3;

import com.lee.test.jdk11.datastructure.mook.union_find.UF;

public class UnionFind3 implements UF {


    // 我们的第二版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
    private int[] parent;

    // 节点所在集合的元素个数，初始化时每个集合的个数为1
    private int[] sizes;

    // 构造函数
    public UnionFind3(int size) {

        parent = new int[size];
        sizes = new int[size];


        // 初始化，定义，每一个元素都是一个独立的集合
        // 当下标对应的值是与下标一致时，表示自己指向自己，自己是root节点
        // 当下标对应的值是与不下标一致时，表示该值指向以该值对应的集合的root节点
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sizes[i] = 1;
        }

    }


    @Override
    public boolean isConnected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    /**
     * 连接a b 元素所在的集合
     * 即使a的root与b的root连接在一起，方向设定为a->b
     * 让集合个数小的集合的root指向集合个数大的集合的root
     *
     * @param a
     * @param b
     */
    @Override
    public void unionElements(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);

        if (aRoot == bRoot) return;

        if (sizes[aRoot] < sizes[bRoot]) {
            // 少的指向大的
            parent[aRoot] = bRoot;
            // 把小的融入到大的中
            sizes[bRoot] += sizes[aRoot];
        } else {
            parent[bRoot] = aRoot;
            sizes[aRoot] += sizes[bRoot];
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
            a = root;
        }

        return root;

    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
