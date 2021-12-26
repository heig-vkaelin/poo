package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.LinearMove;
import engine.utils.Cell;

public class Rook extends FirstMoveSpecificPiece {
    public Rook(Cell cell, PlayerColor color) {
        super(cell, color);
        moves.add(new LinearMove());
    }
    
    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
