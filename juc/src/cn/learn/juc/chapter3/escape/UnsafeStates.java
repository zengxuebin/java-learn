package cn.learn.juc.chapter3.escape;

import cn.learn.juc.chapter3.immutable.SafeStates;

import java.util.Arrays;

/**
 * 如果按这个方式来发布states 就会出现问题 因为任何调用者都可以修改这个数组的内容
 * 在这个示例中 数组states已经逸出了它所在的作用域 因为这个本应私有的变量被发布了
 * <p>
 *     线程安全详见 {@link SafeStates}
 * </p>
 *
 * @author zengxuebin
 * @since 2024/10/13 18:51
 */
public class UnsafeStates {

    private String[] states = new String[] {"AK", "AL", "AR", "AZ"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafeStates unsafeStates = new UnsafeStates();
        unsafeStates.getStates()[0] = "MK";
        System.out.println(Arrays.toString(unsafeStates.getStates()));
    }
}
