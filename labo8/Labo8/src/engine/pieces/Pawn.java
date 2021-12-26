package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Direction;
import engine.moves.OneDirectionMove;
import engine.moves.TypeMove;
import engine.utils.Cell;

public class Pawn extends FirstMoveSpecificPiece {
    private final Direction direction;
    private int doubleMoveTurn;
    
    public Pawn(Cell cell, PlayerColor color) {
        super(cell, color);
        direction = color == PlayerColor.WHITE ? Direction.UP : Direction.DOWN;
        moves.add(new OneDirectionMove(this, 1, direction));
        moves.add(new OneDirectionMove(this, 2, direction, true));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
    
    @Override
    public TypeMove checkMove(Board board, Cell nextPos) {
        if (super.checkMove(board, nextPos) == TypeMove.VALID) {
            if (Math.abs(nextPos.getY() - getCell().getY()) == 2)
                doubleMoveTurn = board.getTurn();
            return TypeMove.VALID;
        }
        
        int deltaX = nextPos.getX() - getCell().getX();
        int deltaY = nextPos.getY() - getCell().getY();
        
        // - Manger en diagonale
        if (Math.abs(deltaX) == 1 && deltaY == direction.intValue()) {
            if (board.getPiece(nextPos) != null)
                return TypeMove.VALID;
        }
        
        // TODO:
        // - En passant
        if (enPassant(board, new Cell(nextPos.getX(), getCell().getY()))) {
            return TypeMove.VALID;
        }
        
        // - Promotion
        
        return TypeMove.INVALID;
    }
    
    @Override
    public void postUpdate() {
        super.postUpdate();
    }
    
    public boolean canBePromoted() {
        return this.getColor() == PlayerColor.WHITE ?
                this.getCell().getY() == Board.BOARD_SIZE - 1 : this.getCell().getY() == 0;
    }
    
    public boolean enPassant(Board board, Cell cell) {
        Piece piece = board.getLastPiecePlayed();
        int lastTurn = board.getTurn() - 1;
        return piece.getClass() == Pawn.class &&
                ((Pawn) piece).doubleMoveTurn == lastTurn &&
                piece.getCell().equals(cell);
    }
}
