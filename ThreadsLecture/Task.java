import java.util.concurrent.CountDownLatch;

public class Task implements Runnable {

    private int value;
    private CountDownLatch cdl;

    public CountDownLatch getCdl() {
        return cdl;
    }

    public void setCdl(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public Task(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // в данном случае synchronized нам даёт полную гарантию, что значения все будут учтены
    /* аналогичный вариант
    public synchronized void inc() {
        value++;
    }
    */

    // таким образом работа немного быстрее
    public void inc() {
        synchronized (Task.class){
            value++;
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            inc();
        }
        cdl.countDown();
    }

    @Override
    public String toString() {
        return String.format("(%s)", value);

    }
}
