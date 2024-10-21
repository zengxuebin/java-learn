package cn.learn.juc.chapter3.immutable;

import java.util.concurrent.TimeUnit;

/**
 * Store即使为final 也是非线程安全
 *
 * @author zengxuebin
 * @since 2024/10/20 18:52
 */
class App {
    String str = "app";
}

class Store {
    /**
     * 即使对象中所有的域都是final类型的，这个对象也仍然是可变的，因为final类型的域中可以保存对可变对象的引用
     */
    private final App app;

    public Store(App app) {
        this.app = app;
    }

    public App getApp() {
        return app;
    }
}

public class ImmutableApp {
    public static void main(String[] args) {
        App app = new App();
        Store store = new Store(app);
        // 读线程
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println(store.getApp().str);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        // 写线程
        new Thread(() -> {
            app.str = "new app";
        }).start();
    }
}
