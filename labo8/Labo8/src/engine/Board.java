package engine;

import chess.PlayerColor;
import engine.pieces.*;
import engine.utils.Cell;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public interface PieceListener {
        void action(Piece piece, Cell cell);
    }
    
    public interface PromotionListener {
        void action(Piece piece);
    }
    
    public static final int BOARD_SIZE = 8;
    private int turn;
    private final Piece[][] pieces;
    private final List<King> kings;
    private Piece lastPiecePlayed;
    
    private PieceListener onAddPiece;
    private PieceListener onRemovePiece;
    private PromotionListener onPromotion;
    
    public Board() {
        turn = 0;
        pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
        kings = new ArrayList<>();
    }
    
    public void fillBoard() {
        PlayerColor color = PlayerColor.WHITE;
        
        for (int line = 0; line < 9; line += 7, color = PlayerColor.BLACK) {
            // Reine
            addPiece(new Queen(this, new Cell(3, line), color));
            // Roi
            addPiece(new King(this, new Cell(4, line), color));
            // Tours, cavaliers et fous
            for (int i = 0; i < 2; i++) {
                addPiece(new Rook(this, new Cell(i * 7, line), color));
//                addPiece(new Knight(this, new Cell(1 + i * 5, line), color));
//                addPiece(new Bishop(this, new Cell(2 + i * 3, line), color));
            }
            // Pions
            for (int i = 0; i < 8; i++) {
                addPiece(new Pawn(this, new Cell(i, (line * 5 / 7) + 1), color));
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
    
    /**
     * Vérifie que les coordonnées de la pièce sont valides
     *
     * @param cell : case à vérifier
     */
    public void checkCoordsOnBoard(Cell cell) {
        if (cell.getX() >= BOARD_SIZE || cell.getX() < 0 || cell.getY() >= BOARD_SIZE
                || cell.getY() < 0)
            throw new RuntimeException("Coordonnées de la pièce invalides.");
    }
    
    public int getTurn() {
        return turn;
    }
    
    public Piece getLastPiecePlayed() {
        return lastPiecePlayed;
    }
    
    public Piece getPiece(Cell cell) {
        checkCoordsOnBoard(cell);
        return pieces[cell.getX()][cell.getY()];
    }
    
    public void setPiece(Piece p, Cell cell) {
        checkCoordsOnBoard(cell);
        pieces[cell.getX()][cell.getY()] = p;
        p.setCell(cell);
        
        if (p instanceof King)
            kings.add((King) p);
        
        if (onAddPiece != null)
            onAddPiece.action(p, cell);
    }
    
    public void addPiece(Piece p) {
        setPiece(p, p.getCell());
    }
    
    public void removePiece(Cell cell) {
        checkCoordsOnBoard(cell);
        
        Piece piece = pieces[cell.getX()][cell.getY()];
        pieces[cell.getX()][cell.getY()] = null;
        
        if (piece != null) {
            if (piece instanceof King)
                kings.remove((King) piece);
            
            if (onRemovePiece != null)
                onRemovePiece.action(piece, cell);
        }
    }
    
    public void postUpdate(Piece piece) {
        lastPiecePlayed = piece;
        piece.postUpdate();
        turn++;
    }
    
    public PlayerColor currentPlayer() {
        return turn % 2 == 0 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }
    
    public boolean move(Cell from, Cell to) {
        Piece p;
        try {
            p = getPiece(from);
            checkCoordsOnBoard(to);
        } catch (RuntimeException e) {
            return false;
        }
        
        if (p == null || p.getColor() != currentPlayer()) {
            return false;
        }
        
        if (p.checkMove(to) && p.applyMove(to)) {
            postUpdate(p);
            return true;
        }
        
        System.out.println("Last invalid");
        return false;
    }
    
    public void applyMove(Piece p, Cell to) {
        removePiece(p.getCell());
        removePiece(to);
        setPiece(p, to);
    }
    
    public boolean isAttacked(PlayerColor color, Cell cell) {
        for (Piece[] row : pieces)
            for (Piece piece : row)
                if (piece != null && piece.getColor() != color && piece.checkMove(cell))
                    return true;
        
        return false;
    }
    
    public boolean isCheck(PlayerColor color) {
        King king = kings.stream()
                .filter(k -> k.getColor() == color)
                .findAny()
                .orElse(null);
        
        if (king == null)
            return false;
        
        return isAttacked(color, king.getCell());
    }
    
    public void setAddPieceListener(PieceListener onAddPiece) {
        this.onAddPiece = onAddPiece;
    }
    
    public void setRemovePieceListener(PieceListener onRemovePiece) {
        this.onRemovePiece = onRemovePiece;
    }
    
    public void setPromotionListener(PromotionListener onPromotion) {
        this.onPromotion = onPromotion;
    }
    
    public PromotionListener getOnPromotion() {
        return onPromotion;
    }
}
