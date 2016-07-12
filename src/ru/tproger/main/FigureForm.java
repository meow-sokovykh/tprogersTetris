/*
 * @FigureForm.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */

package ru.tproger.main;

import ru.tproger.graphics.TpReadableColor;

import java.util.Random;

/**
 * Связывает все формоспецефичные параметры фигуры.
 * Сейчас: маска координат, цвет
 *
 * @author  DoKel
 * @version 1.0
 */
public enum FigureForm {

    I_FORM (CoordMask.I_FORM, TpReadableColor.BLUE),
    J_FORM (CoordMask.J_FORM, TpReadableColor.ORANGE),
    L_FORM (CoordMask.L_FORM, TpReadableColor.YELLOW),
    O_FORM (CoordMask.O_FORM, TpReadableColor.RED),
    S_FORM (CoordMask.S_FORM, TpReadableColor.AQUA),
    Z_FORM (CoordMask.Z_FORM, TpReadableColor.PURPLE),
    T_FORM (CoordMask.T_FORM, TpReadableColor.GREEN);

    /** Маска координат (задаёт геометрическую форму) */
    private CoordMask mask;

    /** Цвет, характерный для этой формы */
    private TpReadableColor color;

    /**
     * Конструктор.
     *
     * @param mask Маска координат (задаёт геометрическую форму)
     * @param color Цвет, характерный для этой формы
     */
    FigureForm(CoordMask mask, TpReadableColor color){
        this.mask = mask;
        this.color = color;
    }

    /**
     * Массив со всеми объектами этого enum'а (для удобной реализации getRandomForm() )
     */
    private static final FigureForm[] formByNumber = {I_FORM, J_FORM, L_FORM, O_FORM, S_FORM, Z_FORM, T_FORM,};

    /**
     * @return Маска координат данной формы
     */
    public CoordMask getMask(){
        return this.mask;
    }

    /**
     * @return Цвет, специфичный для этой формы
     */
    public TpReadableColor getColor(){
        return this.color;
    }

    /**
     * @return Случайный объект этого enum'а, т.е. случайная форма
     */
    public static FigureForm getRandomForm() {
        int formNumber = new Random().nextInt(formByNumber.length);
        return formByNumber[formNumber];
    }

}
