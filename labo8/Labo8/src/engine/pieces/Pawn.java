package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.utils.Direction;
import engine.moves.OneDirectionMove;
import engine.utils.Cell;

/**
 * Classe représentant un pion
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
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
            // On stocke le tour actuel si le pion s'est déplacé de deux cases
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
        
        // Vérification de la mise en échec du en-passant
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
        
        // Gestion de la promotion
        if (canBePromoted() && getBoard().getOnPromotion() != null)
            getBoard().getOnPromotion().action(this);
    }
    
    /**
     * @return true si le pion peut être promu, false sinon
     */
    public boolean canBePromoted() {
        return direction == Direction.UP ?
                getCell().getY() == Board.BOARD_SIZE - 1 :
                getCell().getY() == 0;
    }
    
    /**
     * Vérifie si le move en-passant peut être réalisé
     *
     * @param cell : case de destination
     * @return true si le move est légal, false sinon
     */
    public boolean enPassant(Cell cell) {
        Piece piece = getBoard().getLastPiecePlayed();
        int lastTurn = getBoard().getTurn() - 1;
        return piece != null && piece != this && piece.getClass() == Pawn.class &&
                ((Pawn) piece).doubleMoveTurn == lastTurn &&
                piece.getCell().equals(cell);
    }
}
