package JDK.Lectures.Calculator.log;

import JDK.Lectures.Calculator.Calculable;
import JDK.Lectures.Calculator.ICalculableFactory;
import JDK.Lectures.Calculator.model.Calculator;

public class LogCalculableFactory implements ICalculableFactory {
    private Loggable logger;

    public LogCalculableFactory(Loggable logger) {
        this.logger = logger;
    }

    @Override
    public Calculable create(int primaryArg) {
        return new LogCalculator(new Calculator(primaryArg), logger);
    }

}