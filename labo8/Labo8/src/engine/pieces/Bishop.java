package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.DiagonalMove;
import engine.utils.Cell;

public class Bishop extends Piece {
    public Bishop(Cell cell, PlayerColor color) {
        super(cell, color);
        moves.add(new DiagonalMove());
    }
    
    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
