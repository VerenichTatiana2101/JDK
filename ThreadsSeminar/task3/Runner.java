package task3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Runner implements  Runnable{
    private String name;
    private CountDownLatch cdl;


    public Runner(String name, CountDownLatch cdl) {
        this.cdl = cdl;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            goToStart();
            cdl.await();
            running();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setCdl(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    private void goToStart() throws InterruptedException {
        System.out.println(name +" - Идет к старту");
        Thread.sleep(1000 + new Random().nextInt(2000));
        System.out.println(name + " - Подошёл к старту");
        cdl.countDown();

    }

    private void running() throws InterruptedException {
        System.out.println(name + " - Стартовал");
        Thread.sleep(1000 + new Random().nextInt(2000));
        System.out.println(name + " - Финишировал");

    }

    @Override
    public String toString() {
        return "Runner " + name + "\n";
    }
}
