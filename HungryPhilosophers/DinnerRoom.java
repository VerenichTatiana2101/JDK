import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class DinnerRoom implements Runnable{
    private CountDownLatch count = new CountDownLatch(5);
    Fork[] forks = new Fork[5];
    Philosopher[] philosophers;


    public DinnerRoom() {
        initPhilosophers();
    }


    @Override
    public void run() {
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i].start();
        }
    }

    void initPhilosophers() {


        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(false);
        }

        System.out.println(Arrays.toString(forks));

        Philosopher platon = new Philosopher("Платон", count);
        Philosopher aristotel = new Philosopher("Аристотель", count);
        Philosopher sokrat = new Philosopher("Сократ", count);
        Philosopher konfucij = new Philosopher("Конфуций", count);
        Philosopher kant = new Philosopher("Кант", count);

        philosophers = new Philosopher[]{platon, aristotel, sokrat, konfucij, kant};
        System.out.println(Arrays.toString(philosophers));
    }


}
