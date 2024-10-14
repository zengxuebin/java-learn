package cn.learn.juc.chapter3.visibility;

import java.util.concurrent.TimeUnit;

/**
 * volatile确保可见性
 *
 * @author zengxuebin
 * @since 2024/10/13 15:52
 */
public class Visibility {

    private volatile static boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            while (!flag) {

            }
            System.out.println("flag is true");
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flag = true;
    }
}
