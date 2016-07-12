/*
 * @GameField.java
 *
 * Version 1.0 (11.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package ru.tproger.main;

import ru.tproger.graphics.TpReadableColor;
import java.util.Random;
import static ru.tproger.main.Constants.*;

/**
 * Хранит информацию о поле и о падающей фигуре.
 * Содержит методы для их обновления, и получения о них информации.
 *
 * @author DoKel
 * @version 1.0
 */
public class GameField {

    /**  Цвета ячеек поля. Для пустых ячеек используется константа EMPTINESS_COLOR */
    private TpReadableColor[][] theField;

    /** Количество непустых ячеек строки.
     *  Можно было бы получать динамически из theField, но это дольше.
     */
    private int[] countFilledCellsInLine;

    /**  Информация о падающей в данный момент фигуре   */
    private Figure figure;

    /**
     * Генерирует начальные блоки и создаёт первую фигуру.
     * Здесь должна происходить инициализация всех полей класса.
     */
    public GameField(){
        spawnNewFigure();

        theField = new TpReadableColor[COUNT_CELLS_X][COUNT_CELLS_Y+OFFSET_TOP];
        countFilledCellsInLine = new int[COUNT_CELLS_Y+OFFSET_TOP];

        Random rnd = new Random();

        /* До определённого уровня создаём начальные блоки с пустотами в каждой линии */
        for(int y = 0; y < BLOCKS_INITIAL_LEVEL; y++){

            /* Количество пустых блоков в линии */
            int missingBlocksCount = MISSNG_BLOCKS_IN_INITIAL_LINE_MIN
                    + rnd.nextInt(MISSNG_BLOCKS_IN_INITIAL_LINE_MAX - MISSNG_BLOCKS_IN_INITIAL_LINE_MIN);

            /* X-координаты пустых блоков линии*/
            int[] missingBlocksXCoords = new int[missingBlocksCount];

            /*
             * Пустые блоки генерируются слева направо.
             * Каждый следующий пустой блок не может быть к концу координат ближе,
             * чем на количество оставшихся для генерации блоков.
             */
            missingBlocksXCoords[0] = rnd.nextInt(COUNT_CELLS_X - (missingBlocksCount - 1) );
            for(int i = 1; i < missingBlocksCount; i++){
                int previousCoord = missingBlocksXCoords[i-1];
                int offset = rnd.nextInt(COUNT_CELLS_X-(previousCoord-1));

                missingBlocksXCoords[i] =  previousCoord + offset;
            }

            /*
             * Если Х-координата не попала в missingBlocksXCoords,
             * создаём в ней кирпичик случайного цвета
             */
            int q = 0;
            for(int x = 0; x < COUNT_CELLS_X; x++){
                if( (q < missingBlocksCount) && (missingBlocksXCoords[q] == x) ) {
                    theField[x][y] = EMPTINESS_COLOR;
                    q++;
                } else {
                    theField[x][y] = TpReadableColor.getRandomColor();
                    countFilledCellsInLine[y]++;
                }
            }
        }

        /* Остальное пространство заполняем пустыми блоками */
        for(int y = BLOCKS_INITIAL_LEVEL; y < COUNT_CELLS_Y + OFFSET_TOP; y++){
            for(int x = 0; x < COUNT_CELLS_X; x++){
                theField[x][y] = EMPTINESS_COLOR;
            }
        }
    }

    /**
     * Создаёт новую фигуру в невидимой зоне
     * X-координата для генерации не должна быть ближе к правому краю,
     * чем максимальная ширина фигуры (MAX_FIGURE_WIDTH), чтобы влезть в экран
     */
    private void spawnNewFigure(){
       int randomX = new Random().nextInt(COUNT_CELLS_X - MAX_FIGURE_WIDTH);

       this.figure = new Figure(new Coord(randomX, COUNT_CELLS_Y + OFFSET_TOP - 1));
    }

    /**
     * @param x X-координата проверяемой ячейки
     * @param y Y-координата проверяемой ячейки
     * @return Является ли ячейка с координатами (X, Y) пустой
     */
    public boolean isEmpty(int x, int y){
        return (theField[x][y].equals(EMPTINESS_COLOR));
    }

    /**
     * @param x X-координата проверяемой ячейки
     * @param y Y-координата проверяемой ячейки
     * @return Цвет ячейки с координатами (X, Y)
     */
    public TpReadableColor getColor(int x, int y) {
        return theField[x][y];
    }

    /**
     * @return Информация о летящей в данный момент фигуре
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     * Если это возможно, смещает летящуюю фигуру в направлении shiftDirection
     *
     * @param shiftDirection направление, в котором необходимо сместить фигуру
     */
    public void tryShiftFigure(ShiftDirection shiftDirection) {
        Coord[] shiftedCoords = figure.getShiftedCoords(shiftDirection);

        boolean canShift = true;

        for(Coord coord: shiftedCoords) {
            if((coord.y<0 || coord.y>=COUNT_CELLS_Y+OFFSET_TOP)
             ||(coord.x<0 || coord.x>=COUNT_CELLS_X)
             || ! isEmpty(coord.x, coord.y)){
                canShift = false;
            }
        }

        if(canShift){
            figure.shift(shiftDirection);
        }
    }

    /**
     * Если это возможно, пробуем повернуть фигуру по часовой стрелке
     */
    public void tryRotateFigure() {
        Coord[] rotatedCoords = figure.getRotatedCoords();

        boolean canRotate = true;

        for(Coord coord: rotatedCoords) {
            if((coord.y<0 || coord.y>=COUNT_CELLS_Y+OFFSET_TOP)
                    ||(coord.x<0 || coord.x>=COUNT_CELLS_X)
                    ||! isEmpty(coord.x, coord.y)){
                canRotate = false;
            }
        }

        if(canRotate){
            figure.rotate();
        }
    }

    /**
     * Если фигуру можно сместить вниз, смещаем.
     * Если это невозможно, переносим фигуру в список статичных блоков и создаём новую.
     */
    public void letFallDown() {
        Coord[] fallenCoords = figure.getFallenCoords();

        boolean canFall = true;

        for(Coord coord: fallenCoords) {
            if((coord.y<0 || coord.y>=COUNT_CELLS_Y+OFFSET_TOP)
                    ||(coord.x<0 || coord.x>=COUNT_CELLS_X)
                    ||! isEmpty(coord.x, coord.y)){
                canFall = false;
            }
        }

        if(canFall) {
            figure.fall();
        } else {
            Coord[] figureCoords = figure.getCoords();

            /* Флаг, говорящий о том, что после будет необходимо сместить линии вниз
             * (т.е. какая-то линия была уничтожена)
             */
            boolean haveToShiftLinesDown = false;

            for(Coord coord: figureCoords) {
                theField[coord.x][coord.y] = figure.getColor();

                /* Увеличиваем информацию о количестве статичных блоков в линии*/
                countFilledCellsInLine[coord.y]++;

                /* Проверяем, полностью ли заполнена строка Y
                 * Если заполнена полностью, устанавливаем  haveToShiftLinesDown в true
                 */
                haveToShiftLinesDown = tryDestroyLine(coord.y) || haveToShiftLinesDown;
            }

            /* Если это необходимо, смещаем линии на образовавшееся пустое место */
            if(haveToShiftLinesDown) shiftLinesDown();

            /* Создаём новую фигуру взамен той, которую мы перенесли*/
            spawnNewFigure();
        }
    }

    /**
     * Если на поле есть полностю пустые линии, сдвигает вышестоящие непустые линии на их место.
     */
    private void shiftLinesDown() {

        /* Номер обнаруженной пустой линии (-1, если не обнаружена) */
        int fallTo = -1;

        /* Проверяем линии снизу вверх*/
        for(int y = 0; y < COUNT_CELLS_Y; y++){
            if(fallTo == -1){ //Если пустот ещё не обнаружено
                if(countFilledCellsInLine[y] == 0) fallTo = y; //...пытаемся обнаружить (._.)
            }else { //А если обнаружено
                if(countFilledCellsInLine[y] != 0){ // И текущую линию есть смысл сдвигать...

                    /* Сдвигаем... */
                    for(int x = 0; x < COUNT_CELLS_X; x++){
                        theField[x][fallTo] = theField[x][y];
                        theField[x][y] = EMPTINESS_COLOR;
                    }

                    /* Не забываем обновить мета-информацию*/
                    countFilledCellsInLine[fallTo] = countFilledCellsInLine[y];
                    countFilledCellsInLine[y] = 0;

                    /*
                     * В любом случае линия сверху от предыдущей пустоты пустая.
                     * Если раньше она не была пустой, то сейчас мы её сместили вниз.
                     * Если раньше она была пустой, то и сейчас пустая -- мы её ещё не заполняли.
                     */
                    fallTo++;
                }
            }
        }
    }

    /**
     * Если линия полностю заполнена, уничтожает её (но не сдвигает остальные!)
     *
     * @param y Номер проверяемой линии
     * @return Была ли линия уничтожена
     */
    private boolean tryDestroyLine(int y) {
        if(countFilledCellsInLine[y] < COUNT_CELLS_X){
            return false;
        }

        for(int x = 0; x < COUNT_CELLS_X; x++){
            theField[x][y] = EMPTINESS_COLOR;
        }

        /* Не забываем обновить мета-информацию! */
        countFilledCellsInLine[y] = 0;

        return true;
    }

    /**
     * @return Есть ли в невидимой зоне над полем статичные блоки
     */
    public boolean isOverfilled(){
        boolean ret = false;

        for(int i = 0; i < OFFSET_TOP; i++){
            if(countFilledCellsInLine[COUNT_CELLS_Y+i] != 0) ret = true;
        }

        return ret;
    }

}
