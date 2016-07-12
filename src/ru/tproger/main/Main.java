package ru.tproger.main;

import ru.tproger.graphics.GraphicsModule;
import ru.tproger.graphics.lwjglmodule.LwjglGraphicsModule;
import ru.tproger.keyboard.KeyboardHandleModule;
import ru.tproger.keyboard.lwjglmodule.LwjglKeyboardHandleModule;

import static ru.tproger.main.Constants.BOOST_MULTIPLIER;
import static ru.tproger.main.Constants.FPS;
import static ru.tproger.main.Constants.FRAMES_PER_MOVE;

/**
 *
 * Главный управляющий класс.
 * Считывает запросы пользователя,
 * передаёт запросы на исполнение,
 * обновляет данные,
 * передаёт данные в graphicsModule,
 * завершает игру, если необходимо
 *
 *  TODO: Подсчитывает и выводит очки
 *
 * @version 1.0
 * @author DoKel
 *
 */
public class Main {

    /** Флаг для завершения основного цикла программы */
    private static boolean endOfGame;

    /** Графический модуль игры*/
    private static GraphicsModule graphicsModule;

    /** "Клавиатурный" модуль игры, т.е. модуль для чтения запросов с клавиатуры*/
    private static KeyboardHandleModule keyboardModule;

    /** Игровое поле. См. документацию GameField */
    private static GameField gameField;

    /** Направление для сдвига, полученное за последнюю итерацию */
    private static ShiftDirection shiftDirection;

    /** Был ли за последнюю итерацию запрошен поворот фигуры */
    private static boolean isRotateRequested;

    /** Было ли за последнюю итерацию запрошено ускорение падения*/
    private static boolean isBoostRequested;

    /** Номер игровой итерации по модулю FRAMES_PER_MOVE.
     *  Падение фигуры вниз происходит если loopNumber % FRAMES_PER_MOVE == 0
     *  Т.е. 1 раз за FRAMES_PER_MOVE итераций.
     */
    private static int loopNumber;

    /**
     * Точка входа. Содержит все необходимые действия для одного игрового цикла.
     */
    public static void main(String[] args) {
        initFields();

        while(!endOfGame){
            input();
            logic();

            graphicsModule.draw(gameField);
            graphicsModule.sync(FPS);
        }

        graphicsModule.destroy();
    }

    /**
     * Задаёт значения полей для начала игры
     */
    private static void initFields() {
        loopNumber = 0;
        endOfGame = false;
        shiftDirection = ShiftDirection.AWAITING;
        isRotateRequested = false;
        graphicsModule = new LwjglGraphicsModule();
        keyboardModule = new LwjglKeyboardHandleModule();
        gameField = new GameField();
    }

    /**
     * Чтение пользовательских команд. Их выполнение реализовано в logic().
     */
    private static void input(){

        keyboardModule.update();

        shiftDirection = keyboardModule.getShiftDirection();

        isRotateRequested = keyboardModule.wasRotateRequested();

        isBoostRequested = keyboardModule.wasBoostRequested();

        endOfGame = endOfGame || keyboardModule.wasEscPressed() || graphicsModule.isCloseRequested();
    }

    /**
     * Здесь происходят основные игровые действия --
     * запросы пользователя передаются на исполнение,
     * обновляется игровое поле (и фигура)
     */
    private static void logic(){
       if(shiftDirection != ShiftDirection.AWAITING){ // Если есть запрос на сдвиг фигуры

           /* Пробуем сдвинуть */
           gameField.tryShiftFigure(shiftDirection);

           /* Ожидаем нового запроса */
           shiftDirection = ShiftDirection.AWAITING;
       }

       if(isRotateRequested){ // Если есть запрос на поворот фигуры

           /* Пробуем повернуть */
           gameField.tryRotateFigure();

           /* Ожидаем нового запроса */
           isRotateRequested = false;
       }

        /* Падение фигуры вниз происходит если loopNumber % FRAMES_PER_MOVE == 0
         * Т.е. 1 раз за FRAMES_PER_MOVE итераций.
         */
       if( (loopNumber % (FRAMES_PER_MOVE / (isBoostRequested ? BOOST_MULTIPLIER : 1)) ) == 0) gameField.letFallDown();

        /* Увеличение номера итерации (по модулю FPM)*/
       loopNumber = (loopNumber+1)% (FRAMES_PER_MOVE);

        /* Если поле переполнено, игра закончена */
       endOfGame = endOfGame || gameField.isOverfilled();
    }

}
