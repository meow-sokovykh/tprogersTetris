/*
 * @LwjglKeyboardHandleModule.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */


package ru.tproger.keyboard.lwjglmodule;

import org.lwjgl.input.Keyboard;
import ru.tproger.keyboard.KeyboardHandleModule;
import ru.tproger.main.ShiftDirection;

/**
 * Реализует считывание необходимых игре параметров с клавиатуры средствами LWJGL
 *
 * @author DoKel
 * @version 1.0
 */
public class LwjglKeyboardHandleModule implements KeyboardHandleModule {

    /** Был ли за последнюю итерацию нажат ESC */
    private boolean wasEscPressed;

    /** Был ли за последнюю итерацию запрос на поворот фигуры.
     *  Сейчас значит "Была ли за последнюю итерацию нажата стрелка вверх"
     */
    private boolean wasRotateRequested;

    /** Направление, в котором пользователь запросил сдвиг фигуры в последнюю итерацию.
     *  Если пользователь не пытался сдвинуть фигуру, хранит ShiftDirection.AWAITING
     */
    private ShiftDirection shiftDirection;

    /**
     * Считывание последних данных из стека событий
     */
    @Override
    public void update() {
        resetValues();

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch(Keyboard.getEventKey()){
                    case Keyboard.KEY_ESCAPE:
                        wasEscPressed = true;
                        break;
                    case Keyboard.KEY_UP:
                        wasRotateRequested = true;
                        break;
                    case Keyboard.KEY_LEFT:
                        shiftDirection = ShiftDirection.LEFT;
                        break;
                    case Keyboard.KEY_RIGHT:
                        shiftDirection = ShiftDirection.RIGHT;
                        break;
                }
            }
        }
    }

    /**
     * Обнуление данных, полученых в при предыдущих запросах
     */
    private void resetValues() {
        wasEscPressed = false;
        wasRotateRequested = false;
        shiftDirection = ShiftDirection.AWAITING;
    }

    /**
     * @return Возвращает информацию о том, был ли нажат ESCAPE за последнюю итерацию
     */
    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }

    /**
     * @return Возвращает направление, в котором пользователь хочет сдивнуть фигуру.
     * Если пользователь не пытался сдвинуть фигуру, возвращает ShiftDirection.AWAITING.
     */
    @Override
    public ShiftDirection getShiftDirection() {
        return shiftDirection;
    }

    /**
     * @return Возвращает true, если пользователь хочет повернуть фигуру.
     */
    @Override
    public boolean wasRotateRequested() {
        return wasRotateRequested;
    }

    /**
     * @return Возвращает true, если пользователь хочет ускорить падение фигуры..
     */
    @Override
    public boolean wasBoostRequested() {
        return Keyboard.isKeyDown(Keyboard.KEY_DOWN);
    }


}
