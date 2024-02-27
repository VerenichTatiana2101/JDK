package task1;

public class Main {
    /*
    В рамках выполнения задачи необходимо:
    Создать два классa ObjectA, ObjectB
    Реализовать класс в котором два потока
    при запуске провоцируют DeadLock для объектов ObjectA, ObjectB
     */
    public static void main(String[] args) {
        ObjectA objA = new ObjectA();
        ObjectB objB = new ObjectB();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objA) {
                    System.out.println("Объект А заблокирован, ожидаем объект B");
                    synchronized (objB){
                        System.out.println("Объект B заблокирован");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objB) {
                    System.out.println("Объект B заблокирован, ожидаем объект A");
                    synchronized (objA){
                        System.out.println("Объект A заблокирован");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }

}

