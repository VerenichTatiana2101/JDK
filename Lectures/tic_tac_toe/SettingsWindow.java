package JDK.Lectures.tic_tac_toe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// стартовое окно
public class SettingsWindow extends JFrame {
    //высота окна
    private static  final  int WINDOW_HEIGHT = 230;
    // ширина окна
    private static  final  int WINDOW_WIDTH = 350;

    JButton btnStart = new JButton("Start New Game");

    // нужно основное окно чтобы отцентрировать стартовое относительно основного
    SettingsWindow(GameWindow gameWindow){
        // добавляем параметры окну
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(0, 3, 3, 3);
                setVisible(false);
            }
        });
        // дбавление компонета
        add(btnStart);
    }
}
