package cn.learn.juc.chapter1;

import cn.learn.juc.anno.NotThreadSafe;

import java.util.concurrent.TimeUnit;

/**
 * 不安全的序列
 *
 * @author zengxuebin
 * @since 2024/10/11 22:00
 */
@NotThreadSafe
public class UnsafeSequence {

    private int value;

    /**
     * 三个操作：读取value 将value+1 将结果写入到value
     *
     * @return value
     */
    public int getNext() {
        return value++;
    }

    public synchronized int getSafeNext() {
        // 虽然是紧凑的语法 但是包含了三个操作 读取-修改-写入 并非原子操作
        return value++;
    }

    public static void main(String[] args) {
        UnsafeSequence sequence = new UnsafeSequence();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "==>" + sequence.getSafeNext());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "==>" + sequence.getSafeNext());
            }
        }).start();
    }
}
