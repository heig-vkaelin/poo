package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LinearMove;
import engine.utils.Cell;

/**
 * Classe représentant une tour
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class Rook extends FirstMoveSpecificPiece {
    public Rook(Board board, Cell cell, PlayerColor color) {
        super(board, cell, color);
        moves.add(new LinearMove(this, new Cell(0, 1)));
        moves.add(new LinearMove(this, new Cell(1, 0)));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
    
    @Override
    public String textValue() {
        return "Tour";
    }
}
