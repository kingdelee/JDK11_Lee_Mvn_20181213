package datastructure.u_07_SetAndMap.t_02_LinkedListSet.src;

public interface Set<E> {

    void add(E e);

    boolean contains(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
