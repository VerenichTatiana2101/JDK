package JDK.Lectures.tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//основное окно
public class GameWindow extends JFrame {
    //высота окна
    private static  final  int WINDOW_HEIGHT = 555;
    // ширина окна
    private static  final  int WINDOW_WIDTH = 507;
    // положение окна по оси Х
    private static  final  int WINDOW_POSX = 800;
    // положение окна по оси У
    private static  final  int WINDOW_POSY = 300;
    //добавим кнопку старт
    JButton btnStart = new JButton("New Game");
    //кнопка закрыть
    JButton btnExit = new JButton("Exit");
    Map map;
    SettingsWindow settings;

    GameWindow(){
        // для того, чтобы при нажатии на крестик
        // программа завершала свою работу
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // добавляем параметры окну
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // добавим название
        setTitle("Tic-Tac-Toe");
        // запрещаем пользователю менять размер
        // чтобы ничего не поломалось
        setResizable(false);
        map = new Map();
        settings = new SettingsWindow(this);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });

        //добавляем кнопки в окно
        JPanel panButton = new JPanel(new GridLayout(1, 2));
        panButton.add(btnStart);
        panButton.add(btnExit);
        add(panButton, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
//        settings.setVisible(true);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen){
        map.startNewGame(mode, fSzX, fSzY, wLen);
    }
}