package cn.learn.juc.chapter4;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

/**
 * 对象锁/私有锁
 *
 * @author zengxuebin
 * @since 2024/10/21 00:34
 */
public class PrivateLock {

    private final Object myLock = new Object();

    PrivateLock() {}

    void doSomething() {
        synchronized (myLock) {
            System.out.println("进入同步代码块");
        }
    }

    public Object getMyLock() {
        return myLock;
    }

    public static void main(String[] args) {
        PrivateLock privateLock = new PrivateLock();
        new Thread(() -> {
            // 通过公有方法获取锁
            Object lock = privateLock.getMyLock();
            // 和synchronized (myLock)是同一把锁
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(privateLock::doSomething).start();
    }
}
