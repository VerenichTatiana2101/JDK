package JDK.Lectures.Calculator.log;

import JDK.Lectures.Calculator.Calculable;
import JDK.Lectures.Calculator.model.Calculator;

public class LogCalculator implements Calculable {
    private Calculable decoratedCalculator;
    private Loggable logger;

    public LogCalculator(Calculator calculator, Loggable logger) {
        decoratedCalculator = calculator;
        this.logger = logger;
    }

    @Override
    public Calculable sum(int arg) {
        int firstArg = decoratedCalculator.getResult();
        logger.log("First digit: " + firstArg);
        Calculable sum = decoratedCalculator.sum(arg);
        logger.log("Second digit: " + arg);
        logger.log("Sum result: " + sum.getResult());
        return sum;
    }

    @Override
    public Calculable multi(int arg) {
        int firstArg = decoratedCalculator.getResult();
        logger.log("First digit: " + firstArg);
        Calculable mult = decoratedCalculator.multi(arg);
        logger.log("Second digit: " + arg);
        logger.log("Multiplication result: " + mult.getResult());
        return mult;
    }

    @Override
    public int getResult() {
        int res = decoratedCalculator.getResult();
        logger.log("result: " + res);
        return res;
    }

}
