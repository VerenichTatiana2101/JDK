package task3;
/*
В рамках выполнения задачи необходимо:
3 бегуна должны прийти к старту гонки
Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
Программа должна отсчитать “На старт”, “Внимание”, “Марш”
Программа должна завершиться когда все участники закончат гонку.
Подумайте об использовании примитива синхронизации
 */

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(4);

        Thread t1 = new Thread(new Runner("Runner 1", count));
        Thread t2 = new Thread(new Runner("Runner 2", count));
        Thread t3 = new Thread(new Runner("Runner 3", count));

        t1.start();
        t2.start();
        t3.start();

        while (count.getCount() > 1){
            Thread.sleep(100);
        }

        System.out.println("На старт");
        Thread.sleep(500);
        System.out.println("Внимание");
        Thread.sleep(500);
        System.out.println("Марш");
        Thread.sleep(500);

        count.countDown();

    }
}
