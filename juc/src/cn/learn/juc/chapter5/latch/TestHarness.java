package cn.learn.juc.chapter5.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author zengxuebin
 * @since 2024/10/26 00:26
 */
public class TestHarness {

    public long timeTasks(int nThreads, Runnable task) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    // 2. 一旦1执行成功，这边便放行
                    startGate.await();
                    // try {} finally {}确保了endGate.countDown()的执行
                    try {
                        // 3. n个线程执行
                        task.run();
                    } finally {
                        // 4. 每个线程执行完 执行countDown
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }
        long start = System.nanoTime();
        // 1. 一但执行成功
        startGate.countDown();
        // 5. 所有均执行完 放行
        endGate.await();
        return System.nanoTime() - start;
    }

    public static void main(String[] args) throws InterruptedException {
        TestHarness testHarness = new TestHarness();
        long time = testHarness.timeTasks(3, () -> {
            System.out.println("hello");
        });
        System.out.println(time);
    }
}
