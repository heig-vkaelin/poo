package engine.moves;

import engine.Board;
import engine.pieces.Piece;
import engine.utils.Cell;

/**
 * Classe abstraite modélisant la base des divers déplacements.
 * Le mouvement peut être limité à un nombre de cases.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public abstract class Move {
    private final Piece piece;
    private final int maxDistance;
    
    /**
     * Crée un déplacement
     *
     * @param piece       : pièce concernée
     * @param maxDistance : potentielle distance maximale
     * @throws RuntimeException si les arguments sont invalides
     */
    public Move(Piece piece, int maxDistance) {
        if (piece == null || maxDistance < 0)
            throw new RuntimeException("Création du Move invalide");
        
        this.piece = piece;
        this.maxDistance = maxDistance;
    }
    
    /**
     * Vérifie qu'une case peut être atteinte grâce au déplacement
     *
     * @param from : case de départ
     * @param to   : case d'arrivée
     * @return true si la case est atteignable, false sinon
     */
    public abstract boolean canMove(Cell from, Cell to);
    
    /**
     * @return la pièce du déplacement
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * Helper permettant de récupérer plus facilement le plateau du déplacement
     *
     * @return le plateau de la pièce du déplacement
     */
    public Board getBoard() {
        return piece.getBoard();
    }
    
    /**
     * @return la distance maximale du déplacement
     */
    public int getMaxDistance() {
        return maxDistance;
    }
}
