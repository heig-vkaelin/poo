package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public class King extends FirstMoveSpecificPiece {
    public King(Cell cell, PlayerColor color) {
        super(cell, color);
    }
    
    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
