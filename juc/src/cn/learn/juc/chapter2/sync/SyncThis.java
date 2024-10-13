package cn.learn.juc.chapter2.sync;

import java.util.concurrent.TimeUnit;

/**
 * 实例锁/对象锁都是同一把
 *
 * @author zengxuebin
 * @since 2024/10/12 13:01
 */
public class SyncThis {

    public void a() {
        synchronized (this) {
            try {
                System.out.println("a()...........begin");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("a()...........end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void b() {
        try {
            System.out.println("b()...........begin");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("b()...........end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SyncThis syncThis = new SyncThis();
        new Thread(syncThis::a).start();
        new Thread(syncThis::b).start();
    }
}
