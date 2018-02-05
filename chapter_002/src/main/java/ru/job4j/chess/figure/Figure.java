package ru.job4j.chess.figure;

import ru.job4j.chess.board.Cell;
import ru.job4j.chess.exeption.ImpossibleMoveException;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public abstract class Figure {

    private final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public boolean exist(Cell source) {
        return this.position.cellEquals(source);
    }

    public boolean exist(Figure figure) {
        int f1x = this.position.getX();
        int f1y = this.position.getY();

        int f2x = figure.position.getX();
        int f2y = figure.position.getY();

        return (f1x == f2x && f1y == f2y) ? true : false;
    }

    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public abstract Figure copy(Cell dest);
}
