package cn.learn.juc.chapter2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 由线程安全的类构成的类不一定线程安全的
 *
 * @author zengxuebin
 * @since 2024/10/12 00:21
 */
public class ConcurrentHashMapTest {

    private static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    public static void pufIfAbsent(String key, Function<String, Object> callback) {
        if (!map.containsKey(key)) {
            map.put(key, callback.apply(key));
        }
    }

    public static ConcurrentHashMap<String, Object> getMap() {
        return map;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
           ConcurrentHashMapTest.pufIfAbsent("key", key -> {
               String res = key + "111111";
               System.out.println(res);
               return res;
           });
        }).start();
        new Thread(() -> {
            ConcurrentHashMapTest.pufIfAbsent("key", key -> {
                String res = key + "222222";
                System.out.println(res);
                return res;
            });
        }).start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(ConcurrentHashMapTest.getMap());
    }
}
