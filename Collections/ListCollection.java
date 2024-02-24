import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * В рамках выполнения задачи необходимо:
 * Создайте коллекцию мужских и женских имен с помощью интерфейса List
 * Отсортируйте коллекцию в алфавитном порядке
 * Отсортируйте коллекцию по количеству букв в слове
 * Разверните коллекцию
 */
public class ListCollection {
    public static void main(String[] args) {
        List<String> list = createList();
        System.out.println("Исходный массив имён\n" + list);
        System.out.println("Сортировка по алфавиту\n" + sortByAlphabet(list));
        System.out.println("Сортировка по длине\n" + sortByLength(list));
        System.out.println("Обратный порядок\n" + reverceList(list));
    }

    static List<String> createList(){
        List<String> names = new ArrayList<>();
        names.add("Ирина");
        names.add("Анна");
        names.add("Татьяна");
        names.add("Олег");
        names.add("Кирилл");
        return names;
    }

    static List<String> sortByAlphabet(List<String> list) {
        //list.sort(null); //т.к у string реализован метод можно по умолчанию
        //------------------------------------------//
        /**
         *         list.sort(new Comparator<String>() {
         *             @Override
         *             public int compare(String o1, String o2) {
         *                 return 0;
         *             }
         *         });
         */
        //------------------lambda-------------------//
        //list.sort((e1, e2) -> e1.compareTo(e2)); //можно добавить e1.toLowerCase()
        list.sort(String::compareTo);
        return list;
    }

    static List<String> sortByLength(List<String> list) {
        //------------------через stream----------------//
        /**
         *         list.stream().sorted(new Comparator<String>() {
         *             @Override
         *             public int compare(String o1, String o2) {
         *                 return 0;
         *             }
         *         })
         */
        //------------------lambda-------------------//
        return list.stream().sorted(Comparator.comparingInt(String::length)).toList();
    }

    static List<String> reverceList(List<String> list) {
        return list.stream().sorted(Comparator.reverseOrder()).toList(); // т.к. мы во второй сортировке изменили сущ. коллекцию реверс по ней
    }


}
