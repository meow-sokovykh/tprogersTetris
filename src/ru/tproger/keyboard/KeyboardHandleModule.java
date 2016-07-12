/*
 * @KeyboardHandleModule.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */

package ru.tproger.keyboard;

import ru.tproger.main.ShiftDirection;

/**
 * Определяет параметры, которые игре необходимо считывать с клавиатуры.
 *
 * @author DoKel
 * @version 1.0
 */
public interface KeyboardHandleModule {

    /**
     * Считывание последних данных из стека событий, если можулю это необходимо
     */
    void update();

    /**
     * @return Возвращает информацию о том, был ли нажат ESCAPE за последнюю итерацию
     */
    boolean wasEscPressed();

    /**
     * @return Возвращает направление, в котором пользователь хочет сдивнуть фигуру.
     * Если пользователь не пытался сдвинуть фигуру, возвращает ShiftDirection.AWAITING.
     */
    ShiftDirection getShiftDirection();

    /**
     * @return Возвращает true, если пользователь хочет повернуть фигуру.
     */
    boolean wasRotateRequested();

    /**
     * @return Возвращает true, если пользователь хочет ускорить падение фигуры..
     */
    boolean wasBoostRequested();
}
