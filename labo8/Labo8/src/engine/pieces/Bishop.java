package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.LinearMove;
import engine.utils.Cell;

public class Bishop extends Piece {
    public Bishop(Cell cell, PlayerColor color) {
        super(cell, color);
        moves.add(new LinearMove(this, new Cell(1, 1)));
        moves.add(new LinearMove(this, new Cell(1, -1)));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
