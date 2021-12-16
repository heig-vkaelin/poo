package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public class Knight extends Piece {
    public Knight(Cell cell, PlayerColor color) {
        super(cell, color);
    }
    
    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
