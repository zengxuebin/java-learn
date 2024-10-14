package cn.learn.juc.chapter3.escape;

/**
 * 事件源
 *
 * @author CaoLongHui
 * @since 2024/10/13 19:17
 */
public interface EventSource {

    void registerListener(EventListener listener);

}
