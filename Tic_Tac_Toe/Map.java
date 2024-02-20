package JDK.Lectures.tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

// объект отвечает за игру, игровое поле
public class Map extends JPanel {
    private static final Random RANDOM = new Random();
    // _DOT обозначение кем занята кнопка для работы в массиве
    private static final int HUMAN_DOT = 1;
    private static final int AI_DOT = 2;
    private static final int EMPTY_DOT = 0;

    // внутренниий отступ клетки для фиуры внетри нее
    private static final int PADDING = 10;

    // статус игры
    private static final int STATE_GAME = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final int STATE_DRAW = 3;

    // сообщение по окончанию игры
    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";

    // стадия игры
    private int gameStateType;

    // размеры поля и кол-во ячеек
    private int width, height, cellWidth, cellHeight;

    // режим(чел-чел, комп-чел), длина сторон и длина выигрышной позции
    private int mode, fieldSizeX, fieldSizeY, winLen;

    // массив-основа для рисования игрового поля
    private int[][] field;

    // флаг игра в процессе
    private boolean gameWork;

    Map() {
        // фон игровой панли
        setBackground(Color.WHITE);
        // слушатель кнопки, мыши
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (gameWork) {
                    // вызывает один метод щёлканья мышью, если gameWork true
                    update(e);
                }
            }
        });
    }

    // обнуляет игру создавая новый массив поля
    private void initMap() {
        field = new int[fieldSizeY][fieldSizeX];
    }

    // метод начала игры, который вызывается из гейм винд с парамтрами 0,3,3,3
    void startNewGame(int mode, int sizeX, int sizeY, int winLen) {
        this.mode = mode;
        this.fieldSizeX = sizeX;
        this.fieldSizeY = sizeY;
        this.winLen = winLen;
        // обнуляем поле
        initMap();
        // обновляем статус
        gameWork = true;
        gameStateType = STATE_GAME;
        // перерисовываем поле, вызывает paintComponent, сразу после инициализации
        repaint();
    }

    // метод вызывается при щелчке мыши. в него передаётся действие мыши
    private void update(MouseEvent mouseEvent) {
        // извлекаем координаты, где произошёл щелчек относитльно панели
        int x = mouseEvent.getX() / cellWidth; // поделив, определим в какую именно ячейку попал щелчёк
        int y = mouseEvent.getY() / cellHeight;
        // если ячейка пуста и клик в рамках поля, идем дальше
        if (!isValidCell(x, y) || !isEmptyCell(x, y)) {
            return;
        }
        // вписываем ход игрока
        field[y][x] = HUMAN_DOT;
        // проверка, является ли ход выигрышным, если результат true метод завершен
        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) {
            return;
        }
        // ход компьютра
        aiTurn();
        // прорисовка, т.к комп делает ход очень быстро не имеет смысла делать два раза
        repaint();
        // проверка, является ли ход выигрышным, если результат true метод завершен
        checkEndGame(AI_DOT, STATE_WIN_AI);
    }

    // вывод значений массива в данный момент, не используется
    private void testBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println();
    }

    // проверка клика на валидность
    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    // проверка свободна ли ячейка для хода
    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    // рандомный ход компьютера
    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
            // цикл попадания в пустую ячейку, данный момент стоит обдумать и улучшить
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }

    // метод проверяет пусты ли ячейки, если есть пустые фальш
    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    // метод проверяет закончена ли игра
    // проверка, является ли ход выигрышным, если результат true метод завершен
    private boolean checkEndGame(int dot, int gameOverType) {
        // если победа есть меняем состояние, делаем перерисовку возвращаем тру, игра закончена
        if (checkWin(dot)) {
            this.gameStateType = gameOverType;
            repaint();
            return true;
            // если поле заполнено меняем состояние и
            //перерисовываем поле возвращаем тру игра закончена
        } else if (isMapFull()) {
            this.gameStateType = STATE_DRAW;
            repaint();
            return true;
        }
        return false;
    }

    // проверяет ячейки на выигрыш
    // от исходной ячейки строим линию в длину winLen ячеек
    private boolean checkWin(int dot) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                // горизонталь, вертикаль и две диагонали
                if (checkLine(i, j, 1, 0, winLen, dot)) return true;
                if (checkLine(i, j, 1, 1, winLen, dot)) return true;
                if (checkLine(i, j, 0, 1, winLen, dot)) return true;
                if (checkLine(i, j, 1, -1, winLen, dot)) return true;
            }
        }
        return false;
    }

    // от исходной ячейки строим линию в длину winLen ячеек реализация
    private boolean checkLine(int x, int y, int vx, int vy, int len, int dot) {
        int far_x = x + (len - 1) * vx;
        int far_y = y + (len - 1) * vy;
        if (!isValidCell(far_x, far_y)) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (field[y + i * vy][x + i * vx] != dot) {
                return false;
            }
        }
        return true;
    }

    // метод отвечает за отрисовку
    @Override
    protected void paintComponent(Graphics g) {
        // отрисовка по умолчанию
        super.paintComponent(g);
        // если мы в состоянии игры отриcовка наша метода render()
        if (gameWork) {
            render(g);
        }
    }

    // метод отрисовки поля принимает объект графики, как холст
    private void render(Graphics g) {
        // определяем размеры поля
        width = getWidth();
        height = getHeight();
        // определяем размеры ячейки
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;

        g.setColor(Color.BLACK); // цвет кисти - черный
        for (int h = 0; h < fieldSizeX; h++) {
            int y = h * cellHeight;
            // линия проводится из одного угла в другой, единственный метод
            g.drawLine(0, y, width, y);
        } // по вертикали и горизонтали рисуем линии
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellWidth;
            // линия проводится из одного угла в другой
            g.drawLine(x, 0, x, height);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                // если поле пустое ничего не рисуем
                if (field[y][x] == EMPTY_DOT) {
                    continue;
                }
                // если был ход игрока рисуем крестик, две линии
                if (field[y][x] == HUMAN_DOT) {
                    g.drawLine(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            (x + 1) * cellWidth - PADDING, (y + 1) * cellHeight - PADDING);
                    g.drawLine(x * cellWidth + PADDING, (y + 1) * cellHeight - PADDING,
                            (x + 1) * cellWidth - PADDING, y * cellHeight + PADDING);
                } else if (field[y][x] == AI_DOT) {
                    // если был ход компьютера рисуем овал
                    g.drawOval(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            cellWidth - PADDING * 2, cellHeight - PADDING * 2);
                } else {
                    throw new RuntimeException("unchecked value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        }
        // проверка, если игра закончена вывести сообщение кто победил
        if (gameStateType != STATE_GAME) {
            showMessage(g);
        }
    }

    // выдаёт сообщение в зависимости от результата
    private void showMessage(Graphics g) {
        // кисть серый
        g.setColor(Color.DARK_GRAY);
        // создаётся прямоугольная область в которой появится текст
        g.fillRect(0, getHeight() / 2, getWidth(), 70);
        // цвет шрифта
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameStateType){
            // при использовании лямбда выражений break уже не нужен, есть автоматически
            case STATE_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2 + 60);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 20, getHeight() / 2 + 60);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 70, getHeight() / 2 + 60);
            default -> throw new RuntimeException("Unchecked gameOverState: " + gameStateType);
        }
        // игра окончена
        gameWork = false;
    }
}


