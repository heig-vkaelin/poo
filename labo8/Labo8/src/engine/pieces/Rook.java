package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public class Rook extends FirstMoveSpecificPiece {
    public Rook(Cell cell, PlayerColor color) {
        super(cell, color);
    }
    
    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
