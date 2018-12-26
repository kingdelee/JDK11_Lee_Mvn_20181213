package datastructure.u_03_StacksAndQueues.t_02_ArrayStack.src;

public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
