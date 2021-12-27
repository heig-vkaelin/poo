package engine.moves;

import engine.Board;
import engine.pieces.Piece;
import engine.utils.Cell;

public class LinearMove extends Move {
    protected final Cell direction;
    private final int maxDistance;
    private final boolean flyOver;
    
    public LinearMove(Piece piece, Cell direction, int maxDistance, boolean flyOver) {
        super(piece);
        this.direction = direction;
        this.maxDistance = maxDistance;
        this.flyOver = flyOver;
    }
    
    public LinearMove(Piece piece, Cell direction, int maxDistance) {
        this(piece, direction, maxDistance, false);
    }
    
    public LinearMove(Piece piece, Cell direction) {
        this(piece, direction, Integer.MAX_VALUE, false);
    }
    
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
        
        System.out.println("Distance: " + distance);
        System.out.println("sign: " + sign);
        
        if (distance == 0 || distance > maxDistance)
            return false;
        
        if (!flyOver) {
            for (int i = 1; i < distance; ++i) {
                Cell position = from.add(direction.multiply(i * sign));
                // Si une case sur le chemin est occupée
                if (getBoard().getPiece(position) != null) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
