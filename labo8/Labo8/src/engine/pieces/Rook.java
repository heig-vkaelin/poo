package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public class Rook extends FirstMoveSpecificPiece {
    public Rook(PieceType type, Cell cell, PlayerColor color) {
        super(type, cell, color);
    }
}
