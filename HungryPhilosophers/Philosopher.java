import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class Philosopher extends Thread {

    private String name;
    private int philosopherNum;
    private static int nextPhilosopherNumber = 1;
    private int lefFork;
    private int rightFork;
    private int countEat;
    private boolean eats;
    private boolean hungry;
    private CountDownLatch cdl;

    public Philosopher(String name, CountDownLatch cdl) {
        this.name = name;
        this.philosopherNum = nextPhilosopherNumber;
        nextPhilosopherNumber++;
        this.lefFork = philosopherNum;
        this.rightFork = philosopherNum + 1;
        this.countEat = 0;
        this.eats = false;
        this.hungry = true;
        this.cdl= cdl;
    }

    @Override
    public void run() {
        try {
            goToStart();
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goToStart() throws InterruptedException {
        System.out.println(name +" - Идет в столовую");
        Thread.sleep(1000 + new Random().nextInt(2000));
        System.out.println(name + " - Пришел в столовую");
        cdl.countDown();
    }

    void eat(Philosopher philosopher, Fork[] forks) {
        if(countEat<3){
            if(!forks[lefFork].isUsed() && !forks[rightFork].isUsed()){
                forks[lefFork].setUsed(true);
                forks[rightFork].setUsed(true);
                philosopher.setEats(true);
                System.out.println(philosopher.getName() + " приступил к еде, в левой руке вилка "
                + lefFork + " в правой " + rightFork);
                setCountEat(countEat+1);
                System.out.println(philosopher.getName() + " поел " + countEat + " раз ");
                if (countEat == 3){
                    setHungry(false);
                    System.out.println(philosopher.getName() + " Закончил трапезу!");
                }
            }
            // в противном случае ждем
        }
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

    @Override
    public String toString() {
        return "\nФилософ" +
                " №=" + philosopherNum +
                ", вилка слева=" + lefFork +
                ", вилка справа=" + rightFork +
                ", поел раз=" + countEat +
                ", кушает=" + eats +
                ", голоден=" + hungry;
    }
}
