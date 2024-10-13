package cn.learn.juc.chapter2.reentry;

import java.util.concurrent.TimeUnit;

/**
 * 被synchronized修饰的同步代码块对于同一个线程来说是可重入的，这意味着同一线程可以反复加入同步代码块
 * 被synchronized修饰的同步代码块在持有锁的线程执行完毕并释放锁之前，会无条件地阻塞后面其他线程进入
 *
 * @author zengxuebin
 * @since 2024/10/12 23:25
 */
public class ReentryApp {

    public synchronized void doSth() {
        System.out.println("doSth start");
        doOtherSth();
        System.out.println("doSth end");
    }

    public synchronized void doOtherSth() {
        System.out.println("doOtherSth start");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("doOtherSth end");
    }

    public static void main(String[] args) {
        ReentryApp reentryApp = new ReentryApp();
        new Thread(reentryApp::doSth).start();
    }
}
