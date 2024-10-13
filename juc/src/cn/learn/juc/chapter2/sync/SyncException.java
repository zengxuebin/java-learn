package cn.learn.juc.chapter2.sync;

import java.util.concurrent.TimeUnit;

/**
 * 模拟抛出异常也会释放锁
 *
 * @author zengxuebin
 * @since 2024/10/12 23:04
 */
public class SyncException {

    public synchronized void testException(int i) {
        try {
            System.out.println("执行testException(" + i + ")开始");
            if (i == 3) {
                throw new RuntimeException("抛出异常");
            }
            TimeUnit.SECONDS.sleep(3);
            System.out.println("执行testException(" + i + ")结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SyncException syncException = new SyncException();
        new Thread(() -> syncException.testException(3)).start();
        new Thread(() -> syncException.testException(1)).start();
    }
}
