package cn.learn.juc.chapter4;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 优先级阻塞队列
 *
 * @author zengxuebin
 * @since 2024/10/20 23:38
 */
public class PriorityQueueThreadDemo {

    public static void main(String[] args) {
        PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    System.out.println("take=>" + priorityBlockingQueue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                priorityBlockingQueue.put("task-" + i);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
