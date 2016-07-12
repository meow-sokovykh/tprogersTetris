/*
 * @CoordMask.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */

package ru.tproger.main;

/**
 * Каждая маска -- шаблон, который по мнимой координате фигуры и
 * состоянию её поворота возвращает 4 координаты реальных блоков
 * фигуры, которые должны отображаться.
 * Т.е. маска задаёт геометрическую форму фигуры.
 *
 * @author DoKel
 * @version 1.0
 */
public enum CoordMask {
    /* Кирпичик [][][][] */
    I_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initalCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                        case INVERT:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x , initalCoord.y - 1);
                            ret[2] = new Coord(initalCoord.x, initalCoord.y - 2);
                            ret[3] = new Coord(initalCoord.x, initalCoord.y - 3);
                            break;
                        case FLIP_CCW:
                        case FLIP_CW:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x + 1, initalCoord.y);
                            ret[2] = new Coord(initalCoord.x + 2, initalCoord.y);
                            ret[3] = new Coord(initalCoord.x + 3, initalCoord.y);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /* Кирпичик  []
     *           [][][]
     */
    J_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initalCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = new Coord(initalCoord.x + 1 , initalCoord.y);
                            ret[1] = new Coord(initalCoord.x + 1, initalCoord.y - 1);
                            ret[2] = new Coord(initalCoord.x + 1, initalCoord.y - 2);
                            ret[3] = new Coord(initalCoord.x, initalCoord.y - 2);
                            break;
                        case INVERT:
                            ret[0] = new Coord(initalCoord.x + 1 , initalCoord.y);
                            ret[1] = initalCoord;
                            ret[2] = new Coord(initalCoord.x, initalCoord.y - 1);
                            ret[3] = new Coord(initalCoord.x, initalCoord.y - 2);
                            break;
                        case FLIP_CCW:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x + 1, initalCoord.y);
                            ret[2] = new Coord(initalCoord.x + 2, initalCoord.y);
                            ret[3] = new Coord(initalCoord.x + 2, initalCoord.y - 1);
                            break;
                        case FLIP_CW:
                            ret[0] = initalCoord;
                            ret[1] = new Coord(initalCoord.x, initalCoord.y - 1);
                            ret[2] = new Coord(initalCoord.x + 1, initalCoord.y - 1);
                            ret[3] = new Coord(initalCoord.x + 2, initalCoord.y - 1);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /* Кирпичик     []
     *          [][][]
     */
    L_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 2);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        case INVERT:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        case FLIP_CCW:
                            ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        case FLIP_CW:
                            ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[1] = initialCoord;
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /* Кирпичик [][]
     *          [][]
     */
    O_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    ret[0] = initialCoord;
                    ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                    ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                    ret[3] = new Coord(initialCoord.x + 1, initialCoord.y);

                    return ret;
                }
            }
    ),
    /* Кирпичик   [][]
     *          [][]
     */
    S_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                        case INVERT:
                            ret[0] = new Coord(initialCoord.x , initialCoord.y - 1);
                            ret[1] = new Coord(initialCoord.x + 1 , initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        case FLIP_CCW:
                        case FLIP_CW:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /* Кирпичик [][]
     *            [][]
     */
    Z_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                        case INVERT:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1 , initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            break;
                        case FLIP_CCW:
                        case FLIP_CW:
                            ret[0] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x, initialCoord.y - 2);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),
    /* Кирпичик [][][]
     *            []
     */
    T_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        case INVERT:
                            ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            break;
                        case FLIP_CCW:
                            ret[0] = initialCoord;;
                            ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x, initialCoord.y - 2);
                            break;
                        case FLIP_CW:
                            ret[0] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    );

    /**
     * Делегат, содержащий метод,
     * который должен определять алгоритм для generateFigure()
     */
    private interface GenerationDelegate{

        /**
         * По мнимой координате фигуры и состоянию её поворота
         * возвращает 4 координаты реальных блоков фигуры, которые должны отображаться
         *
         * @param initialCoord Мнимая координата
         * @param rotation Состояние поворота
         * @return 4 реальные координаты
         */
        Coord[] generateFigure(Coord initialCoord,  RotationMode rotation);
    }

    /** Делегат с методом,который должен определять алгоритм для generateFigure().
     *  Спецефичен для каждого объекта enum'а
     */
    private GenerationDelegate forms;

    /**
     * Конструктор.
     * @param forms Делегат с методом,который должен определять алгоритм для generateFigure()
     */
    CoordMask(GenerationDelegate forms){
        this.forms = forms;
    }

    /**
     * По мнимой координате фигуры и состоянию её поворота
     * возвращает 4 координаты реальных блоков фигуры, которые должны отображаться.
     *
     * Запрос передаётся делегату, спецефичному для каждого объекта enum'а.
     *
     * @param initialCoord Мнимая координата
     * @param rotation Состояние поворота
     * @return 4 реальные координаты
     */
    public Coord[] generateFigure(Coord initialCoord, RotationMode rotation){
        return this.forms.generateFigure(initialCoord, rotation);
    }
}
