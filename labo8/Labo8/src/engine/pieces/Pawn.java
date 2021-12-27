package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.utils.Direction;
import engine.moves.OneDirectionMove;
import engine.utils.Cell;

public class Pawn extends FirstMoveSpecificPiece {
    private final Direction direction;
    private int doubleMoveTurn;
    
    public Pawn(Board board, Cell cell, PlayerColor color) {
        super(board, cell, color);
        direction = color == PlayerColor.WHITE ? Direction.UP : Direction.DOWN;
        moves.add(new OneDirectionMove(this, 1, direction));
        moves.add(new OneDirectionMove(this, 2, direction, true));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
    
    @Override
    public String textValue() {
        return "Pion";
    }
    
    @Override
    public boolean checkMove(Cell to) {
        if (super.checkMove(to)) {
            if (Math.abs(to.getY() - getCell().getY()) == 2)
                doubleMoveTurn = getBoard().getTurn();
            return true;
        }
        
        int deltaX = to.getX() - getCell().getX();
        int deltaY = to.getY() - getCell().getY();
        
        // Manger en diagonale
        if (Math.abs(deltaX) == 1 && deltaY == direction.intValue()) {
            if (getBoard().getPiece(to) != null)
                return true;
        }
        
        // En passant
        return enPassant(new Cell(to.getX(), getCell().getY()));
    }
    
    @Override
    public boolean applyMove(Cell to) {
        Cell oldCell = getCell();
        Piece piece = getBoard().getLastPiecePlayed();
        
        if (enPassant(new Cell(to.getX(), oldCell.getY()))) {
            getBoard().applyMove(this, to);
            getBoard().removePiece(piece.getCell());
            
            // En échec : on annule les moves
            if (getBoard().isCheck(getColor())) {
                getBoard().applyMove(this, oldCell);
                getBoard().setPiece(piece, piece.getCell());
                return false;
            }
            return true;
        }
        
        return super.applyMove(to);
    }
    
    @Override
    public void postUpdate() {
        super.postUpdate();
        
        if (canBePromoted() && getBoard().getOnPromotion() != null) {
            getBoard().getOnPromotion().action(this);
        }
    }
    
    public boolean canBePromoted() {
        return this.getColor() == PlayerColor.WHITE ?
                this.getCell().getY() == Board.BOARD_SIZE - 1 : this.getCell().getY() == 0;
    }
    
    public boolean enPassant(Cell cell) {
        Piece piece = getBoard().getLastPiecePlayed();
        int lastTurn = getBoard().getTurn() - 1;
        return piece != null && piece != this && piece.getClass() == Pawn.class &&
                ((Pawn) piece).doubleMoveTurn == lastTurn &&
                piece.getCell().equals(cell);
    }
}
