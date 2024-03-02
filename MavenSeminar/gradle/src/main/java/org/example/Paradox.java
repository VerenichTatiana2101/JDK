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

    private final Leading leading = new Leading();
    Random rand = new Random();
    private Door[] doors;
    private Map<Integer, String> resultStrings = new HashMap<>();
    private Door result;
    private int firstChoice;
    private int secondChoice;
    private final int COUNT_ITERATIONS = 1000;

    Map<Integer, String> gameLoop() {
        for (int i = 0; i <= COUNT_ITERATIONS; i++) {
            resultStrings.put(i, startGame());
        }
        return resultStrings;
    }

    String startGame() {
        leading.initPlay();
        leading.initPutPresent();
        firstChoice = firstChoice();
        doors = leading.openDoor(firstChoice);
        secondChoice = secondChoice();
        result = leading.checkTheResults(doors, secondChoice);
        return addToFile(firstChoice, secondChoice, result);
    }

    int firstChoice() {
        return rand.nextInt(1, 3);
    }

    int secondChoice() {
        return rand.nextInt(2);
    }

    String addToFile(int first, int second, Door res) {
        if (first != second) {
            if (res.isPresent()) return ("Игрок менял выбор, результат - WON " + second + "\n");
            else return ("Игрок менял выбор, результат - LOST " + second + "\n");
        } else {
            if (res.isPresent()) return ("Игрок не менял выбор, результат - LOST " + second + "\n");
            else return ("Игрок не менял выбор, результат - WON " + second + "\n");
        }
    }

    String calculateStatistics() {
        Map<Integer, String> gameResult = gameLoop();
        int totalGames = gameResult.size();

        int wonGames = 0;
        int lostGames = 0;

        int changedChoiceWonGames = 0;
        int changedChoiceLostGames = 0;
        int unchangedChoiceWonGames = 0;
        int unchangedChoiceLostGames = 0;

        for (String result : gameResult.values()) {
            if (result.contains("WON") && result.contains("1")) {
                changedChoiceWonGames++;
                wonGames++;
            } else if (result.contains("LOST") && result.contains("1")) {
                changedChoiceLostGames++;
                lostGames++;
            } else if (result.contains("WON") && result.contains("0")) {
                unchangedChoiceWonGames++;
                wonGames++;
            } else if (result.contains("LOST") && result.contains("0")) {
                unchangedChoiceLostGames++;
                lostGames++;
            }
        }

        double winPercentage = (double) wonGames / totalGames * 100;
        double lostPercentage = (double) lostGames / totalGames * 100;

        double changedChoiceWinPercentage = (double) changedChoiceWonGames
                / (changedChoiceWonGames + changedChoiceLostGames) * 100;
        double unchangedChoiceWinPercentage = (double) unchangedChoiceWonGames
                / (unchangedChoiceWonGames + unchangedChoiceLostGames) * 100;

        return String.format("Общий процент выигрышей: %.2f%%\n" +
                        "Общий процент проигрышей: %.2f%%\n" +
                        "Процент выигрышей при изменении выбора: %.2f%%\n" +
                        "Процент выигрышей при неизменном выборе: %.2f%%\n",
                winPercentage, lostPercentage, changedChoiceWinPercentage, unchangedChoiceWinPercentage);
    }
}


