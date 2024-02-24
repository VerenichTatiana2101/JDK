package model;

/**
 * Создать класс справочник сотрудников, который
 * содержит внутри коллекцию сотрудников - каждый
 * сотрудник должен иметь следующие атрибуты:
 * ○ Табельный номер
 * ○ Номер телефона
 * ○ Имя
 * ○ Стаж
 */
public class Employee {
    private int personNumber;
    private String phoneNumber;
    private String name;
    private int experience;


    public Employee(int personNumber, String phoneNumber, String name, int experience) {
        this.personNumber = personNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\nСотрудник: " +
                " Табельный номер=" + personNumber +
                ", Номер телефона=" + phoneNumber + '\'' +
                ", Имя='" + name + '\'' +
                ", Стаж=" + experience;
    }

}
