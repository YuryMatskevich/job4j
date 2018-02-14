package ru.job4j.chess.behavior;

import ru.job4j.chess.board.Cell;
import ru.job4j.chess.exeption.ImpossibleMoveException;

import static java.lang.Math.abs;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class PawnDown implements IChessMove {
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int deltaX = dest.getX() - source.getX();
        int deltaY = dest.getY() - source.getY();
        Cell[] way = new Cell[deltaX];
        if (deltaY == -1 && deltaX == 0) {
            way[0] = new Cell(source.getX(), source.getY() + deltaY);
        } else {
            throw new ImpossibleMoveException("Недопустимый ход");
        }
        return way;
    }
}