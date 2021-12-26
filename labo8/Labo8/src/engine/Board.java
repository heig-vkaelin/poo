package engine;

import chess.PlayerColor;
import engine.pieces.*;
import engine.utils.Cell;

public class Board {
    public static final int BOARD_SIZE = 8;
    private int turn;
    private Piece[][] pieces;
    
    public Board() {
        turn = 0;
        setStartingPositions();
    }
    
    private void setStartingPositions() {
        pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
        PlayerColor color = PlayerColor.WHITE;

        for(int line = 0; line < 9; line += 7, color = PlayerColor.BLACK){
            // Reine
            pieces[3][line] = new Queen(new Cell(3, line), color);
            // Roi
            pieces[4][line] = new King(new Cell(4, line), color);
            // Tours, cavaliers et fous
            for (int i = 0; i < 2; i++) {
                pieces[i * 7][line] = new Rook(new Cell(i * 7, line), color);
                pieces[1 + i * 5][line] = new Knight(new Cell(1 + i * 5, line), color);
                pieces[2 + i * 3][line] = new Bishop(new Cell(2 + i * 3, line), color);
            }
            // Pions
            for (int i = 0; i < 8; i++) {
                pieces[i][(line * 5 / 7) + 1] = new Pawn(new Cell(i, (line * 5 / 7) + 1), color);
            }
        }
        /*// Queens
        pieces[3][0] = new Queen(new Cell(3, 0), PlayerColor.WHITE);
        pieces[3][7] = new Queen(new Cell(3, 7), PlayerColor.BLACK);
        
        // Kings
        pieces[4][0] = new King(new Cell(4, 0), PlayerColor.WHITE);
        pieces[4][7] = new King(new Cell(4, 7), PlayerColor.BLACK);
        
        // Toutes les autres pièces spéciales
        for (int i = 0; i < 2; i++) {
            pieces[i * 7][0] = new Rook(new Cell(i * 7, 0), PlayerColor.WHITE);
            pieces[i * 7][7] = new Rook(new Cell(i * 7, 7), PlayerColor.BLACK);
            
            pieces[1 + i * 5][0] = new Knight(new Cell(1 + i * 5, 0), PlayerColor.WHITE);
            pieces[1 + i * 5][7] = new Knight(new Cell(1 + i * 5, 7), PlayerColor.BLACK);
            
            pieces[2 + i * 3][0] = new Bishop(new Cell(2 + i * 3, 0), PlayerColor.WHITE);
            pieces[2 + i * 3][7] = new Bishop(new Cell(2 + i * 3, 7), PlayerColor.BLACK);
        }
        
        // Pawns
        for (int i = 0; i < 8; i++) {
            pieces[i][1] = new Pawn(new Cell(i, 1), PlayerColor.WHITE);
            pieces[i][6] = new Pawn(new Cell(i, 6), PlayerColor.BLACK);
        }*/
    }
    
    public Piece getPiece(int x, int y) {
        return pieces[x][y];
    }
    
    public void setPiece(Piece p, int x, int y) {
        pieces[x][y] = p;
    }
    
    public void removePiece(int x, int y) {
        pieces[x][y] = null;
    }
    
    public void postUpdate() {
        turn++;
    }
    
    public PlayerColor currentPlayer() {
        return turn % 2 == 0 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }
}
