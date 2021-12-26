package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.LinearMove;
import engine.utils.Cell;

public class Knight extends Piece {
    public Knight(Cell cell, PlayerColor color) {
        super(cell, color);
        moves.add(new LinearMove(new Cell(1, 2), 2, true));
        moves.add(new LinearMove(new Cell(1, -2), 2, true));
        moves.add(new LinearMove(new Cell(2, 1), 2, true));
        moves.add(new LinearMove(new Cell(2, -1), 2, true));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
