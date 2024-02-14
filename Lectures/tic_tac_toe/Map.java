package JDK.Lectures.tic_tac_toe;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    Map(){
        setBackground(Color.BLACK);
    }

    /**
     * Метод начинающий бой
     * @param mode режим игры(игрок-игрок, игрок-компьютер)
     * @param fSzX размер поля по х
     * @param fSzY размер поля по у
     * @param wLen выигрышная длина
     */
    void startNewGame(int mode, int fSzX, int fSzY, int wLen){
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nWinLength: %d",
                mode, fSzX, fSzY, wLen);
    }
}
