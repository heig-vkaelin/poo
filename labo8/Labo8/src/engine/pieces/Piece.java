package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Move;
import engine.moves.TypeMove;
import engine.utils.Cell;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private PlayerColor color;
    private Cell cell;
    protected List<Move> moves;
    
    public Piece(Cell cell, PlayerColor color) {
        this.cell = cell;
        this.color = color;
        moves = new ArrayList<>();
    }
    
    public abstract PieceType getType();
    
    public PlayerColor getColor() {
        return color;
    }
    
    public Cell getCell() {
        return cell;
    }
    
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    
    public TypeMove checkMove(Board board, Cell nextPos) {
        for (Move move : moves) {
            if (move.canMove(board, cell, nextPos)) {
                return TypeMove.VALID;
            }
        }
        return TypeMove.INVALID;
    }

//    public abstract void postUpdate();
}
