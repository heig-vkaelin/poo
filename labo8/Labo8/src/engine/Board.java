package engine;

import chess.PlayerColor;
import engine.pieces.*;
import engine.utils.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modélisant un plateau virtuel du jeu d'échecs.
 * Elle s'occupe notamment de stocker et modifier les positions des différentes
 * pièces.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
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
    
    /**
     * Constructeur de base initialisant les différentes structures
     */
    public Board() {
        pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
        kings = new ArrayList<>();
    }
    
    /**
     * Remplit le tableau avec la position habituelle des différentes pièces
     */
    public void fillBoard() {
        PlayerColor color = PlayerColor.WHITE;
        int line = 0, pawnLine = 1;
        for (int team = 0; team < 2; team++) {
            addPiece(new Rook(this, new Cell(0, line), color));
            addPiece(new Knight(this, new Cell(1, line), color));
            addPiece(new Bishop(this, new Cell(2, line), color));
            addPiece(new Queen(this, new Cell(3, line), color));
            addPiece(new King(this, new Cell(4, line), color));
            addPiece(new Bishop(this, new Cell(5, line), color));
            addPiece(new Knight(this, new Cell(6, line), color));
            addPiece(new Rook(this, new Cell(7, line), color));
            
            // Pawns
            for (int xPawn = 0; xPawn < BOARD_SIZE; xPawn++)
                addPiece(new Pawn(this, new Cell(xPawn, pawnLine), color));
            
            color = PlayerColor.BLACK;
            line = 7;
            pawnLine = 6;
        }
    }
    
    /**
     * Vérifie que les coordonnées de la case sont valides
     *
     * @param cell : case à vérifier
     * @throws RuntimeException si la case est invalide
     */
    public void checkCoordsOnBoard(Cell cell) {
        if (cell == null || cell.getX() >= BOARD_SIZE || cell.getX() < 0 ||
                cell.getY() >= BOARD_SIZE || cell.getY() < 0)
            throw new RuntimeException("Coordonnées de la pièce invalides.");
    }
    
    /**
     * @return le tour actuel
     */
    public int getTurn() {
        return turn;
    }
    
    /**
     * @return la dernière pièce jouée
     */
    public Piece getLastPiecePlayed() {
        return lastPiecePlayed;
    }
    
    /**
     * @param cell : case souhaitée
     * @return la pièce à la case souhaitée ou null
     * @throws RuntimeException si la case est invalide
     */
    public Piece getPiece(Cell cell) {
        checkCoordsOnBoard(cell);
        return pieces[cell.getX()][cell.getY()];
    }
    
    /**
     * Ajoute la pièce à la case souhaitée
     *
     * @param piece : pièce à ajouter
     * @param cell  : case souhaitée
     * @throws RuntimeException si la case est invalide
     */
    public void setPiece(Piece piece, Cell cell) {
        checkCoordsOnBoard(cell);
        
        pieces[cell.getX()][cell.getY()] = piece;
        piece.setCell(cell);
        
        if (piece instanceof King)
            kings.add((King) piece);
        
        if (onAddPiece != null)
            onAddPiece.action(piece, cell);
    }
    
    /**
     * Petite fonction helper permettant d'ajouter une pièce à sa case actuelle
     *
     * @param piece : pièce à ajouter
     * @throws RuntimeException si la case est invalide
     */
    public void addPiece(Piece piece) {
        setPiece(piece, piece.getCell());
    }
    
    /**
     * Supprime une pièce du tableau
     *
     * @param cell : case de la pièce à supprimer
     * @throws RuntimeException si la case est invalide
     */
    public void removePiece(Cell cell) {
        Piece piece = getPiece(cell);
        pieces[cell.getX()][cell.getY()] = null;
        
        if (piece != null) {
            if (piece instanceof King)
                kings.remove((King) piece);
            
            if (onRemovePiece != null)
                onRemovePiece.action(piece, cell);
        }
    }
    
    /**
     * @return le joueur à qui c'est le tour de jouer
     */
    public PlayerColor currentPlayer() {
        return turn % 2 == 0 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }
    
    /**
     * Vérifie que le déplacement d'une pièce peut se faire. Si c'est le cas,
     * il est réalisé.
     *
     * @param from : case de départ
     * @param to   : case d'arrivée
     * @return true si le mouvement a pu être fait, false sinon
     */
    public boolean move(Cell from, Cell to) {
        Piece p;
        try {
            p = getPiece(from);
            checkCoordsOnBoard(to);
        } catch (RuntimeException e) {
            return false;
        }
        
        if (p == null || p.getColor() != currentPlayer())
            return false;
        
        if (p.checkMove(to) && p.applyMove(to)) {
            postUpdate(p);
            return true;
        }
        
        return false;
    }
    
    /**
     * Applique le mouvement d'une pièce à une destination
     *
     * @param piece : pièce à déplacer
     * @param to    : case d'arrivée
     */
    public void applyMove(Piece piece, Cell to) {
        removePiece(piece.getCell());
        removePiece(to);
        setPiece(piece, to);
    }
    
    /**
     * Vérifie si une pièce est actuellement menacée/attaquée
     *
     * @param color : couleur de la pièce à vérifier
     * @param cell  : case de la pièce à vérifier
     * @return true si la pièce est attaquée, false sinon
     */
    public boolean isAttacked(PlayerColor color, Cell cell) {
        for (Piece[] row : pieces)
            for (Piece piece : row)
                if (piece != null && piece.getColor() != color && piece.checkMove(cell))
                    return true;
        
        return false;
    }
    
    /**
     * Vérifie si un joueur est actuellement en échec
     *
     * @param color : la couleur du joueur
     * @return true si le joueur est en échec, false sinon
     */
    public boolean isCheck(PlayerColor color) {
        King king = kings.stream()
                .filter(k -> k.getColor() == color)
                .findAny()
                .orElse(null);
        
        if (king == null)
            return false;
        
        return isAttacked(color, king.getCell());
    }
    
    /**
     * Applique les changements nécessaires à la fin d'un tour
     *
     * @param piece : pièce jouée
     */
    public void postUpdate(Piece piece) {
        lastPiecePlayed = piece;
        piece.postUpdate();
        turn++;
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
