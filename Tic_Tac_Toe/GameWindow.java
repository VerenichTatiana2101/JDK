package JDK.Lectures.tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int WIDTH = 555;
    private static final int HEIGHT = 507;

    JButton btnStart, btnExit;
    SettingsWindow settingWindow;
    Map map;

    GameWindow(){
        // чтобы при закрытии окна закрывалось приложение
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // устанавливаем размер
        setSize(WIDTH, HEIGHT);
        // объект не располагается относительно чего-либо, появляется по центру
        setLocationRelativeTo(null);
        // название в шапке окна
        setTitle("TicTacToe");
        // не даёт пользователю менять размер, чтобы не сломать расположение элементов
        setResizable(false);

        // кнопки
        btnStart = new JButton("New Game");
        btnExit = new JButton("Exit");
        // подключаем всплывающее окно
        settingWindow = new SettingsWindow(this);
        map = new Map();

        // в кнопке выхода делаем просто exit
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // при нажатии включаем видимость всплывающего окна
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingWindow.setVisible(true);
            }
        });

        // располыгаем кнопки относительно нашего окна в блоке из двух колонок одной строки грида
        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);

        // используем BorderLayout нижнюю часть экрана
        add(panBottom, BorderLayout.SOUTH);
        add(map);
        // делаем объект видимым, тк изначально он невидим
        setVisible(true);
    }

    // метод старт гейм который вызывается из окна настроек и передаёт нам данные 0,3,3,3 и далее передаём из в мар
    void startNewGame(int mode, int sizeX, int sizeY, int winLen){
        map.startNewGame(mode, sizeX, sizeY, winLen);
    }
}