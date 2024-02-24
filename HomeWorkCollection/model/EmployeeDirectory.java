package model;

import exceptions.EmployeeNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeDirectory {
    private List<Employee> listEmployee = new ArrayList<>();

    /**
     * Метод добавление нового сотрудника в справочник
     */
    public void add(Employee employee) {
        listEmployee.add(employee);
    }

    /**
     * Метод, который ищет сотрудника по стажу
     * (список)
     *
     * @return сотрудника по стажу
     */
    public List<Employee> searchExperience(int age) {
        /*
        List<model.Employee> getForAge = new ArrayList<>();
        for (model.Employee employee : listEmployee) {
            if (age == employee.getExperience()) {
                getForAge.add(employee);
            }
        }
        return getForAge;
        */
        return listEmployee.stream().filter(e -> e.getExperience() == age).toList();
    }

    /**
     * Метод,
     * который возвращает номер телефона сотрудника по имени
     * (список)
     *
     * @return номера телефонов сотрудников по имени
     */
    public List<String> getNumberByName(String name) {
        /*
        List<String> phones = new ArrayList<>();
        for (model.Employee employee : listEmployee) {
            if (name != null && name.equals(employee.getName())) {
                phones.add("\n" + employee.getName() + " " + employee.getPhoneNumber());
            }
        }
        return phones;
         */
        List<Employee> resultFilter = listEmployee.stream().filter(x -> x.getName().equals(name)).toList();
        List<String> phones = new ArrayList<>();
        for (Employee employee : resultFilter) {
            phones.add("\n" + employee.getName() + " " + employee.getPhoneNumber());
        }
        return phones;
    }

    /**
     * Метод, который ищет сотрудника по
     * табельному номеру
     *
     * @return сотрудника по табельному номеру
     */
    public Employee searchByPersonNumber(int personNumber) throws EmployeeNotFoundException {
        List<Employee> resultFilter = listEmployee.stream().filter(e -> e.getPersonNumber() == personNumber).toList();
        for (Employee employee : resultFilter) {
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник с таким номером не найден");
    }

    @Override
    public String toString() {
        return "Справочник сотрудников" +
                listEmployee;
    }
}

