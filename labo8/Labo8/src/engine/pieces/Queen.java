package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public class Queen extends Piece {
    public Queen(PieceType type, Cell cell, PlayerColor color) {
        super(type, cell, color);
    }
}
