package cn.learn.juc.chapter3.escape;

import java.util.concurrent.TimeUnit;

/**
 * @author zengxuebin
 * @since 2024/10/13 19:32
 */
public class ContructorThread {

    private String str;

    public ContructorThread(String str) {
        new Thread(this::readStr).start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.str = str;
    }

    private void readStr() {
        System.out.println("readStr=>" + str);
    }

    public static void main(String[] args) {
        new ContructorThread("hello");
    }
}
