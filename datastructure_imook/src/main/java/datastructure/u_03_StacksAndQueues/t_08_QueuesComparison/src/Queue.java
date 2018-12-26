package datastructure.u_03_StacksAndQueues.t_08_QueuesComparison.src;

public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
