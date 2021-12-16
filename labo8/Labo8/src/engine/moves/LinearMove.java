package engine.moves;

import engine.Board;
import engine.utils.Cell;

public class LinearMove extends Move {
    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        return from.getX() == to.getX() || from.getY() == to.getY();
    }
}
