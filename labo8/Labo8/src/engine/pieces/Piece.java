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
    private final PlayerColor color;
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
    
    public boolean checkMove(Board board, Cell nextPos) {
        // Si la case de destination est occupée par une pièce de même couleur
        if (board.getPiece(nextPos) != null && board.getPiece(nextPos).getColor() == color) {
            return false;
        }
        
        for (Move move : moves) {
            if (move.canMove(board, cell, nextPos))
                return true;
        }
        
        return false;
    }
    
    public boolean applyMove(Board board, Cell to) {
        Cell oldCell = getCell();
        Piece eaten = board.getPiece(to);
        
        board.applyMove(this, to);
        
        // TODO: check mise en échec: cancel le move
        if (false) {
            board.applyMove(this, oldCell);
            if (eaten != null)
                board.setPiece(eaten, to);
            return false;
        }
        
        return true;
    }
    
    public void postUpdate() {
    }
}
