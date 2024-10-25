package cn.learn.juc.chapter5.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author zengxuebin
 * @since 2024/10/25 00:08
 */
class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int ident) {
        id = ident;
        System.out.println("NeedsCleanup=>" + id);
    }

    public void cleanup() {
        System.out.println("CleaningUp=>" + id);
    }
}
class Blocked implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                NeedsCleanup needsCleanup = new NeedsCleanup(1);
                try {
                    System.out.println("模拟业务执行1秒钟");
                    TimeUnit.SECONDS.sleep(1);
                } finally {
                    // 查看异常抛出后 资源是否能得到清理
                    needsCleanup.cleanup();
                }
            }
        } catch (InterruptedException e) {
            System.err.println("处理中断异常");
        }
    }
}
public class InterruptingIdiom {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Blocked());
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1100);
        thread.interrupt();
    }
}
