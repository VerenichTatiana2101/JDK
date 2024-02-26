import java.util.Scanner;

public class StatsHelper {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread readThread = new Thread(() ->{
            Scanner in = new Scanner(System.in);
            while(in.hasNextLine() && !Thread.interrupted()){  //!Thread.interrupted добавили для прерывания потока
                in.nextLine();
                count++;
            }
        });
        readThread.setDaemon(true); //этот поток может быть сервисным, этот поток нужен только на период сущ программы
        readThread.start();
        // прервать поток
        readThread.interrupt();

        while (true){
            System.out.println("Вы ввели " + count + " сообщений ");
            Thread.sleep(2000);
        }
    }
}
