package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LinearMove;
import engine.utils.Cell;

/**
 * Classe représentant un roi
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class King extends FirstMoveSpecificPiece {
    private static final int CASTLE_DISTANCE = 2;
    
    public King(Board board, Cell cell, PlayerColor color) {
        super(board, cell, color);
        moves.add(new LinearMove(this, new Cell(0, 1), 1));
        moves.add(new LinearMove(this, new Cell(1, 0), 1));
        moves.add(new LinearMove(this, new Cell(1, 1), 1));
        moves.add(new LinearMove(this, new Cell(1, -1), 1));
    }
    
    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
    
    @Override
    public String textValue() {
        return "Roi";
    }
    
    @Override
    public boolean checkMove(Cell to) {
        return super.checkMove(to) || castle(to);
    }
    
    /**
     * Vérifie si le déplacement est un roque légal
     *
     * @param to : case de destination
     * @return true si le roque a bien été effectué, false sinon
     */
    private boolean castle(Cell to) {
        int deltaY = to.getY() - getCell().getY();
        int deltaX = to.getX() - getCell().getX();
        if (hasMoved() || Math.abs(deltaX) != CASTLE_DISTANCE || deltaY != 0)
            return false;
        
        boolean leftSide = deltaX < 0;
        Cell direction = new Cell(leftSide ? -1 : 1, 0);
        Cell rookCell = new Cell(leftSide ? 0 : Board.BOARD_SIZE - 1, getCell().getY());
        Piece rook = getBoard().getPiece(rookCell);
        Cell rookDestination = getCell().add(direction);
        
        // Vérification de la tour et que le chemin est libre
        if (!(rook instanceof Rook) || ((Rook) rook).hasMoved() ||
                !rook.checkMove(rookDestination))
            return false;
        
        // Vérification que le chemin ne met pas le roi en échec
        Cell initialPosition = getCell();
        for (int i = 0; i <= CASTLE_DISTANCE; i++) {
            Cell position = getCell().add(direction.multiply(i));
            System.out.println("x: " + position.getX() + " y: " + position.getY());
            getBoard().setPiece(this, position);
            
            boolean isAttacked = getBoard().isAttacked(getColor(), position);
            getBoard().removePiece(position);
            if (isAttacked) {
                getBoard().setPiece(this, initialPosition);
                return false;
            }
        }
        
        // Roque appliqué
        getBoard().applyMove(rook, rookDestination);
        rook.postUpdate();
        
        return true;
    }
}
