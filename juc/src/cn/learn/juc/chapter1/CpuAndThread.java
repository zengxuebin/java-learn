package cn.learn.juc.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * 模拟cpu线程交互 一个线程等待I/O 另一个线程运行
 *
 * @author zengxuebin
 * @since 2024/10/11 21:35
 */
public class CpuAndThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("请输入信息：");
                final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    final String str = reader.readLine();
                    System.out.println("==>" + str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("每两秒执行一次，表示在做其他事情");
            }
        });

        t1.start();
        t2.start();
    }
}
