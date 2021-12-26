package engine.moves;

import engine.Board;
import engine.utils.Cell;

public class LinearMove extends Move {
    private final Cell direction;
    private final int maxDistance;
    private final boolean flyOver;
    
    public LinearMove(Cell direction, int maxDistance, boolean flyOver) {
        this.direction = direction;
        this.maxDistance = maxDistance;
        this.flyOver = flyOver;
    }
    
    public LinearMove(Cell direction, int maxDistance) {
        this(direction, maxDistance, false);
    }
    
    public LinearMove(Cell direction) {
        this(direction, Integer.MAX_VALUE, false);
    }
    
    private int getDistance(Cell fromTo) {
        int distance = 0;
        
        if (direction.isCollinear(fromTo))
            distance = Math.max(Math.abs(fromTo.getX()), Math.abs(fromTo.getY()));
        
        return distance;
    }
    
    
    @Override
    public boolean isValid(Cell from, Cell to) {
        return from.getX() == to.getX() || from.getY() == to.getY();
    }
    
    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        Cell fromTo = to.subtract(from);
        int distance = getDistance(fromTo);
        int sign = direction.sameDirection(fromTo) ? 1 : -1;
        
        System.out.println("Distance: " + distance);
        
        if (distance == 0 || distance > maxDistance)
            return false;
        
        if (!flyOver) {
            for (int i = 1; i < distance; ++i) {
                Cell position = from.add(direction.multiply(i * sign));
                // Si une case sur le chemin est occupée
                if (board.getPiece(position) != null) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
