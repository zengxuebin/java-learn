package cn.learn.juc.chapter3.visibility;

import java.util.concurrent.TimeUnit;

/**
 * 多线程环境中，没有同步机制线程间无法及时看到彼此对共享变量的修改。
 *
 * @author zengxuebin
 * @since 2024/10/13 15:20
 */
public class NoVisibility {

    private static boolean read;

    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!read) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
//        try {
//            TimeUnit.MILLISECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        read = true;
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        number = 42;
    }
}
