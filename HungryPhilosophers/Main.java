public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        DinnerRoom dinnerRoom = new DinnerRoom();
        Thread thread = new Thread(dinnerRoom);
        thread.start();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(duration);

    }
}
