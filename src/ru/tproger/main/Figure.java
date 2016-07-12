/*
 * @Figure.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */

package ru.tproger.main;

import ru.tproger.graphics.TpReadableColor;

/**
 * Хранит и изменяет мнимую координату фигуры, форму фигуры и состояние её поворота
 *
 * @author DoKel
 * @version 1.0
 */
public class Figure {
    /**
     * Мнимая координата фигуры. По этой координате
     * через маску генерируются координаты реальных
     * блоков фигуры.
     */
    private Coord metaPointCoords;

    /**
     * Текущее состояние поворота фигуры. См. документацию RotationMode
     */
    private RotationMode currentRotation;

    /**
     * Форма фигуры. См. документацию FigureForm
     */
    private FigureForm form;

    /**
     * Конструктор. <br>
     * Состояние поворота по умолчанию: RotationMode.NORMAL <br>
     * Форма задаётся случайная.
     *
     * @param metaPointCoords Мнимая координата фигуры. См. документацию одноимённого поля
     */
    public Figure(Coord metaPointCoords){
        this(metaPointCoords, RotationMode.NORMAL, FigureForm.getRandomForm());
    }

    /**
     * @param metaPointCoords Мнимая координата фигуры. См. документацию одноимённого поля
     * @param rotation Состояние поворота См. документацию RotationMode
     * @param form Форма фигуры См. документацию FigureForm
     */
    public Figure(Coord metaPointCoords, RotationMode rotation, FigureForm form){
        this.metaPointCoords = metaPointCoords;
        this.currentRotation = rotation;
        this.form = form;
    }

    /**
     * @return Координаты реальных ячеек фигуры в текущем состоянии
     */
    public Coord[] getCoords(){
        return form.getMask().generateFigure(metaPointCoords, currentRotation);
    }

    /**
     * @return Координаты ячеек фигуры, как если бы
     * она была повёрнута проти часовой стрелки от текущего положения
     */
    public Coord[] getRotatedCoords(){
        return form.getMask().generateFigure(metaPointCoords, RotationMode.getNextRotationFrom(currentRotation));
    }

    /**
     * Поворачивает фигуру против часово стрелки
     */
    public void rotate(){
        this.currentRotation = RotationMode.getNextRotationFrom(currentRotation);
    }

    /**
     * @param direction Направление сдвига
     * @return Координаты ячеек фигуры, как если бы
     * она была сдвинута в указано направлении
     */
    public Coord[] getShiftedCoords(ShiftDirection direction){
        Coord newFirstCell = null;

        switch (direction){
            case LEFT:
                newFirstCell = new Coord(metaPointCoords.x - 1, metaPointCoords.y);
                break;
            case RIGHT:
                newFirstCell = new Coord(metaPointCoords.x + 1, metaPointCoords.y);
                break;
            default:
                ErrorCatcher.wrongParameter("direction (for getShiftedCoords)", "Figure");
        }

        return form.getMask().generateFigure(newFirstCell, currentRotation);
    }

    /**
     * Меняет мнимую X-координату фигуры
     * для сдвига в указаном направлении
     *
     * @param direction Направление сдвига
     */
    public void shift(ShiftDirection direction){
        switch (direction){
            case LEFT:
                metaPointCoords.x--;
                break;
            case RIGHT:
                metaPointCoords.x++;
                break;
            default:
                ErrorCatcher.wrongParameter("direction (for shift)", "Figure");
        }
    }

    /**
     * @return Координаты ячеек фигуры, как если бы
     * она была сдвинута вниз на одну ячейку
     */
    public Coord[] getFallenCoords(){
        Coord newFirstCell = new Coord(metaPointCoords.x, metaPointCoords.y - 1);

        return form.getMask().generateFigure(newFirstCell, currentRotation);
    }

    /**
     * Меняет мнимую Y-координаты фигуры
     * для сдвига на одну ячейку вниз
     */
    public void fall(){
        metaPointCoords.y--;
    }

    /**
     * @return Цвет фигуры
     */
    public TpReadableColor getColor() {
        return form.getColor();
    }
}
