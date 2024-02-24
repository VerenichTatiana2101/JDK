import java.util.*;

/**
 * Задание 2
 * В рамках выполнения задачи необходимо:
 * ● Создайте коллекцию мужских и женских имен с помощью интерфейса List -
 * добавьте повторяющиеся значения
 * ● Получите уникальный список Set на основании List
 * ● Определите наименьший элемент (алфавитный порядок)
 * ● Определите наибольший элемент (по количеству букв в слове, но в обратном порядке)
 * ● Удалите все элементы содержащие букву ‘A’
 */
public class SetCollection {
    public static void main(String[] args) {
        List<String> list = createList();
        System.out.println("Исходный массив имён\n" + list);
        System.out.println("Множество set имён\n" + hashSet(list));
        System.out.println("Наименьший элемент (алфавитный порядок)\n"
                + firstInSortByAlphabet(hashSet(list)));
        System.out.println("Наибольший элемент (по количеству букв в слове, но в обратном порядке)\n"
                + maxElementRev(hashSet(list)));
        System.out.println("Удалите все элементы содержащие букву ‘A’\n"
                + deleteNamesWithA(hashSet(list)));
        System.out.println("Исходный hashSet массив имён\n" + hashSet(list));
    }

    static List<String> createList() {
        List<String> names = new ArrayList<>();
        names.add("Ирина");
        names.add("Анна");
        names.add("Анна");
        names.add("Татьяна");
        names.add("Олег");
        names.add("Кирилл");
        names.add("Олег");
        names.add("Олег");
        return names;
    }

    private static Set<String> hashSet(List<String> list) {
        return new HashSet<>(list);
    }

    static String firstInSortByAlphabet(Set<String> list) {
        TreeSet<String> tree = new TreeSet<>(list);
        //return tree.first();
        return tree.stream().findFirst().orElse("пусто");
    }

    static String maxElementRev(Set<String> list) {
        TreeSet<String> tree = new TreeSet<>(list);
        return tree.stream().max((e1, e2) -> e1.length() - e2.length()).orElse("Пусто");
    }

    static Set<String> deleteNamesWithA(Set<String> strings) {
        /**
         * Iterator<String> name = strings.iterator();
         *         while (name.hasNext()){
         *             if(name.next().contains("А")) name.remove();
         *         }
         *         return strings;
         */
        strings.removeIf(s -> s.contains("А"));
        return strings;
    }
}
