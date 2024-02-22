/**
 * Напишите обобщенный класс Pair,
 * который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond()
 * для получения значений каждого из составляющих пары,
 * а также переопределение метода toString(), возвращающее строковое представление пары.
 */
public class Pair<E, V> {

    public static void main(String[] args) {
        Pair pair = new Pair("one", 25);
        System.out.println(pair);
    }
    private final E first;
    private final V second;
    public Pair(E first, V second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
