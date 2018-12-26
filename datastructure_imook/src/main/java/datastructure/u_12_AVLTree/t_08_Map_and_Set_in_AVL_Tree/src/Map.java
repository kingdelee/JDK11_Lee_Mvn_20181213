package datastructure.u_12_AVLTree.t_08_Map_and_Set_in_AVL_Tree.src;

public interface Map<K, V> {

    void add(K key, V value);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    V remove(K key);

    int getSize();

    boolean isEmpty();
}
