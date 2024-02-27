import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class DinnerRoom implements Runnable {
    private final CountDownLatch cdl = new CountDownLatch(5);
    Fork[] forks = new Fork[5];
    Philosopher[] philosophers = new Philosopher[5];
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
        System.out.println(Arrays.toString(forks));

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(names[i], cdl, this, (i + 1) % philosophers.length);
        }
        System.out.println(Arrays.toString(philosophers));
    }

    boolean freeForks(int left, int right) {
        if (!forks[left].isUsed() && !forks[right].isUsed()) {
            forks[left].setUsed(true);
            forks[right].setUsed(true);
            return true;
        }
        return false;
    }


}
