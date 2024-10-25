package cn.learn.juc.chapter5.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author zengxuebin
 * @since 2024/10/24 22:34
 */
class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            // 线程被中断后，在处理异常后，中断状态会恢复
            System.out.println("打断睡眠线程执行之后该线程的状态为=>" + Thread.interrupted());
        }
        System.out.println("退出睡眠的执行");
    }
}

/**
 * IO操作不会被中断
 */
class IOBlocked implements Runnable {
    private InputStream is;

    public IOBlocked(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {

        try {
            System.out.println("等待读取信息");
            is.read();
        } catch (IOException e) {
            if (Thread.interrupted()) {
                System.out.println("Interrupted from blocked IO");
            } else  {
                throw new RuntimeException(e);
            }
        }
        System.out.println("退出IOBlocked");
    }
}

/**
 * synchronized上的等待不会中断
 */
class SynchronizedBlocked implements Runnable {

    public synchronized void method() {
        while (true) {
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        new Thread(this::method).start();
    }

    @Override
    public void run() {
        System.out.println("试着去调用method()");
        method();
        System.out.println("退出SynchronizedBlocked的执行");
    }
}

public class Interrupting {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    static void test(Runnable runnable) throws InterruptedException {
        Future<?> future = executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("将要去中断的类为=>" + runnable.getClass().getName());
        future.cancel(true);
        System.out.println("中断信号已经发送给了=>" + runnable.getClass().getName());
    }

    public static void main(String[] args) throws Exception {
//        test(new SleepBlocked());
//        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
    }
}
