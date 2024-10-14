package cn.learn.juc.chapter3.escape;

/**
 * @author zengxuebin
 * @since 2024/10/13 19:23
 */
public class SomeEventSource implements EventSource {

    // 持有事件监听器
    private EventListener eventListener;

    @Override
    public void registerListener(EventListener listener) {
        this.eventListener = listener;
    }

    public void processEvent(Event event) {
        eventListener.onEvent(event);
    }
}
