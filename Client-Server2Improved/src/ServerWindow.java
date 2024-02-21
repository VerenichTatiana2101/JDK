package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;



public class ServerWindow extends JFrame {
    // размеры окна
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    // путь к файлу для сохранения истории
    public static final String LOG_PATH = "src/server/history.txt";

    // Список клиентов
    List<ClientGui> clientGUIList;

    // кнопкиgit
    JButton btnStart, btnStop;
    JTextArea log;
    // флаг работает ли приложение
    boolean work;

    public ServerWindow(){
        // инициализация списка клиентов
        clientGUIList = new ArrayList<>();
        // закрытие при нажатии на Х
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // устанавливаем размер
        setSize(WIDTH, HEIGHT);
        // нельзя менять размер окон
        setResizable(false);
        // название
        setTitle("Chat server");
        //расположение не относительно чего, по умолчанию центр
        setLocationRelativeTo(null);
        // создание визуала
        createPanel();
        // видимость
        setVisible(true);
    }

    /**
     * @param clientGUI клиент
     * @return если сервер работает, клиент добавляется, если нет ложь
     */
    public boolean connectUser(ClientGui clientGUI){
        if (!work){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }

    // метод для получения истории переписки
    public String getLog() {
        return readLog();
    }

    /**
     * Метод, который отключает пользователя от сервера
     * @param clientGUI клиент
     */
    public void disconnectUser(ClientGui clientGUI){
        clientGUIList.remove(clientGUI);
        if (clientGUI != null){
            // оповещение клиента, что его отключили
            clientGUI.disconnectFromServer();
        }
    }

    /**
     * Метод отправки сообщения
     * @param text текст сообщения
     */
    public void message(String text){
        // проверка
        if (!work){
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    /**
     * Метод перебирает всех клиентов для рассылки сообщения
     * @param text текст сообщения
     */
    private void answerAll(String text){
        for (ClientGui clientGUI: clientGUIList){
            clientGUI.answer(text);
        }
    }

    /**
     * Метод сохраняющий сообщение в историю
     * @param text текст сообщения
     */
    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Метод чтения истории переписки
     * посимвольное чтение
     * @return история переписки, собранная StringBuilder-ом
     */
    private String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        // создали панельку сетка 1на 2
        JPanel panel = new JPanel(new GridLayout(1, 2));
        // добавили кнопки
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        // добавили слушателя на кнопку
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (work){
                    appendLog("Сервер уже был запущен");
                } else {
                    work = true;
                    appendLog("Сервер запущен!");
                }
            }
        });

        // добавили слушателя на кнопку
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!work){
                    appendLog("Сервер уже был остановлен");
                } else {
                    work = false;
                    // отключение всех пользователей, вынести в отдельный метод
                    while (!clientGUIList.isEmpty()){
                        disconnectUser(clientGUIList.get(clientGUIList.size()-1));
                    }
                    appendLog("Сервер остановлен!");
                }
            }
        });

        // добавили кнопки на панель
        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
}