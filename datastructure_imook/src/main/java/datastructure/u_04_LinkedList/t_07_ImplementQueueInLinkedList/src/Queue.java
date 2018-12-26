package datastructure.u_04_LinkedList.t_07_ImplementQueueInLinkedList.src;

public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
