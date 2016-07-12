/*
 * @ErrorCatcher.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */

package ru.tproger.main;

/**
 * Класс предназначен для обработки возможных ошибок и/или вывода их на экран
 *
 * @author DoKel
 * @version 1.0
 */
public class ErrorCatcher {



    /**
     * Передан неверный параметр
     *
     * @param eparameter Описание неверного параметра
     * @param eclass Описание класса, в котором встретилась ошибка
     */
    public static void wrongParameter(String eparameter, String eclass) {
        System.err.println("Wrong parameter "+eparameter+" occured in class "+eclass);
        System.exit(-2);
    }

    /**
     * Внутренняя ошибка графического модуля
     *
     * @param e Выброшенное исключение
     */
    public static void graphicsFailure(Exception e) {
        System.err.println("GraphicsModule failed.");
        e.printStackTrace();
        System.exit(-3);
    }

}
