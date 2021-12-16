package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.DiagonalMove;
import engine.moves.LinearMove;
import engine.utils.Cell;

public class Queen extends Piece {
    public Queen(Cell cell, PlayerColor color) {
        super(cell, color);
        moves.add(new DiagonalMove());
        moves.add(new LinearMove());
    }
    
    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
