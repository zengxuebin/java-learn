package cn.learn.juc.chapter1;

/**
 * 获取cpu核心数
 *
 * @author zengxuebin
 * @since 2024/10/11 21:29
 */
public class GetCoreNum {
    public static void main(String[] args) {
        System.out.println("逻辑处理器==>" + Runtime.getRuntime().availableProcessors());
    }
}
