package datastructure.u_04_LinkedList.t_06_ImplementStackInLinkedList.src;

public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
