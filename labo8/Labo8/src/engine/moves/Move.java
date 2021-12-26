package engine.moves;

import engine.Board;
import engine.utils.Cell;

public abstract class Move {
    public abstract boolean isValid(Cell from, Cell to);
    
    public abstract boolean canMove(Board board, Cell from, Cell to);
}
