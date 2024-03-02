package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class UserView {
    Scanner scan = new Scanner(System.in);
    Door[] doors;
    Leading leading = new Leading();
    int inCommand;

    void startGame() {
        leading.initPlay();
        System.out.println(Arrays.toString(leading.initPutPresent()));
        inCommand = firstChoice();
        doors = leading.openDoor(inCommand);
        System.out.println(Arrays.toString(doors));
        int secondCh = secondChoice(inCommand);
        System.out.println(leading.checkTheResults(doors, secondCh));
    }

    int firstChoice() {
        System.out.println("Попробуйте угадать, за какой дверью приз:\n"
                + "1, 2 или 3?\n"
                + "0 - выйти\n");
        int inCommand = scan.nextInt();
        scan.nextLine();
        switch (inCommand) {
            case 1, 2, 3 -> {
                System.out.println("Вы ввели " + inCommand);
                leading.openDoor(inCommand);
            }
            case 0 -> System.exit(0);
            default -> System.out.println("Ошибка! Пожалуйста, введите число от 1 до 3.");
        }
        return inCommand;
    }


    int secondChoice(int choice) {
        while (true) {
            System.out.println("Может Вы желаете изменить свой выбор? (y/n)");
            System.out.println("Для выхода нажмите q");
            String answer = scan.nextLine().toLowerCase();
            switch (answer) {
                case "y" -> {
                    return 1;
                }
                case "n" -> {
                    return 0;
                }
                case "q" -> System.exit(0);
                default -> System.out.println("Ошибка! Пожалуйста, введите 'y' или 'n'.");

            }
        }
    }
}
