package cn.learn.juc.chapter2;

import cn.learn.juc.anno.NotThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 延迟初始化
 *
 * @author zengxuebin
 * @since 2024/10/12 12:13
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance = null;

    /**
     * 线程不安全 将会创建多个实例
     *
     * @return
     */
    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }

    public ExpensiveObject getSafeInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new ExpensiveObject();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        LazyInitRace initRace = new LazyInitRace();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(initRace::getInstance);
        }
        executorService.shutdown();
    }
}

class ExpensiveObject {
    public ExpensiveObject() {
        System.out.println(this);
    }
}
