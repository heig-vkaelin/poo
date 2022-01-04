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
     * Met des pions de la couleur voulue tout autour de la position donnée
     * @param pos position que l'on souhaite entourer
     * @param color couleur de pions
     */
    public void pawnAroundPos(Cell pos, PlayerColor color){
        for(int i = pos.getX() - 1; i < pos.getX() + 2; i++){
            for(int j = pos.getY() - 1; j < pos.getY() + 2; j++){
                if(i == pos.getX() && j == pos.getY()){
                    continue;
                }
                addPiece(new Pawn(this, new Cell(i, j), color));
            }
        }
    }

    // Cette fonction permet de tester rapidement si le cavalier peut sauter par dessus des pions de sa couleur
    // ou de la couleur adverse ainsi que voir qu'il peut sauter par dessus des pions et manger.

    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des Cavaliers
     */
    public void testKnight(){
        Cell knight1 = new Cell(5, 5);
        Cell knight2 = new Cell(2, 5);
        Cell knight3 = new Cell(5, 2);
        Cell knight4 = new Cell(2, 2);
        addPiece(new Knight(this, knight1, PlayerColor.WHITE));
        pawnAroundPos(knight1, PlayerColor.BLACK);
        addPiece(new Knight(this, knight2, PlayerColor.WHITE));
        pawnAroundPos(knight2, PlayerColor.WHITE);
        addPiece(new Knight(this, knight3, PlayerColor.BLACK));
        pawnAroundPos(knight3, PlayerColor.WHITE);
        addPiece(new Knight(this, knight4, PlayerColor.BLACK));
        pawnAroundPos(knight4, PlayerColor.BLACK);
    }

    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des Tours.
     */
    public void testRook(){
        Cell rookPos1 = new Cell(5, 4);
        Cell rookPos2 = new Cell(2, 4);
        addPiece(new Rook(this, new Cell(0,0), PlayerColor.WHITE));
        addPiece(new Rook(this, new Cell(7,7), PlayerColor.BLACK));
        addPiece(new Rook(this, rookPos1, PlayerColor.WHITE));
        addPiece(new Rook(this, rookPos2, PlayerColor.WHITE));
        pawnAroundPos(rookPos1, PlayerColor.WHITE);
        pawnAroundPos(rookPos2, PlayerColor.BLACK);
    }

    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des Fous.
     */
    public void testBishop(){
        Cell bishopPos1 = new Cell(5, 4);
        Cell bishopPos2 = new Cell(2, 4);
        addPiece(new Bishop(this, bishopPos1, PlayerColor.WHITE));
        addPiece(new Bishop(this, bishopPos2, PlayerColor.WHITE));
        pawnAroundPos(bishopPos1, PlayerColor.WHITE);
        pawnAroundPos(bishopPos2, PlayerColor.BLACK);
    }

    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des Pions.
     */
    public void testPawn(){
        for(int i = 0; i < 7; i++){
            addPiece(new Pawn(this, new Cell(i, 1), PlayerColor.WHITE));
            addPiece(new Knight(this, new Cell(i, 2), (i % 2 == 0) ? PlayerColor.WHITE : PlayerColor.BLACK));
        }
        addPiece(new Pawn(this, new Cell(7, 1), PlayerColor.BLACK));
        addPiece(new Pawn(this, new Cell(4, 6), PlayerColor.BLACK));
        addPiece(new Pawn(this, new Cell(3, 4), PlayerColor.WHITE));
    }

    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des Reines.
     */
    public void testQueen(){
        Cell queenPos = new Cell(5, 4);
        Cell queenPos2 = new Cell(2, 4);
        addPiece(new Queen(this, queenPos, PlayerColor.WHITE));
        addPiece(new Queen(this, queenPos2, PlayerColor.WHITE));
        pawnAroundPos(queenPos, PlayerColor.BLACK);
        pawnAroundPos(queenPos2, PlayerColor.WHITE);
    }

    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des Rois.
     */
    public void testKing(){
        Cell kingPos = new Cell(4, 4);
        addPiece(new King(this, kingPos, PlayerColor.WHITE));
        pawnAroundPos(kingPos, PlayerColor.BLACK);
    }

    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement du rock.
     */
    public void testCastle(){
        addPiece(new King(this, new Cell(4, 0), PlayerColor.WHITE));
        addPiece(new Rook(this, new Cell(7, 0), PlayerColor.WHITE));
        addPiece(new Rook(this, new Cell(0, 0), PlayerColor.WHITE));
        addPiece(new Queen(this, new Cell(3, 7), PlayerColor.BLACK));
    }
    
    /**
     * Remplit le tableau avec la position habituelle des différentes pièces
     */
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
                addPiece(new Knight(this, new Cell(1 + i * 5, line), color));
                addPiece(new Bishop(this, new Cell(2 + i * 3, line), color));
            }
            // Pions
            for (int i = 0; i < 8; i++) {
                addPiece(new Pawn(this, new Cell(i, (line * 5 / 7) + 1), color));
            }
        }
        /*
        // Queens
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
        }
        */
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
