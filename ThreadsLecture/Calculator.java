import java.util.*;
import java.util.concurrent.*;

public class Calculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Queue<Task> queue = new ArrayDeque<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Thread taskService = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    executor.submit(() -> {
                        Task task = queue.poll();
                        if (task != null) {
                            System.out.println("Task " + task + " started");
                            task.run();
                        }
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        taskService.start();
        while (in.hasNextLine()) {
            // 1+2
            try {
                int value = Integer.parseInt(in.nextLine().trim());
                Task task = new Task(value);
                queue.add(task);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
