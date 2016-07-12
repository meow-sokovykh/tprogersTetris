/*
 * @TpReadableColor.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.graphics;

import java.util.Random;

/**
 * Класс, для обозначения цветов, использующихся в игре.
 * Эти цвета должны передаваться в графический модуль для отрисовывания.
 */
public enum TpReadableColor {
    BLACK, RED, GREEN, BLUE, AQUA, YELLOW, ORANGE, PURPLE;

    /**
     * Массив со всеми объектами этого enum'а (для удобной реализации getRandomColor() )
     */
    private static TpReadableColor[] colorByNumber = { BLACK, RED, GREEN, BLUE, AQUA, YELLOW, ORANGE, PURPLE, };

    /**
     * @return Случайный объект этого enum'а, т.е. случайный цвет
     */
    public static TpReadableColor getRandomColor() {
        int colorNumber = new Random().nextInt(colorByNumber.length);
        return colorByNumber[colorNumber];
    }
}
