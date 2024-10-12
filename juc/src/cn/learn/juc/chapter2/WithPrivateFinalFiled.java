package cn.learn.juc.chapter2;

import java.lang.reflect.Field;

/**
 * 探讨final字段能否被修改
 *
 * @author zengxuebin
 * @since 2024/10/12 00:12
 */
public class WithPrivateFinalFiled {

    private int i = 1;

    private final String str = "I'm final";

    private String s2 = "Am I safe?";

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        final WithPrivateFinalFiled w = new WithPrivateFinalFiled();
        System.out.println(w);
        Field field = w.getClass().getDeclaredField("i");
        field.setAccessible(true);
        field.setInt(w, 50);
        System.out.println(w);

        field = w.getClass().getDeclaredField("str");
        field.setAccessible(true);
        field.set(w, "Can I modify?");
        System.out.println(w);

        field = w.getClass().getDeclaredField("s2");
        field.setAccessible(true);
        field.set(w, "I'm not safe");
        System.out.println(w);
    }

    @Override
    public String toString() {
        return "WithPrivateFinalFiled{" +
                "i=" + i +
                ", str='" + str + '\'' +
                ", s2='" + s2 + '\'' +
                '}';
    }
}
