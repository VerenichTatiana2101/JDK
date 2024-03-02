package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Данный класс будет заниматься считыванием записей игры
 * для дальнейших расчётов
 * для того, чтобы убедиться в верности парадокса Монте Холла
 */
public class Paradox {
    Leading leading = new Leading();
    Random rand = new Random();
    Door[] doors;
    Map<String, String> resulStrings = new HashMap<>();
    String result;
    int inCommand;
    int secondCh;

    void oho(){
        for (int i = 0; i < 1000; i++) {
            startGame();
        }
        for (int i = 0; i < resulStrings.size(); i++) {
            System.out.println(i);
        }

    }

    void startGame() {
        leading.initPlay();
        leading.initPutPresent();
        inCommand = firstChoice();
        doors = leading.openDoor(inCommand);
        secondCh = secondChoice(inCommand);
        result = leading.checkTheResults(doors, secondCh);
        addToFile(inCommand, secondCh, result, resulStrings);
    }

    int firstChoice() {
        int n = rand.nextInt(3);
        n += 1;
        return n;
    }

    int secondChoice(int choice) {
        choice = rand.nextInt(2);
        return choice;
    }

    Map<String, String> addToFile(int first, int second, String res, Map<String, String> resultMap){

        if(first != second)resultMap.put("Игрок менял выбор, результат - ", res);
        else resultMap.put("Игрок не менял выбор, результат - ", res);
        return resultMap;
    }
}
