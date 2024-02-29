import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private final String name;
    private static int nextPhilosopherNumber = 0;
    private final int leftFork, rightFork;
    private int countEat;
    private boolean hungry;
    private CountDownLatch cdl;
    private DinnerRoom dinnerRoom;

    public Philosopher(String name,CountDownLatch cdl, DinnerRoom dinnerRoom, int rightFork) {
        this.name = name;
        int philosopherNum = nextPhilosopherNumber;
        nextPhilosopherNumber++;
        this.leftFork = philosopherNum;
        this.rightFork = rightFork;
        this.countEat = 0;
        this.hungry = true;
        this.cdl = cdl;
        this.dinnerRoom = dinnerRoom;
    }

    @Override
    public void run() {
        try {
            goToStart();
            cdl.await();
            System.out.println("Кто успел, тот и съел, поехали!!!");
            sleep(50);
            while (hungry) {
                eat();
            }
            System.out.println(getPhilosopherName() + " Закончил трапезу!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goToStart() throws InterruptedException {
        System.out.println(name + " - Пришел в столовую");
        cdl.countDown();
    }

    void eat() throws InterruptedException {
        if (dinnerRoom.freeForks(leftFork, rightFork)) {
            sleep(500 + new Random().nextInt(2000));
            System.out.println(getPhilosopherName() + " приступил к еде, в левой руке вилка "
                    + leftFork + " в правой " + rightFork);
            countEat++;
            System.out.println(getPhilosopherName() + " поел " + countEat + " раз ");
            dinnerRoom.think(leftFork, rightFork);
            if (countEat == 3) {
                setHungry(false);
            }

        }
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public String getPhilosopherName() {
        return name;
    }

    @Override
    public String toString() {
        return "\n" + getPhilosopherName() +
                ", вилка слева=" + leftFork +
                ", вилка справа=" + rightFork +
                ", поел раз=" + countEat +
                ", голоден=" + hungry;
    }
}
