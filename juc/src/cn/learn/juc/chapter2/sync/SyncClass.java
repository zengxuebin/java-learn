package cn.learn.juc.chapter2.sync;

import java.util.concurrent.TimeUnit;

/**
 * 类锁
 *
 * @author zengxuebin
 * @since 2024/10/12 13:06
 */
public class SyncClass {

    public void a() {
        synchronized (SyncClass.class) {
            try {
                System.out.println("a()...........begin");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("a()...........end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized static void b() {
        try {
            System.out.println("b()...........begin");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("b()...........end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SyncClass syncClass1 = new SyncClass();
        SyncClass syncClass2 = new SyncClass();
        new Thread(syncClass1::a).start();
        new Thread(() -> syncClass2.b()).start();
    }
}
