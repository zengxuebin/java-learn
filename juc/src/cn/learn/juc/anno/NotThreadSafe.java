package cn.learn.juc.anno;

import java.lang.annotation.*;

/**
 * 线程不安全
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
}
