/*
 * @ShiftDirection.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */
package ru.tproger.main;

/**
 * Хранит все возможные формы запросов на сдвиг фигуры в бок.
 *
 * @author DoKel
 * @version 1.0
 */
public enum ShiftDirection {
    /** Направление не определено (сдвиг не требуется)*/
    AWAITING,

    /** Требуется сдвиг влево*/
    LEFT,

    /** Требуется сдвиг вправо*/
    RIGHT;
}
