package cn.learn.juc.chapter3.visibility;

/**
 * @author zengxuebin
 * @since 2024/10/13 16:32
 */
public class VolatileTest {

    private static volatile int race;

    public static void increase() {
        race++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
