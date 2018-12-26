package com.lee.test.jdk11.datastructure.mook.heap;

import com.lee.test.jdk11.datastructure.mook.array.Array;

import java.util.Iterator;

public class MaxHeap<E extends Comparable<E>> implements Heap<E>, Iterable<E> {

    private Array<E> data;

    public MaxHeap() {
        this.data = new Array(16);
    }

    public MaxHeap(int capacity) {
        this.data = new Array(capacity);

    }

    public MaxHeap(E[] arr) {
        this.data = new Array(arr);
        heapify();
    }

    /**
     * 添加节点的时候，按数组顺序添加；并执行上浮
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        data.addLast(e);
        siftUp(getSize() - 1, e);
        return true;
    }

    /**
     * 和移除堆头是类似的
     * 将对于节点删除，再把末尾节点放该节点中，对该节点进行下沉
     *
     * @param e
     * @return
     */
    @Override
    public boolean remove(Object e) {
        if (e == null) return false;

        int i = indexOf(e);

        if (i != -1) {
            removeAt(i);
            data.setSize(getSize() - 1);
            return true;
        }

        return false;
    }

    private E removeAt(int i) {
        E e = get(i);
        E lastNode = get(getSize() - 1);
        data.set(i, lastNode);
        siftDown(i, lastNode);
        // 如果被下沉的节点比儿子要大，即没有下沉, i的节点仍是lastNode
        // 此时考虑，当堆失衡时需要上浮可能成为堆头
        // 如果仍无法上浮，则说明该节点也已经稳定下来
        if (get(i) == lastNode) {
            siftUp(i, e);
            if (get(i) != lastNode) {
                return lastNode;
            }
        }
        return lastNode;
    }

    /**
     * 获取节点所在位置的索引，该方式是从头遍历
     *
     * @param e
     * @return
     */
    private int indexOf(Object e) {

        for (int i = 0, len = getSize(); i < len; i++) {
            if (data.get(i).equals(e)) return i;
        }

        return -1;
    }

    /**
     * Retrieves and removes the head of this queue
     *
     * @return
     */
    @Override
    public E poll() {
        return extractMax();
    }

    /**
     * 查看堆头
     *
     * @return
     */
    @Override
    public E peek() {
        return get(0);
    }

    private E get(int i) {
        return data.get(i);
    }

    private void set(int i, E e) {
        data.set(i, e);
    }

    /**
     * 修改堆头的值，会造成堆失序，需要对堆头进行下沉操作
     *
     * @param e
     * @return 原来的堆头
     */
    @Override
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0, e);
        return ret;
    }

    @Override
    public boolean contains(E e) {
        return data.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public int parent(int i) {
        return (i - 1) >>> 1;
    }

    @Override
    public int leftChild(int i) {
        return (i << 1) + 1;
    }

    @Override
    public int rightChild(int i) {
        return (i << 1) + 2;
    }

    /**
     * 子比母大的时候，上浮
     *
     * @param i 末尾索引
     * @param e
     */
    private void siftUp(int i, E e) {
        while (i != 0) {
            int parentIndex = parent(i);
            E parentNode = get(parentIndex);

            if (e.compareTo(parentNode) > 0) {
                data.swap(i, parentIndex);
                i = parentIndex;
            } else {
                break;
            }
        }

    }

    /**
     * 方法2：
     * 队头出队的时候会下沉
     * 父亲与两个儿子比较，儿子大则与父亲位置交换，继续比较，否则下沉结束
     * 技巧：循环末尾时，size一定比index要大，否则跳出循环
     *
     * @param i 需要下沉的节点索引
     * @param e 下沉的节点值
     */
    private void siftDown2(int i, E e) {

        int left;
        while ((left = leftChild(i)) < getSize()) {
            E leftNode = get(left);
            E rightNode = get(left + 1);
            E fatherNode = get(i);

            E bigger;
            int biggerIndex;
            if (leftNode.compareTo(rightNode) > 0) {
                bigger = leftNode;
                biggerIndex = left;
            } else {
                bigger = rightNode;
                biggerIndex = left + 1;
            }

            if (fatherNode.compareTo(bigger) > 0) {
                break;
            } else {
                data.swap(i, biggerIndex);
                i = biggerIndex;
            }
        }

    }

    /**
     * 方法1：jdk
     * 队头出队的时候会下沉
     * 父亲与两个儿子比较，儿子大则与父亲位置交换，继续比较，否则下沉结束
     * 技巧： 如果被删除的节点是最后一层，则无需进行下沉操作，直接删除即可
     * 而最后一层的索引一定是>half，所以循环只需要<half即可
     * 否则需要对其进行xx操作
     *
     * @param i 需要下沉的节点索引
     * @param e 下沉的节点值
     */
    private void siftDown(int i, E e) {

        int half = getSize() >>> 1;

        while (i < half) {

            int leftIndex = leftChild(i);
            int rightIndex = leftIndex + 1;
            int biggerIndex;

            biggerIndex = get(leftIndex).compareTo(get(rightIndex)) > 0 ?
                    leftIndex : rightIndex;

            E bigger = get(biggerIndex);

            if (e.compareTo(bigger) > 0) break;

            set(i, bigger);
            i = biggerIndex;
        }
        set(i, e);

    }

    @Override
    public void heapify() {
        // 定位到一个非叶子节点，以此节点开始遍历到堆头，在循环中对当前节点进行下沉操作

        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            siftDown(i, get(i));
        }
    }

    /**
     * 返回堆头
     *
     * @return
     */
    @Override
    public E findMax() {
        return data.get(0);
    }

    /**
     * 取出并删除堆头：先交换堆头堆尾，再删除堆尾，再对堆头做下沉
     *
     * @return
     */
    @Override
    public E extractMax() {
        E max = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();

        siftDown(0, max);
        return max;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    class Itr implements Iterator<E> {

        int index;

        @Override
        public boolean hasNext() {
            return index < getSize();
        }

        @Override
        public E next() {
            return get(index++);
        }
    }

}
