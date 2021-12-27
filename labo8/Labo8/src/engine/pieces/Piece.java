package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Move;
import engine.utils.Cell;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements ChessView.UserChoice {
    private final Board board;
    private final PlayerColor color;
    private Cell cell;
    protected List<Move> moves;
    
    public Piece(Board board, Cell cell, PlayerColor color) {
        this.board = board;
        this.cell = cell;
        this.color = color;
        moves = new ArrayList<>();
    }
    
    public abstract PieceType getType();
    
    public abstract String textValue();
    
    public Board getBoard() {
        return board;
    }
    
    public PlayerColor getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return textValue();
    }
    
    public Cell getCell() {
        return cell;
    }
    
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    
    public boolean checkMove(Cell to) {
        // Si la case de destination est occupée par une pièce de même couleur
        if (board.getPiece(to) != null && board.getPiece(to).getColor() == color) {
            return false;
        }
        
        for (Move move : moves) {
            if (move.canMove(cell, to))
                return true;
        }
        
        return false;
    }
    
    public boolean applyMove(Cell to) {
        Cell oldCell = getCell();
        Piece eaten = board.getPiece(to);
        
        board.applyMove(this, to);
        
        // En échec : on annule le move
        if (board.isCheck(color)) {
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
