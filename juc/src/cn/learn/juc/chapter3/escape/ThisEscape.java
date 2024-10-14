package cn.learn.juc.chapter3.escape;

import java.util.concurrent.TimeUnit;

/**
 * this逸出
 *
 * @author zengxuebin
 * @since 2024/10/13 19:18
 */
public class ThisEscape {

    /**
     * 用num标识初始化是否完成
     */
    private final  int num;
    
    public ThisEscape(EventSource source) {
        // ThisEscape没有构造完成，但是已经将事件构造成功
        // 在事件监听中，去调用了ThisEscape的示例方法——> this
        source.registerListener(event -> doSth());
        System.out.println("-------模拟初始化耗时-------");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        num = 33;
    }

    private void doSth() {
        if (num != 33) {
            System.out.println("如果构造完成的话 将不会输出这句话");
        }
    }
}
