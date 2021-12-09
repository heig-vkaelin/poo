package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;

public abstract class Piece {
    private PieceType type;
    private PlayerColor color;
    private Cell cell;
    
    public Piece(PieceType type, Cell cell, PlayerColor color) {
        this.type = type;
        this.cell = cell;
        this.color = color;
    }
    
    public PieceType getType() {
        return type;
    }
    
    public PlayerColor getColor() {
        return color;
    }
    
    public Cell getCell() {
        return cell;
    }
}
