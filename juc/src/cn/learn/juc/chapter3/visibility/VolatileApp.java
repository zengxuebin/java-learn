package cn.learn.juc.chapter3.visibility;

import java.util.concurrent.TimeUnit;

/**
 * volatile正确实践
 *
 * @author zengxuebin
 * @since 2024/10/13 16:32
 */
public class VolatileApp {

    private volatile boolean asleep;

    public void count() {
        int num = 0;
        while (!asleep) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(++num + " 只羊");
        }
    }

    public static void main(String[] args) {
        VolatileApp volatileApp = new VolatileApp();
        new Thread(volatileApp::count).start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        volatileApp.asleep = true;
    }
}
