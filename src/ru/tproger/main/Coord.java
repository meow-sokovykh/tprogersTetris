/*
 * @Coord.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 *
 */
package ru.tproger.main;

/**
 * Оболочка для (X, Y) координат
 */
public class Coord{
    public int x,y;


    /**
     * Конструктор.
     *
     * @param x X-координата
     * @param y Y-координата
     */
    public Coord(int x, int y){
        this.x=x;
        this.y=y;
    }
}
