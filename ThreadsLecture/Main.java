import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World");
        System.out.println(Thread.currentThread().getName());
        
        //----------------создание потоков-----------------//
        /* начало
        for (int i = 0; i < 3; i++) {
            //запуск потока start, нет гарантий порядка
            MyThread thread = new MyThread();
            thread.start();
            thread.join(); // не завершив первый поток не идет дальше, выставляет асинхрон
        }

        for (int i = 0; i < 5; i++) {
            //стандартный Thread с костыльным runnable
            Thread thread = new Thread(new MyRunnable());
            thread.start();
            thread.join();
        }

        //-------через лямбду---------//

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("3. Hello from Thread" + Thread.currentThread());
            }).start();

        }
        конец*/


        //-----------параллельная работа двух потоков-----------//

        Thread tic = new Thread(new TicTac("["));
        Thread tac = new Thread(new TicTac("]"));
        //tic.setDaemon(true); //тут нельзя делать сервисные треды
        //tac.setDaemon(true); //будет рассинхрон
        tic.start();
        tac.start();


        /* начало
        Task task = new Task(0);
        // примитив синхронизации, который позволяет ждать на какой-то строчке,
        // пока не выполнятся какие-то процессы
        // в нашем случае мы стартуем потоки и по окончанию ждём остальных
        CountDownLatch cdl = new CountDownLatch(3);
        task.setCdl(cdl);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(task);
            thread.start();
            //thread.join(); // не стоит
        }
        cdl.await(); //ждет
        System.out.println(task.getValue());
        конец */

    }
}
