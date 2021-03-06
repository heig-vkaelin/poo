package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LinearMove;
import engine.utils.Cell;

/**
 * Classe représentant un cavalier
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class Knight extends Piece {
    public Knight(Board board, Cell cell, PlayerColor color) {
        super(board, cell, color);
        moves.add(new LinearMove(this, new Cell(1, 2), 2, true));
        moves.add(new LinearMove(this, new Cell(1, -2), 2, true));
        moves.add(new LinearMove(this, new Cell(2, 1), 2, true));
        moves.add(new LinearMove(this, new Cell(2, -1), 2, true));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
    
    @Override
    public String textValue() {
        return "Cavalier";
    }
}
