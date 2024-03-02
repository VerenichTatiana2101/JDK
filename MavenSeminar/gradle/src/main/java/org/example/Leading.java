package org.example;

import java.util.Random;

public class Leading {
    Door[] doors = new Door[3];

    void initPlay() {
        for (int i = 0; i < doors.length; i++) {
            doors[i] = new Door(false);
        }
    }

    /**
     * Рандомно одной из двери setPresent() флаг меняю на правду, обозначая,
     * что за данной дверью подразумевается подарок
     * @return заполненный массив дверей для игры
     */
    Door[] initPutPresent() {
        Random random = new Random();
        int randomDoorIndex = random.nextInt(doors.length);
        doors[randomDoorIndex].setPresent(true);
        return doors;
    }

    /**
     * Метод, который создаёт новый массив дверей из двух элементов
     * первой записываем выбор игрока,
     * т.к. по условию мы должны открыть одну дверь без приза,
     * в первую очередь проверяем есть ли призовые,
     * если есть, ее и вписываем в новый массив вторым элементом,
     * если обе без приза вписываем любую
     * @param inCommand номер двери, выбранной пользователем
     * @return новый массив из двух дверей
     */
    Door[] openDoor(int inCommand) {
        Door[] twoDoors = new Door[2];
        int temp = 0;
        twoDoors[0] = doors[inCommand - 1];
        for (int i = 0; i < doors.length; i++) {
            if (inCommand - 1 != i && doors[i].isPresent()) {
                twoDoors[1] = doors[i];
            } 
            else if (inCommand - 1 != i) {
                temp = i;
            }
        }
        if(twoDoors[1] == null) twoDoors[1] = doors[temp];
        return twoDoors;
    }

    /**
     * Изначальный выбор двери в новый массив был помещен сразу на нулевую позицию,
     * при смене результата, присваиваю выбору индекс 1, если пользователь не меняет
     * свой выбор индекс 0, где и есть его изначальный выбор
     * @param arr массив из двух оставшихся дверей
     * @param inCommand решение пользователя менять или не менять свой выбор
     * @return Метод возвращает дверь результата игры в виде строки
     */
    String checkTheResults(Door[] arr, int inCommand) {
        if(inCommand == 0) return arr[0].toString();
        else return arr[1].toString();
    }


}
