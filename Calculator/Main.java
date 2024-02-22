package JDK.Lectures.Calculator;


import JDK.Lectures.Calculator.log.ConsoleLogger;
import JDK.Lectures.Calculator.log.LogCalculableFactory;
import JDK.Lectures.Calculator.view.ViewCalculator;

public class Main {
    public static void main(String[] args) {
        ICalculableFactory calculableFactory = new LogCalculableFactory(new ConsoleLogger());
        ViewCalculator view = new ViewCalculator(calculableFactory);
        view.run();
    }
}