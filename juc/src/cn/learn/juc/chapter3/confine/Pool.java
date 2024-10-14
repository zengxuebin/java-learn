package cn.learn.juc.chapter3.confine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 池化类
 *
 * @author zengxuebin
 * @since 2024/10/13 22:26
 */
public class Pool<T> {

    /**
     * 标识符默认大小
     */
    private int size;

    /**
     * 存储对象的池子
     */
    private List<T> items = new ArrayList<>();

    /**
     * 检出的标识-被占用
     */
    private volatile boolean[] checkedOut;

    private Semaphore available;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                items.add(classObject.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            if (!checkedOut[i]) {
                // 标识该对象已经被使用了
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    public void checkIn(T item) {
        if (releaseItem(item)) {
            available.release();
        }
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (index == -1) {
            return false;
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
