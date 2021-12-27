package engine.moves;

import engine.Board;
import engine.pieces.Piece;
import engine.utils.Cell;

public abstract class Move {
    private final Piece piece;
    
    public Move(Piece piece) {
        this.piece = piece;
    }
    
    public abstract boolean canMove(Cell from, Cell to);
    
    public Piece getPiece() {
        return piece;
    }
    
    public Board getBoard() {
        return piece.getBoard();
    }
}
