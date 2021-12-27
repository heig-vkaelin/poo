package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.utils.Direction;
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
    public String textValue() {
        return "Pion";
    }
    
    @Override
    public boolean checkMove(Board board, Cell nextPos) {
        if (super.checkMove(board, nextPos)) {
            if (Math.abs(nextPos.getY() - getCell().getY()) == 2)
                doubleMoveTurn = board.getTurn();
            return true;
        }
        
        int deltaX = nextPos.getX() - getCell().getX();
        int deltaY = nextPos.getY() - getCell().getY();
        
        // - Manger en diagonale
        if (Math.abs(deltaX) == 1 && deltaY == direction.intValue()) {
            if (board.getPiece(nextPos) != null)
                return true;
        }
        
        // TODO:
        // - En passant
        if (enPassant(board, new Cell(nextPos.getX(), getCell().getY()))) {
            return true;
        }
        
        // - Promotion
        
        return false;
    }
    
    @Override
    public boolean applyMove(Board board, Cell to) {
        Cell oldCell = getCell();
        Piece piece = board.getLastPiecePlayed();
        
        if (enPassant(board, new Cell(to.getX(), oldCell.getY()))) {
            board.applyMove(this, to);
            board.removePiece(piece.getCell());
            
            // TODO: check mise en échec: cancel les moves
            if (false) {
                board.applyMove(this, oldCell);
                board.setPiece(piece, piece.getCell());
                return false;
            }
            return true;
        }
        
        return super.applyMove(board, to);
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
        return piece != null && piece.getClass() == Pawn.class &&
                ((Pawn) piece).doubleMoveTurn == lastTurn &&
                piece.getCell().equals(cell);
    }
}
