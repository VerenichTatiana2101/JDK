import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class DinnerRoom implements Runnable {
    Philosopher[] philosophers = new Philosopher[5];
    private final CountDownLatch cdl = new CountDownLatch(philosophers.length);
    Fork[] forks = new Fork[philosophers.length];
    private final String[] names = {
            "Платон",
            "Аристотель",
            "Сократ",
            "Конфуций",
            "Кант"
    };

    public DinnerRoom() {
        initPhilosophers();
    }

    @Override
    public void run() {
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }

    void initPhilosophers() {
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(false);
        }
        //System.out.println(Arrays.toString(forks));

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(names[i], cdl, this, (i + 1) % philosophers.length);
        }
        //System.out.println(Arrays.toString(philosophers));
    }

    synchronized boolean freeForks(int left, int right) {
        if (!forks[left].isUsed() && !forks[right].isUsed()) {
            forks[left].setUsed(true);
            forks[right].setUsed(true);
            return true;
        }
        return false;
    }


    public void think(int left, int right) throws InterruptedException {
        forks[left].setUsed(false);
        forks[right].setUsed(false);
        System.out.println("Вилки " + left + " и " + right + " свободны");
        Thread.sleep(100);
    }
}
