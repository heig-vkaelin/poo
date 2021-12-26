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
        // Si ce n'est pas le tour du joueur
        if (color != board.currentPlayer())
            return TypeMove.INVALID;
        
        // Si la case de destination est occupée par une pièce de même couleur
        if (board.getPiece(nextPos) != null && board.getPiece(nextPos).getColor() == color) {
            return TypeMove.INVALID;
        }
        
        for (Move move : moves) {
            if (move.canMove(board, cell, nextPos)) {
                return TypeMove.VALID;
            }
        }
        return TypeMove.INVALID;
    }
    
    public void postUpdate() {
    }
}
