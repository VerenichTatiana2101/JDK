package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGui extends JFrame {
    // параметры окна
    public static final int WIDTH = 350;
    public static final int HEIGHT = 300;

    // ссылка на объект сервера
    private ServerWindow server;
    // флаг подключения
    private boolean connected;
    // имя клиента, то, чем будут подписаны сообщения
    private String name;

    // виджеты
    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    /**
     * Метод наполнения окна клиента
     * @param server окно сервера
     */
    public ClientGui(ServerWindow server){
        this.server = server;

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        // расположение слева от окна сервера
        setLocation(server.getX() - 500, server.getY());
        // создание визуальной часты
        createPanel();

        setVisible(true);
    }

    /**
     * Метод, отправляющий ответ от сервера
     * @param text текст сообщения клиента
     */
    public void answer(String text){
        appendLog(text);
    }

    /**
     * Метод подключения клиента к серверу,
     * работает с кнопкой подключения
     */
    private void connectToServer() {
        // метод сервера со ссылкой на себя, клиента
        // this - ссылка на текущий объект в рамках
        // которого мы сейчас пишем код, добавляет в список
        if (server.connectUser(this)){
            appendLog("Вы успешно подключились!\n");
            // скрываем панель авторизации
            headerPanel.setVisible(false);
            connected = true;
            // имя сохраняем то, которое было указано в качестве логина
            name = tfLogin.getText();
            // попытка получить историю переписки
            String log = server.getLog();
            if (log != null){
                appendLog(log);
            }
        } else {
            appendLog("Подключение не удалось");
        }
    }

    /**
     * Метод отключения от сервера, переделать
     */
    public void disconnectFromServer() {
        if (connected) {
            // делаем панель для нового подключения видимой
            headerPanel.setVisible(true);
            connected = false;
            server.disconnectUser(this);
            appendLog("Вы были отключены от сервера!");
        }
    }

    /**
     * Метод, который срабатывает при нажатии на кнопку отправить сообщение
     *
     */
    public void message(){
        if (connected){
            String text = tfMessage.getText();
            if (!text.isEmpty()){
                server.message(name + ": " + text);
                // очищаем поле после отправки сообщения
                tfMessage.setText("");
            }
        } else {
            appendLog("Нет подключения к серверу");
        }

    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    // создание панелей,
    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH); // сверху
        add(createLog()); // центр
        add(createFooter(), BorderLayout.SOUTH); // снизу
    }

    private Component createHeaderPanel(){
        // заполнение значениями по умолчанию
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    // центральная панель
    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        // возможность прокрутки при большом количестве сообщений
        return new JScrollPane(log);
    }

    // нижняя часть панели
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        // клавиша переноса строки или enter, отправка сообщения
        tfMessage.addKeyListener(new KeyAdapter() {
            // подключение слушателя клавиатуры полю для набора текста
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    message();
                }
            }
        });
        // то же самое при нажатии на клавишу send
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    // закрытие окна на крестик
    @Override
    protected void processWindowEvent(WindowEvent e) {
        // при закрытии окна вызываем метод отключения от сервера
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectFromServer();
        }
        // на случай чего оставляем изначальный метод тоже
        super.processWindowEvent(e);
    }
}
