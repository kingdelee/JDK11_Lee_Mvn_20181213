package datastructure.u_08_HeapAndPriorityQueue.t_06_Priority_Queue.src;

public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
