/*
 * @Constants.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */

package ru.tproger.main;

import ru.tproger.graphics.TpReadableColor;

/**
 * Хранит ключевые для игры константы в public static final полях
 *
 * @author DoKel
 * @version 1.0
 */
public class Constants {

	/* Размер одной плитки */
	public static final int CELL_SIZE = 24;

	/* Количество ячеек на экране по горизонтали и вертикали */
    public static final int COUNT_CELLS_X = 10;
    public static final int COUNT_CELLS_Y = 2 * COUNT_CELLS_X;

    /*Невидимое пространство сверху, в котором создаются фигуры*/
    public static final int OFFSET_TOP = 3;

	/* Параметры окна */
    public static final int SCREEN_WIDTH = COUNT_CELLS_X *CELL_SIZE;
    public static final int SCREEN_HEIGHT = COUNT_CELLS_Y *CELL_SIZE;
    public static final String SCREEN_NAME = "Tproger's Tetris";

    /* Количество раз, в которое увеличивается скорость падения,
     * если пользователь нажал соответсвующую клавишу
     */
    public static final int BOOST_MULTIPLIER = 5;

    /* Количество клеток, на которое в секунду смещается вниз фигура*/
    public static final int MOVEDOWNS_PER_SECOND = 3;

    /* Количество игровых циклов в секунду*/
    public static final int FPS = 60;

    /* Количество игровых циклов, за которое фигура сместится вниз на одну клетку */
    public static final int FRAMES_PER_MOVE = FPS / MOVEDOWNS_PER_SECOND;

    /* Цвет, которым в поле обозначается пустота */
    public static final TpReadableColor EMPTINESS_COLOR = TpReadableColor.BLACK;

	/* Количество линий, заполненных блоками в начале */
    public static final int BLOCKS_INITIAL_LEVEL = COUNT_CELLS_Y / 3;

    /* Максимальное и минимальное количества отсутствующих блоков в линиях, созданных в начале игры */
    public static final int MISSNG_BLOCKS_IN_INITIAL_LINE_MIN = COUNT_CELLS_X/3;
    public static final int MISSNG_BLOCKS_IN_INITIAL_LINE_MAX = COUNT_CELLS_X/2;

    /* Максимально возможная ширина фигуры */
    public static final int MAX_FIGURE_WIDTH = 4;
}
