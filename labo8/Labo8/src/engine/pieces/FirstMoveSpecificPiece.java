package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public abstract class FirstMoveSpecificPiece extends Piece {
    public FirstMoveSpecificPiece(PieceType type, Cell cell, PlayerColor color) {
        super(type, cell, color);
    }
}
