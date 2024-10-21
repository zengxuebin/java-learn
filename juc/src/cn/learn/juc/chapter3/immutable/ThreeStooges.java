package cn.learn.juc.chapter3.immutable;

import cn.learn.juc.anno.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zengxuebin
 * @since 2024/10/20 19:04
 */
@Immutable
public class ThreeStooges {

    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

}
