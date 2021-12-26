package engine.pieces;

import chess.PlayerColor;
import engine.utils.Cell;

public abstract class FirstMoveSpecificPiece extends Piece {
    private boolean hasMoved;
    
    public FirstMoveSpecificPiece(Cell cell, PlayerColor color) {
        super(cell, color);
        hasMoved = false;
    }
    
    public boolean hasMoved() {
        return hasMoved;
    }
    
    public void setMoved() {
        hasMoved = true;
    }
}
