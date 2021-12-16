package engine.moves;

import engine.Board;
import engine.utils.Cell;

public abstract class Move {
    public boolean isPathFree(Board board, Cell from, Cell to) {
        return false;
    }
    
    public abstract boolean canMove(Board board, Cell from, Cell to);
}
