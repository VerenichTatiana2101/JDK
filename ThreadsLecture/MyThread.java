public class MyThread extends Thread{

    // без run thread не запустится
    @Override
    public void run() {
        //предполагает Runnable.run
        System.out.println("1. Hello from Thread" + Thread.currentThread());
    }
}
