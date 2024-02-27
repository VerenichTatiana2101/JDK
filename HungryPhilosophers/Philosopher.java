import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class Philosopher extends Thread {
    private final String name;
    private static int nextPhilosopherNumber = 0;
    private final int leftFork, rightFork;
    private int countEat;
    private boolean eats;
    private boolean hungry;
    private CountDownLatch cdl;
    private DinnerRoom dinnerRoom;

    public Philosopher(String name, CountDownLatch cdl, DinnerRoom dinnerRoom, int rightFork) {
        this.name = name;
        int philosopherNum = nextPhilosopherNumber;
        nextPhilosopherNumber++;
        this.leftFork = philosopherNum;
        this.rightFork = rightFork;
        this.countEat = 0;
        this.eats = false;
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
            for (int i = 0; i <= 3; i++) {
                eat();
                System.out.println(getName() + " поел " + i + " раз ");
            }
            setHungry(false);
            System.out.println(getName() + " Закончили трапезу!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goToStart() throws InterruptedException {
        System.out.println(name + " - Идет в столовую");
        Thread.sleep(1000 + new Random().nextInt(2000));
        System.out.println(name + " - Пришел в столовую");
        cdl.countDown();
    }

    void eat() throws InterruptedException {
        //if(countEat<3){
        if (dinnerRoom.freeForks(leftFork, rightFork)) {
            setEats(true);
            System.out.println(getName() + " приступил к еде, в левой руке вилка "
                    + leftFork + " в правой " + rightFork);
            wait(200);
            //setCountEat(countEat+1);
            //System.out.println(philosopher.getName() + " поел " + countEat + " раз ");
                /*
                if (countEat == 3){
                    setHungry(false);
                    System.out.println(philosopher.getName() + " Закончил трапезу!");
                }*/
        }
        // в противном случае ждем
        else {
            try {
                wait(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // }
    }

    public void setEats(boolean eats) {
        this.eats = eats;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public void setCountEat(int countEat) {
        this.countEat = countEat;
    }

    public void setCdl(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public String getPhilosopherName() {
        return name;
    }

    @Override
    public String toString() {
        return "\n" + getName() +
                ", вилка слева=" + leftFork +
                ", вилка справа=" + rightFork +
                ", поел раз=" + countEat +
                ", кушает=" + eats +
                ", голоден=" + hungry;
    }
}
