/**
 * Написать класс Калькулятор (необобщенный),
 * который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract().
 * Параметры этих методов – два числа разного типа,
 * над которыми должна быть произведена операция.
 */
public class Calculator {
    public static void main(String[] args) {
        int a = 10;
        double b = 5.2;

        System.out.println("Сумма: " + sum(a, b));
        System.out.println("Произведение: " + multiply(a, b));
        System.out.println("Деление: " + divide(a, b));
        System.out.println("Вычитание: " + subtract(a, b));
    }
    public static <T extends Number> double sum(T num1, T num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static <T extends Number> double multiply(T num1, T num2) {
        return num1.doubleValue() * num2.doubleValue();
    }

    public static <T extends Number> double divide(T num1, T num2) {
        if (num2.doubleValue() != 0) {
            return num1.doubleValue() / num2.doubleValue();
        } else {
            throw new ArithmeticException("Ошибка: Деление на ноль не допускается.");
        }
    }
    public static <T extends Number> double subtract(T num1, T num2) {
        return num1.doubleValue() - num2.doubleValue();
    }


}