package datastructure.u_03_StacksAndQueues.t_08_QueuesComparison.src;

import java.util.Random;

public class Main {

    // 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
//        for(int i = 0 ; i < opCount ; i ++)
//            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

//    public static void main(String[] args) {
//
//        int opCount = 100000;
//
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
//        double time1 = testQueue(arrayQueue, opCount);
//        System.out.println("ArrayQueue, time: " + time1 + " s");
//
//        LoopQueue<Integer> loopQueue = new LoopQueue<>();
//        double time2 = testQueue(loopQueue, opCount);
//        System.out.println("LoopQueue, time: " + time2 + " s");
//    }

    public static void main(String[] args) {
        LoopQueue<Integer> objectLoopQueue = new LoopQueue<>();
        objectLoopQueue.enqueue(1);
        objectLoopQueue.enqueue(2);
        objectLoopQueue.enqueue(3);
        objectLoopQueue.enqueue(4);
        objectLoopQueue.enqueue(5);
        objectLoopQueue.dequeue();
        objectLoopQueue.dequeue();
        objectLoopQueue.enqueue(6);
        objectLoopQueue.enqueue(7);
        objectLoopQueue.enqueue(8);
        objectLoopQueue.enqueue(9);
        objectLoopQueue.enqueue(10);
        objectLoopQueue.enqueue(11);
        objectLoopQueue.enqueue(12);
        objectLoopQueue.enqueue(13);
        objectLoopQueue.enqueue(14);
    }


}
