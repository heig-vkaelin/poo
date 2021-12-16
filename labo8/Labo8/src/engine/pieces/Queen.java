package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public class Queen extends Piece {
    public Queen(Cell cell, PlayerColor color) {
        super(cell, color);
    }
    
    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
