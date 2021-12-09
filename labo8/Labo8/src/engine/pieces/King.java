package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public class King extends FirstMoveSpecificPiece {
    public King(PieceType type, Cell cell, PlayerColor color) {
        super(type, cell, color);
    }
}
