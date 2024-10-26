package cn.learn.juc.chapter5.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zengxuebin
 * @since 2024/10/26 00:04
 */
class Player implements Runnable {
    protected final CountDownLatch startSignal;

    Player(CountDownLatch latch) {
        startSignal = latch;
    }

    @Override
    public void run() {
        try {
            if (startSignal.getCount() == 0) {
                System.err.println("游戏人数已满……无法加入游戏");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "=>进入游戏，准备就绪");
            startSignal.await();
            // 所有人员都已经准备好之后，进行的操作
            play();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void play() {
        System.out.println("人数已凑够，开始游戏");
    }
}

class Game {
    /**
     * 游戏人数
     */
    private final int num;

    private final CountDownLatch startSignal;

    Game(int num) {
        this.num = num;
        // num表示将要调用num次countDown之后，await就可以放行了
        startSignal = new CountDownLatch(num);
    }

    void begin(int nPlayers) {
        for (int i = 0; i < nPlayers; i++) {
            new Thread(new Player(startSignal)).start();

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            startSignal.countDown();
        }
    }
}
public class GameLatch {

    public static void main(String[] args) {
        // 一局游戏两个人
        Game game = new Game(2);
        // 表示1个人已经准备开始游戏了
        game.begin(1);
        game.begin(1);
        game.begin(3);
    }

}
