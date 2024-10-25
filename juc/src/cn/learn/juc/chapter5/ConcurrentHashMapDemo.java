package cn.learn.juc.chapter5;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zengxuebin
 * @since 2024/10/24 15:24
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("key1", "value1");
        concurrentHashMap.put("key2", "value1");
        concurrentHashMap.put("key3", "value1");
        concurrentHashMap.put("key4", "value1");
        concurrentHashMap.put("key5", "value1");
        new Thread(() -> {
            System.out.println(concurrentHashMap.size());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(concurrentHashMap.size());
        }).start();
        new Thread(() -> {
            concurrentHashMap.put("key6", "value1");
        }).start();
    }
}
