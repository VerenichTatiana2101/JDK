import exceptions.EmployeeNotFoundException;
import model.Employee;
import model.EmployeeDirectory;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws EmployeeNotFoundException {
        EmployeeDirectory directory = getEmployeeDirectory();

        System.out.println(directory);
        System.out.println("\nПоиск сотрудника по стажу" + directory.searchExperience(9));
        System.out.println("\nПоиск номера телефона сотрудника по имени" + directory.getNumberByName("Иван"));
        System.out.println("\nПоиск сотрудника по табельному номеру" + directory.searchByPersonNumber(2));
    }

    private static EmployeeDirectory getEmployeeDirectory() {
        Employee employee0 = new Employee(1, "369852", "Татьяна", 12);
        Employee employee1 = new Employee(3, "774452", "Ирина", 9);
        Employee employee2 = new Employee(2, "229852", "Анна", 7);
        Employee employee3 = new Employee(5, "7777852", "Иван", 9);
        Employee employee4 = new Employee(5, "5557852", "Иван", 3);

        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(employee0);
        directory.add(employee1);
        directory.add(employee2);
        directory.add(employee3);
        directory.add(employee4);
        return directory;
    }
}
