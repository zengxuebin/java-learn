package cn.learn.stack.abuse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Collect and Slice List or Stream
 * 收集和切片列表或流
 *
 * @author zengxuebin
 * @since 2024/11/21 11:29
 */
public class CollectSlice {

    private final Stream<Integer> s;
    private final int from;
    private final int to;

    public CollectSlice(Stream<Integer> s, int from, int i) {
        this.s = s;
        this.from = from;
        to = i;
    }

    public List<Integer> getList() {
        return s.collect(
                Collectors.collectingAndThen(
                        // 我们将流收集到List中，这是一个终结操作（结束流）
                        Collectors.toList(),
                        // 再次对该列表进行流式传输，利用skip()和limit()对原始列表进行子列表，收集回List 。
                        l -> l.stream()
                                .skip(from)
                                .limit(to - (from - 1))
                                .collect(Collectors.toList())
                )
        );
    }

    public static void main(String[] args) {
        Stream<Integer> stream = IntStream
                .rangeClosed(0, 10)
                .boxed();
        CollectSlice slice = new CollectSlice(stream, 3, 7);
        // [3, 4, 5, 6, 7]
        List<Integer> list = slice.getList();
        System.out.println(list);
    }
}
