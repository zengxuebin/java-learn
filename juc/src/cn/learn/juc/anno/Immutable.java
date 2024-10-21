package cn.learn.juc.anno;

import java.lang.annotation.*;

/**
 * 标记不可变
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Immutable {
}
