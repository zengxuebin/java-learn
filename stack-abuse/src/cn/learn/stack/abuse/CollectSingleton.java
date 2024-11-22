package cn.learn.stack.abuse;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collect and Turn Stream into Singleton Set
 * 如何在 Java 8+ 中收集列表并将其转换为单例集。
 *
 * @author zengxuebin
 * @since 2024/11/21 11:04
 */
public class CollectSingleton {
    private final Stream<?> stream;

    public CollectSingleton(Stream<?> stream) {
        this.stream = stream;
    }

    /**
     * return an immutable singleton Set that contains a single (and strictly single) immutable element.
     *
     * @return 返回一个包含单个（并且严格单个）不可变元素的不可变单例Set
     */
    public Set<?> getSingleton() {
        return stream.collect(
                Collectors.collectingAndThen(
                        // 我们将流收集到List中，这是一个终结操作（结束流）
                        Collectors.toList(),
                        // 再次对该列表进行流式传输，检查大小。
                        l -> {
                            // 如果大小大于 1 - 无法创建单例Set
                            // collectingAndThen()调用之前的操作管道中，流将被过滤除一个元素之外的所有元素。
                            if (l.size() > 1){
                                throw new RuntimeException();
                            } else{
                                return l.isEmpty()
                                        ? Collections.emptySet()
                                        // 使用singleton(l.get(0))创建一个新的单例Set
                                        // 将列表的第一个（也是唯一一个）元素传递到Collections.singleton()方法中。
                                        : Collections.singleton(l.get(0));
                            }
                        }
                )
        );
    }

    public static void main(String[] args) {
        CollectSingleton s1 = new CollectSingleton(Stream.of(1,2));
        // RuntimeException.class
        try {
            System.out.println(s1.getSingleton());
        } catch (RuntimeException e) {
            System.out.println("RuntimeException.class");
        }

        CollectSingleton s2 = new CollectSingleton(Stream.empty());
        Set<?> singleton = s2.getSingleton();
        // []
        System.out.println(singleton);

        CollectSingleton s3 = new CollectSingleton(Stream.of(1));
        singleton = s3.getSingleton();
        // [1]
        System.out.println(singleton);
    }
}
