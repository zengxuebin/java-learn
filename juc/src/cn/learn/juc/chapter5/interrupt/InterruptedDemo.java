package cn.learn.juc.chapter5.interrupt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zengxuebin
 * @since 2024/10/24 23:18
 */
public class InterruptedDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        Thread thread = new Thread(() -> {
            try {
                System.out.println("从有界队列中获取元素=>" + blockingQueue.take());
            } catch (InterruptedException e) {
                System.err.println("发生异常之后该线程的状态为=>" + Thread.interrupted());
                Thread.currentThread().interrupt();
                System.err.println("发生异常之后该线程的状态为=>" + Thread.interrupted());
            }
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.interrupt();
    }
}
