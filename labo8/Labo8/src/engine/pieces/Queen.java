package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.moves.LinearMove;
import engine.utils.Cell;

public class Queen extends Piece {
    public Queen(Cell cell, PlayerColor color) {
        super(cell, color);
        moves.add(new LinearMove(this, new Cell(0, 1)));
        moves.add(new LinearMove(this, new Cell(1, 0)));
        moves.add(new LinearMove(this, new Cell(1, 1)));
        moves.add(new LinearMove(this, new Cell(1, -1)));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
    
    @Override
    public String textValue() {
        return "Reine";
    }
}
