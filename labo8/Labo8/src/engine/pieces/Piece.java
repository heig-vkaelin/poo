package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.Move;
import engine.utils.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite permettant de définir la base de toutes les pièces du jeu
 * d'échecs.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public abstract class Piece implements ChessView.UserChoice {
    private final Board board;
    private final PlayerColor color;
    private Cell cell;
    protected List<Move> moves;
    
    /**
     * Crée une nouvelle pièce
     *
     * @param board : plateau de la pièce
     * @param cell  : case de la pièce
     * @param color : couleur de la pièce
     * @throws RuntimeException s'il manque un paramètre
     */
    public Piece(Board board, Cell cell, PlayerColor color) {
        if (board == null || cell == null || color == null)
            throw new RuntimeException("Construction de la pièce invalide");
        
        this.board = board;
        this.cell = cell;
        this.color = color;
        moves = new ArrayList<>();
    }
    
    /**
     * @return le type de la pièce
     */
    public abstract PieceType getType();
    
    /**
     * @return le texte en français représentant la pièce
     */
    public abstract String textValue();
    
    /**
     * @return le plateau de la pièce
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * @return la couleur de la pièce
     */
    public PlayerColor getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return textValue();
    }
    
    /**
     * @return la case de la pièce
     */
    public Cell getCell() {
        return cell;
    }
    
    /**
     * Change la case de la pièce
     *
     * @param cell : nouvelle case
     * @throws RuntimeException si le case est inexistante
     */
    public void setCell(Cell cell) {
        if (cell == null)
            throw new RuntimeException("Case de la pièce invalide.");
        
        this.cell = cell;
    }
    
    /**
     * Vérifie qu'un mouvement peut-être réalisé par la pièce
     *
     * @param to : case de destination souhaitée
     * @return true si le mouvement peut être fait, false sinon
     */
    public boolean checkMove(Cell to) {
        // Si la case de destination est occupée par une pièce de même couleur
        if (to == null || (board.getPiece(to) != null &&
                board.getPiece(to).getColor() == color))
            return false;
        
        for (Move move : moves) {
            if (move.canMove(cell, to))
                return true;
        }
        
        return false;
    }
    
    /**
     * Vérifie qu'un mouvement (légal) peut être appliqué
     *
     * @param to : case de destination souhaitée
     * @return true s'il peut être appliqué, false s'il met le roi du joueur en échec
     */
    public boolean applyMove(Cell to) {
        Cell oldCell = getCell();
        Piece eaten = board.getPiece(to);
        
        board.applyMove(this, to);
        
        // En échec : on annule le move
        if (board.isCheck(color)) {
            board.applyMove(this, oldCell);
            if (eaten != null)
                board.setPiece(eaten, to);
            return false;
        }
        
        return true;
    }
    
    /**
     * Méthode à implémenter dans les pièces devant réaliser des actions après un
     * tour.
     */
    public void postUpdate() {
    }
}
