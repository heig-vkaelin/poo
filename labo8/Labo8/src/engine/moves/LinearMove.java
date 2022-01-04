package engine.moves;

import engine.pieces.Piece;
import engine.utils.Cell;

/**
 * Classe représentant un déplacement linéaire dans un plan 2D.
 * La gestion des collisions est potentiellement gérée.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class LinearMove extends Move {
    protected final Cell direction;
    private final boolean flyOver;
    
    /**
     * Crée un déplacement linéaire
     *
     * @param piece       : pièce concernée
     * @param direction   : direction du déplacement
     * @param maxDistance : potentielle distance maximale
     * @param flyOver     : indique si la pièce prend en compte les collisions ou pas
     * @throws RuntimeException si les arguments sont invalides
     */
    public LinearMove(Piece piece, Cell direction, int maxDistance, boolean flyOver) {
        super(piece, maxDistance);
        
        if (direction == null)
            throw new RuntimeException("Création du LinearMove invalide");
        
        this.direction = direction;
        this.flyOver = flyOver;
    }
    
    /**
     * Crée un déplacement linéaire
     *
     * @param piece       : pièce concernée
     * @param direction   : direction du déplacement
     * @param maxDistance : potentielle distance maximale
     */
    public LinearMove(Piece piece, Cell direction, int maxDistance) {
        this(piece, direction, maxDistance, false);
    }
    
    /**
     * Crée un déplacement linéaire
     *
     * @param piece     : pièce concernée
     * @param direction : direction du déplacement
     */
    public LinearMove(Piece piece, Cell direction) {
        this(piece, direction, Integer.MAX_VALUE, false);
    }
    
    /**
     * Retourne la distance entre deux cases
     *
     * @param fromTo : case représentant la soustraction du départ à l'arrivée
     * @return la distance entre les deux cases, ou 0 si la case est inaccessible
     */
    private int getDistance(Cell fromTo) {
        int distance = 0;
        
        if (direction.reachable(fromTo))
            distance = Math.max(Math.abs(fromTo.getX()), Math.abs(fromTo.getY()));
        
        return distance;
    }
    
    @Override
    public boolean canMove(Cell from, Cell to) {
        Cell fromTo = to.subtract(from);
        int distance = getDistance(fromTo);
        int sign = direction.sameDirection(fromTo) ? 1 : -1;
        
        if (distance == 0 || distance > getMaxDistance())
            return false;
        
        // Gestion des collisions
        if (!flyOver) {
            for (int i = 1; i < distance; ++i) {
                Cell position = from.add(direction.multiply(i * sign));
                // Si une case sur le chemin est occupée
                if (getBoard().getPiece(position) != null)
                    return false;
            }
        }
        
        return true;
    }
}
