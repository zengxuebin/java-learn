package cn.learn.juc.chapter3.escape;

import java.util.concurrent.TimeUnit;

/**
 * 测试
 *
 * @author zengxuebin
 * @since 2024/10/13 19:24
 */
public class EscapeApp {

    public static void main(String[] args) {
        SomeEventSource someEventSource = new SomeEventSource();

        new Thread(() -> {
            ThisEscape thisEscape = new ThisEscape(someEventSource);
        }).start();

        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            someEventSource.processEvent(new Event() {});
        }).start();

    }
}
