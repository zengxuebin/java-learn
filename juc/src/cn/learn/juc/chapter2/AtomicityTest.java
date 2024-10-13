package cn.learn.juc.chapter2;

/**
 *
 *
 * @author zengxuebin
 * @since 2024/10/12 23:43
 */
public class AtomicityTest implements Runnable {

    private int i;

    /**
     * 虽然return i是原子操作，但是缺少了同步使得其数值可以在处于不稳定的中间状态时被读取
     *
     * @return
     */
    public int getI() {
        return i;
    }

    public synchronized void increment() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            increment();
        }
    }

    public static void main(String[] args) {
        AtomicityTest test = new AtomicityTest();
        new Thread(test).start();
        while (true) {
            int temp = test.getI();
            if (temp % 2 != 0) {
                System.out.println(temp);
                System.exit(0);
            }
        }
    }
}
