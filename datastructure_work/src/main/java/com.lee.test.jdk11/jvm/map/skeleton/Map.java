package com.lee.test.jdk11.jvm.map.skeleton;


public interface Map<K, V> {

    void put(K k, V v);

    V get(K k);

    /**
     * 映射视图
     *
     * @return
     */
    Set<Entry<K, V>> entrySet();

    /**
     * 迭代容器
     *
     * @param <K>
     * @param <V>
     */
    interface Entry<K, V> {
        V getV();

        K getK();

        void setV();
    }
}
