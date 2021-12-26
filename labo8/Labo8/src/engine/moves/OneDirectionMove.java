package engine.moves;

import engine.Board;
import engine.pieces.FirstMoveSpecificPiece;
import engine.utils.Cell;

public class OneDirectionMove extends Move {
    private final int maxDistance;
    private final Direction boundToDirection;
    private final boolean oneTimeMove;
    
    public OneDirectionMove(FirstMoveSpecificPiece piece, int maxDistance, Direction boundToDirection, boolean oneTimeMove) {
        super(piece);
        this.maxDistance = maxDistance;
        this.boundToDirection = boundToDirection;
        this.oneTimeMove = oneTimeMove;
    }
    
    public OneDirectionMove(FirstMoveSpecificPiece piece, int maxDistance, Direction boundToDirection) {
        this(piece, maxDistance, boundToDirection, false);
    }
    
    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        if (oneTimeMove && ((FirstMoveSpecificPiece) getPiece()).hasMoved())
            return false;
        
        Cell calculatedTo = from.add(boundToDirection.getValue().multiply(maxDistance));
        
        return board.getPiece(to) == null &&
                to.equals(calculatedTo);
    }
}
