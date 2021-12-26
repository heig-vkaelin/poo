package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.TypeMove;
import engine.utils.Cell;

public class Pawn extends FirstMoveSpecificPiece {
    public Pawn(Cell cell, PlayerColor color) {
        super(cell, color);
    }
    
    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
    
//    @Override
//    public TypeMove checkMove(Board board, Cell nextPos) {
//
////        if () TODO: check distance
//
//        return super.checkMove(board, nextPos);
//    }
}
