package JDK.Lectures.tic_tac_toe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// создаём окно, которое будет всплывать поверх основного для начала новой игры
public class SettingsWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 250;

    JButton btnStart;

    SettingsWindow(GameWindow gameWindow){
        // создаём кнопку
        btnStart = new JButton("Start new game");

        // настраиваем второе окно относительно первого указывая положение
        setLocationRelativeTo(gameWindow);
        setLocation(getX() - WIDTH/2, getY() - HEIGHT/2); // настраиваем экран посередине
        setSize(WIDTH, HEIGHT);

        // единственная кнопка начала игры
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // слушатель делает окно невидимым
                //открывается при нажатии старт
                setVisible(false);
                // вызывается метод начала игры с размерами поля
                gameWindow.startNewGame(0, 3, 3, 3);
            }
        });
        // добавляет кнопку на окно
        add(btnStart);
    }
}