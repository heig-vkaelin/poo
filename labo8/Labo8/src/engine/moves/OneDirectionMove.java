package engine.moves;

import engine.pieces.FirstMoveSpecificPiece;
import engine.pieces.Piece;
import engine.utils.Cell;
import engine.utils.Direction;

/**
 * Classe représentant un déplacement réduit à une seule direction.
 * Le déplacement peut potentiellement être à usage unique.
 * La gestion des collisions est également gérée.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class OneDirectionMove extends Move {
    private final Direction boundToDirection;
    private final boolean oneTimeMove;
    
    /**
     * Crée un déplacement à une direction
     *
     * @param piece            : pièce concernée
     * @param maxDistance      : potentielle distance maximale
     * @param boundToDirection : unique direction possible
     * @param oneTimeMove      : true si le déplacement est à usage unique
     */
    public OneDirectionMove(Piece piece, int maxDistance, Direction boundToDirection,
                            boolean oneTimeMove) {
        super(piece, maxDistance);
        
        if (boundToDirection == null)
            throw new RuntimeException("Création du OneDirectionMove invalide");
        
        this.boundToDirection = boundToDirection;
        this.oneTimeMove = oneTimeMove;
    }
    
    /**
     * Crée un déplacement à une direction
     *
     * @param piece            : pièce concernée
     * @param maxDistance      : potentielle distance maximale
     * @param boundToDirection : unique direction possible
     */
    public OneDirectionMove(Piece piece, int maxDistance, Direction boundToDirection) {
        this(piece, maxDistance, boundToDirection, false);
    }
    
    @Override
    public boolean canMove(Cell from, Cell to) {
        if (from == null || to == null)
            return false;
        
        // Vérification si le déplacement est à usage unique
        if (oneTimeMove && (!(getPiece() instanceof FirstMoveSpecificPiece) ||
                ((FirstMoveSpecificPiece) getPiece()).hasMoved()))
            return false;
        
        Cell calculatedTo = from.add(
                boundToDirection.getValue().multiply(getMaxDistance())
        );
        
        for (int i = 1; i < getMaxDistance(); ++i) {
            Cell position = from.add(boundToDirection.getValue().multiply(i));
            // Si une case sur le chemin est occupée
            if (getBoard().getPiece(position) != null)
                return false;
        }
        
        return getBoard().getPiece(to) == null && to.equals(calculatedTo);
    }
}
