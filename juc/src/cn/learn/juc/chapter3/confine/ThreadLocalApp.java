package cn.learn.juc.chapter3.confine;

import java.util.concurrent.TimeUnit;

/**
 * @author zengxuebin
 * @since 2024/10/13 23:03
 */
public class ThreadLocalApp {

    public static void main(String[] args) {
        final ThreadLocal<App> appThreadLocal = new ThreadLocal<>();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("read thread=>" + appThreadLocal.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            App app = new App();
            app.name = "new app";
            appThreadLocal.set(app);
            System.out.println("write thread=>" + appThreadLocal.get().name);
        }).start();
    }
}

class App {
    String name = "app";
}
