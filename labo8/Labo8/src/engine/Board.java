package engine;

import chess.PlayerColor;
import engine.moves.TypeMove;
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
        
        for (int line = 0; line < 9; line += 7, color = PlayerColor.BLACK) {
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
            // TODO: add back si tu veux les pions
//            for (int i = 0; i < 8; i++) {
//                pieces[i][(line * 5 / 7) + 1] = new Pawn(new Cell(i, (line * 5 / 7) + 1), color);
//            }
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
    
    /**
     * Vérifie que les coordonnées de la pièce sont valides
     *
     * @param x : ligne de la pièce
     * @param y : colonne de la pièce
     */
    public void checkCoordsOnBoard(int x, int y) {
        if (x >= BOARD_SIZE || x < 0 || y >= BOARD_SIZE || y < 0)
            throw new RuntimeException("Coordonnées de la pièce invalides.");
    }
    
    public Piece getPiece(int x, int y) {
        checkCoordsOnBoard(x, y);
        return pieces[x][y];
    }
    
    public Piece getPiece(Cell cell) {
        return getPiece(cell.getX(), cell.getY());
    }
    
    public void setPiece(Piece p, int x, int y) {
        checkCoordsOnBoard(x, y);
        pieces[x][y] = p;
    }
    
    public void removePiece(int x, int y) {
        checkCoordsOnBoard(x, y);
        pieces[x][y] = null;
    }
    
    public void postUpdate() {
        turn++;
    }
    
    public PlayerColor currentPlayer() {
        return turn % 2 == 0 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }
    
    public TypeMove move(Cell from, Cell to) {
        Piece p;
        try {
            p = getPiece(from);
            checkCoordsOnBoard(to.getX(), to.getY());
        } catch (RuntimeException e) {
            System.out.println("Not valid coords");
            return TypeMove.INVALID;
        }
        
        if (p == null) {
            System.out.println("No piece");
            return TypeMove.INVALID;
        }
        
        return p.checkMove(this, to);
    }
}
