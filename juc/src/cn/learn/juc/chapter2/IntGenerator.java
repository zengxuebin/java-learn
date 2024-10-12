package cn.learn.juc.chapter2;

/**
 * int生成器
 *
 * @author zengxuebin
 * @since 2024/10/11 22:50
 */
public abstract class IntGenerator {

    private volatile boolean canceled = false;

    public abstract int next();

    public boolean isCanceled() {
        return canceled;
    }

    public void cancel() {
        canceled = true;
    }

}
