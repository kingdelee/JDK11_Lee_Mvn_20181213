package com.lee.test.jdk11.datastructure.mook.tree.bst;

public interface BSTInterface<E extends Comparable<E>> {

    // 忽略手动创建root
    void add(E e);

    // 优化方法，首次必须创建Root，之后调用AddVal
    void addRoot(E e);

    // 优化方法，首次必须创建Root，之后调用AddVal
    void addVal(E e);

    // 删除单一节点，不会删除节点之后的整个链
    E remove(E e);

    // 删除单一节点，不会删除节点之后的整个链
    void removeNoRet(E e);

    // 删除最大值的节点，不会删除节点之后的整个链
    E removeMax();

    // 删除最小值的节点，不会删除节点之后的整个链
    E removeMin();

    E get(E e);

    // 获取最小值
    E getMin();

    // 获取最大值
    E getMax();


    boolean contains(E e);

    boolean isEmpty();

    // 递归实现 前序遍历
    Object[] getArrByPreOrder();

    // 非递归实现 前序遍历
    Object[] getArrByPreOrderNR();

    Object[] getArrByInOder();

    Object[] getArrByInOderNR();

    Object[] getArrByPostOder();

    Object[] getArrByPostOderNR();

    // 层序遍历（即广度遍历）
    Object[] getArrByBFS();

    // 深度优先
    Object[] getArrByDFS();

    int getSize();

}
