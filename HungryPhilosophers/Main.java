public class Main {
    public static void main(String[] args) {
        DinnerRoom dinnerRoom = new DinnerRoom();
        Thread thread = new Thread(dinnerRoom);
        thread.start();

    }
}
