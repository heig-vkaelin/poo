package engine.pieces;

import chess.PlayerColor;
import engine.Board;
import engine.utils.Cell;

/**
 * Classe abstraite permettant d'ajouter la gestion de premier coup spécifique à
 * certaines pièces.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public abstract class FirstMoveSpecificPiece extends Piece {
    private boolean hasMoved;
    
    public FirstMoveSpecificPiece(Board board, Cell cell, PlayerColor color) {
        super(board, cell, color);
        hasMoved = false;
    }
    
    /**
     * @return true si la pièce a déjà bougé, false sinon
     */
    public boolean hasMoved() {
        return hasMoved;
    }
    
    /**
     * Indique à la fin du tour que la pièce a déjà bougé
     */
    public void postUpdate() {
        hasMoved = true;
    }
}
