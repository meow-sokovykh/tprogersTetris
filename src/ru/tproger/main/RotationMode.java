/*
 * @RotationMode.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */
package ru.tproger.main;

/**
 * Все возможные состояния фигуры после поворотов:
 * не была повёрнута, повёрнута против часовой стрелки 1 раз, 2 раза, 3 раза.
 * Пооворот против часовой стрелки 4 раза соответствует возвращению в начально положение.
 * Считается, что фигура может поворачиваться только против часовой стрелки.
 *
 * @author DoKel
 * @version 1.0
 */
public enum RotationMode {
    /** Начальное положение */
    NORMAL(0),

    /** Положение, соответствующее повороту против часовой стрелки*/
    FLIP_CCW(1),

    /** Положение, соответствующее зеркальному отражению*/
    INVERT(2),

    /** Положение, соответствующее повороту по часовой стрелке (или трём поворотам против)*/
    FLIP_CW(3);



    /** Количество поворотов против часовой стрелки, необходимое для принятия положения*/
    private int number;

    /**
     * Конструктор.
     *
     * @param number Количество поворотов против часовой стрелки, необходимое для принятия положения
     */
    RotationMode(int number){
        this.number = number;
    }

    /** Хранит объекты enum'а. Индекс в массиве соответствует полю number.
     *  Для более удобной работы getNextRotationForm().
     */
    private static RotationMode[] rotationByNumber = {NORMAL, FLIP_CCW, INVERT, FLIP_CW};

    /**
     * Возвращает положение, образованое в результате поворота по часовой стрелке
     * из положения perviousRotation
     *
     * @param perviousRotation Положение из которого был совершён поворот
     * @return Положение, образованное в результате поворота
     */
    public static RotationMode getNextRotationFrom(RotationMode perviousRotation) {
        int newRotationIndex = (perviousRotation.number + 1) % rotationByNumber.length;
        return rotationByNumber[newRotationIndex];
    }
}
