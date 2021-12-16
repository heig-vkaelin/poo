package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public abstract class Piece {
    private PlayerColor color;
    private Cell cell;
    
    public Piece(Cell cell, PlayerColor color) {
        this.cell = cell;
        this.color = color;
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
}
