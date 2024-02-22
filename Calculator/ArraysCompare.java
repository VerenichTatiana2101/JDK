import java.util.Arrays;
import java.util.Objects;

public class ArraysCompare {
    /**
     * Напишите обобщенный метод compareArrays(),
     * который принимает два массива и возвращает true, если они одинаковые,
     * и false в противном случае. Массивы могут быть любого типа данных,
     * но должны иметь одинаковую длину и содержать элементы одного типа по парно по индексам.
     * То есть тип элемента в первом массиве под нулевым индексом такой же как тип элемента
     * во втором массиве под нулевым индексом (и под всеми остальными индексами аналогично)
     */
    public static <E> boolean compareArrays(E[] arrayA, E[] arrayB) {
        if (arrayA == null) {
            throw new NullPointerException("arrayA");
        }
        if (arrayB == null) {
            throw new NullPointerException("arrayB");
        }
        if (arrayA.length != arrayB.length) {
            return false;
        }
        for (int i = 0; i < arrayA.length; ++i) {
            if (!Objects.equals(arrayA[i], arrayB[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Обобщённый метод compareArrays():\n");
        Integer[] intArray1 = {17, 4, 9, 4};
        Integer[] intArray2 = {17, 2, 9, 4};
        System.out.println("Integer arrays are equal: " + compareArrays(intArray1, intArray2));

        String[] stringArray1 = {"apple", "banana", "cat"};
        String[] stringArray2 = {"apple", "banana", "cherry"};
        System.out.println("String arrays are equal: " + compareArrays(stringArray1, stringArray2));

        Object[] objArray1 = {1, "cat", 20.5};
        Object[] objArray2 = {1, "cat", 20.5};
        System.out.println("Object arrays are equal: " + compareArrays(objArray1, objArray2));
    }
}
