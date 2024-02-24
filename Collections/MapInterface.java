import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Задание 3
 * В рамках выполнения задачи необходимо:
 * ● Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение
 * ● Найдите человека с самым маленьким номером телефона
 * ● Найдите номер телефона человека чье имя самое большое в алфавитном порядке
 */
public class MapInterface {
    public static void main(String[] args) {
        Map<String, String> phones = createBook();
        System.out.println("Полный список телефонных номеров\n" + phones);
        System.out.println("Человек с самым маленьким номером телефона\n" + minNumberHuman(phones));
        System.out.println("Номер телефона человека чье имя самое большое в алфавитном порядке\n" + maxNameNumber(phones));
    }

    static Map<String, String> createBook() {
        return Map.of(
                "295975456", "Татьяна",
                "445559885", "Олег",
                "889518558", "Ирина");
    }
    static String minNumberHuman(Map<String, String> phones) {
        /**
         * String key = phones.keySet().stream().min(Comparator.comparingInt(Integer::parseInt)).orElse("null");
         *         return phones.get(key);
         */
        return
                phones.entrySet().stream().min
                                ((p1, p2) -> Integer.parseInt(p1.getKey()) - Integer.parseInt(p2.getKey()))
                        .get().getValue();
    }
    static String maxNameNumber(Map<String, String> phones) {
        /**
         * return
         *                 phones.entrySet().stream().max
         *                                 ((p1, p2) -> p1.getValue().compareTo(p2.getValue()))
         *                         .get().getKey();
         */
        return phones.entrySet().stream().max
                                (Comparator.comparing(Map.Entry::getValue)).get().getKey();
    }
}
