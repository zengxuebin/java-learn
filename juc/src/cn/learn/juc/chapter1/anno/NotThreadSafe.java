package cn.learn.juc.chapter1.anno;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
}
