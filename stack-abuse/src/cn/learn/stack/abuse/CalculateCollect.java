package cn.learn.stack.abuse;

import cn.learn.stack.abuse.bean.Book;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Calculate Distribution from Collection in Java
 * 从 Java 中的集合计算分布
 *
 * @author zengxuebin
 * @since 2024/11/22 08:51
 */
public class CalculateCollect {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 1, 2, 1, 2, 3, 1, 4, 5, 1, 3);
        System.out.println(calculateIntegerDistribution(integerList));

        Book book1 = new Book("001", "Our Mathematical Universe", "Max Tegmark", 432, 2014);
        Book book2 = new Book("002", "Life 3.0", "Max Tegmark", 280, 2017);
        Book book3 = new Book("003", "Sapiens", "Yuval Noah Harari", 443, 2011);
        Book book4 = new Book("004", "Steve Jobs", "Water Isaacson", 656, 2011);

        List<Book> books = Arrays.asList(book1, book2, book3, book4);
        System.out.println(calculateDistributionCount(books));
    }

    /**
     * The distribution is calculated with:
     * 分布计算公式为：
     * This method accepts a list and streams it. While streamed,
     * the values are grouped by their integer value - and their values are counted using Collectors.counting(),
     * before being collected into a Map<Integer, Double> where the keys represent the input values
     * and the doubles represent their percentages in the distribution.
     * 此方法接受一个列表并对其进行流式处理。在流式传输时，
     * 值按其整数值进行分组- 并使用Collectors.counting()对它们的值进行计数，
     * 然后将其收集到Map<Integer, Double>中，其中键代表输入值，双精度数代表它们在分布。
     *
     * @param list
     * @return 这里的关键方法是collect() ，它接受两个收集器。
     * 键收集器通过简单地按键值（输入元素）分组来收集。
     * 值收集器通过collectingAndThen()方法进行收集，该方法允许我们对值进行计数，
     * 然后将它们格式化为另一种格式，例如count * 100.00 / list.size()
     */
    public static Map<Integer, Double> calculateIntegerDistribution(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Integer::intValue,
                        Collectors.collectingAndThen(Collectors.counting(),
                                count -> (Double.parseDouble(String.format("%.2f", count * 100.00 / list.size()))))));
    }

    /**
     * Sort Distribution by Value or Key
     * 按值或键对分布进行排序
     * sort the values. More often than not, this will be by key.
     * Java HashMaps don't guarantee to preserve order of insertion, so we'll have to use a LinkedHashMap which does.
     * Additionally, it's easiest to re-stream the map and re-collect it now that it's a much smaller size and much more manageable.
     * 这将通过key进行。
     * Java HashMap不保证保留插入顺序，因此我们必须使用LinkedHashMap来保证插入顺序。
     * 此外，现在重新传输地图并重新收集它是最容易的，因为它的尺寸要小得多并且更易于管理。
     * @param list
     * @return The previous operation can quickly collapse multiple thousand records into small maps,
     * depending on the number of keys you're dealing with, so re-streaming isn't expensive:
     * 前面的操作可以快速将数千条记录折叠成小映射，
     * 具体取决于您正在处理的键的数量，因此重新流式传输并不昂贵
     */
    public static Map<Integer, Double> calculateIntegerDistributionAndSort(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Integer::intValue,
                        Collectors.collectingAndThen(Collectors.counting(),
                                count -> (Double.parseDouble(String.format("%.2f", count.doubleValue() / list.size())))))
                )
                // Stream the entries of the distribution map
                // to sort it and store in a LinkedHashMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(e -> Integer.parseInt(e.getKey().toString()),
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new)
                );
    }

    /**
     * Calculate the distribution of the publishedYear field
     * 计算publishedYear字段的分布
     *
     * @param books
     * @return
     */
    public static Map<Integer, Double> calculateDistribution(List<Book> books) {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getPublishedYear,
                        Collectors.collectingAndThen(Collectors.counting(),
                                count -> (Double.parseDouble(String.format("%.2f", count * 100.00 / books.size()))))))
                // Sort results by year
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(e -> Integer.parseInt(e.getKey().toString()),
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new));
    }

    /**
     * Calculate Normalized (Percentage) Distribution of Collection in Java
     * 在 Java 中计算集合的归一化（百分比）分布
     *
     * @param books
     * @return
     */
    public static Map<Integer, Double> calculateDistributionNormalized(List<Book> books) {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getPublishedYear,
                        // 要将百分比从0.0...100.0范围标准化为0..1范围 - 我们只需调整collectingAndThen()调用，
                        // 以便在除以集合大小之前不将计数乘以100.0 。我们需要显式获取count的doubleValue()
                        Collectors.collectingAndThen(Collectors.counting(),
                                count -> (Double.parseDouble(String.format("%.4f", count.doubleValue() / books.size()))))))
                // Sort results by key
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(e -> Integer.parseInt(e.getKey().toString()),
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new));
    }

    /**
     * Calculate Element Count (Frequency) of Collection
     * 计算集合的元素计数（频率）
     *
     * @param books
     * @return
     */
    public static Map<Integer, Integer> calculateDistributionCount(List<Book> books) {
        return books
                .stream()
                .collect(Collectors.groupingBy(Book::getPublishedYear,
                        Collectors.collectingAndThen(Collectors.counting(),
                                count -> (Integer.parseInt(String.format("%s", count.intValue()))))))
                // Sort values by key
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(e -> Integer.parseInt(e.getKey().toString()),
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new));
    }
}
