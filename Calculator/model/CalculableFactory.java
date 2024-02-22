package JDK.Lectures.Calculator.model;

import JDK.Lectures.Calculator.Calculable;
import JDK.Lectures.Calculator.ICalculableFactory;
import JDK.Lectures.Calculator.model.Calculator;

public class CalculableFactory implements ICalculableFactory {
    public Calculable create(int primaryArg) {
        return new Calculator(primaryArg);
    }
}