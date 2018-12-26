package datastructure.u_03_StacksAndQueues.t_05_ArrayQueue.src;

public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
