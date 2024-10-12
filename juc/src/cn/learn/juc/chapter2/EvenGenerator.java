package cn.learn.juc.chapter2;

/**
 * 偶数生成器
 *
 * @author zengxuebin
 * @since 2024/10/11 23:56
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public int next() {
        currentEvenValue++;
        currentEvenValue++;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenGenerator evenGenerator = new EvenGenerator();
        EvenChecker.test(evenGenerator);
    }
}
