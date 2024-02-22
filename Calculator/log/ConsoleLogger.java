package JDK.Lectures.Calculator.log;

public class ConsoleLogger implements Loggable {

    @Override
    public void log(String info) {
        System.out.println("Log: "+ info);
    }

}
