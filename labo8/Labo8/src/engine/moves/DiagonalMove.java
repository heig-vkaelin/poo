package engine.moves;

import engine.Board;
import engine.utils.Cell;

public class DiagonalMove extends Move {
    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        return Math.abs(to.getX() - from.getX()) == Math.abs(to.getY() - from.getY());
    }
}
