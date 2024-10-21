package cn.learn.juc.chapter3.immutable;

import cn.learn.juc.chapter3.escape.UnsafeStates;

import java.util.Arrays;

/**
 * 与{@link UnsafeStates}相比，使用数组的浅拷贝，不会改变原数组的值。}
 *
 * @author zengxuebin
 * @since 2024/10/13 18:51
 */
public class SafeStates {

    private String[] states = new String[] {"AK", "AL", "AR", "AZ"};

    public String[] getStates() {
        return states.clone();
    }

    public static void main(String[] args) {
        SafeStates unsafeStates = new SafeStates();
        unsafeStates.getStates()[0] = "MK";
        System.out.println(Arrays.toString(unsafeStates.getStates()));
    }
}
