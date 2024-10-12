package cn.learn.juc.chapter2.generator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 检查从 {@link IntGenerator} 对象生成的整数是否为偶数
 *
 * @author zengxuebin
 * @since 2024/10/11 23:49
 */
public class EvenChecker implements Runnable {

    private final IntGenerator intGenerator;

    private final int id;

    public EvenChecker(IntGenerator intGenerator, int id) {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int val = intGenerator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even"+ ", 由id为：" + id + "的任务发现");
                intGenerator.cancel();
            }
        }
    }

    public static void test(IntGenerator intGenerator, int count) {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(intGenerator, i));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator intGenerator) {
        test(intGenerator, 10);
    }
}
