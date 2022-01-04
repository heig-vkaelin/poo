package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LinearMove;
import engine.utils.Cell;

/**
 * Classe représentant un fou
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class Bishop extends Piece {
    public Bishop(Board board, Cell cell, PlayerColor color) {
        super(board, cell, color);
        moves.add(new LinearMove(this, new Cell(1, 1)));
        moves.add(new LinearMove(this, new Cell(1, -1)));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
    
    @Override
    public String textValue() {
        return "Fou";
    }
}
