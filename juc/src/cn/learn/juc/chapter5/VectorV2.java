package cn.learn.juc.chapter5;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author zengxuebin
 * @since 2024/10/24 09:39
 */
public class VectorV2 {

    private static String getLast(Vector<String> list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            try {
                TimeUnit.NANOSECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return list.get(lastIndex);
        }
    }

    private static void delList(Vector<String> list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }

    public static void main(String[] args) {
        Vector<String> list = new Vector<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        new Thread(() -> {
            System.out.println(getLast(list));
        }).start();
        new Thread(() -> {
            delList(list);
        }).start();
    }
}
