package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public class Pawn extends FirstMoveSpecificPiece {
    public Pawn(PieceType type, Cell cell, PlayerColor color) {
        super(type, cell, color);
    }
}
