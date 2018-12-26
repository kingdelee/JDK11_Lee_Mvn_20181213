package com.lee.test.jdk11.datastructure.mook.union_find.v1;

import com.lee.test.jdk11.datastructure.mook.union_find.UF;

public class UnionFind implements UF {

    private int[] ids;

    public UnionFind(int size) {

        ids = new int[size];

        for (int i = 0; i < size; i++) {
            ids[i] = i;
        }
    }

    // 查看元素p和元素q是否所属一个集合
    // O(1)复杂度
    @Override
    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    // 查找元素p所对应的集合编号
    // O(1)复杂度
    private int find(int i) {
        if (i < 0 || i >= ids.length) throw new IllegalArgumentException("i is out of bound");
        return ids[i];
    }


    @Override
    public void unionElements(int a, int b) {
        int aID = find(a);
        int bID = find(b);

        if (aID == bID) return;

        for (int i = 0, len = ids.length; i < len; i++) {
            if (ids[i] == aID) ids[i] = bID;
        }

    }

    @Override
    public int getSize() {
        return ids.length;
    }


}
