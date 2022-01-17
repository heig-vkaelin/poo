package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;
import engine.utils.Cell;

import java.util.Objects;

/**
 * Classe principale de la gestion du jeu d'échecs.
 * Elle s'occupe de démarrer le jeu ainsi qu'écouter et répondre aux événements de
 * la view.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class GameManager implements ChessController {
    private ChessView view;
    private Board board;
    
    /**
     * @return le plateau de jeu
     */
    protected Board getBoard() {
        return board;
    }
    
    /**
     * Met à jour le message de la vue
     */
    private void updateDisplayMessage() {
        if (view == null || board == null)
            return;
    
        String color = board.currentPlayer() == PlayerColor.WHITE ? "blancs" : "noirs";
        StringBuilder msg = new StringBuilder("Aux " + color);
        
        if (board.isCheck(board.currentPlayer())) {
            if (board.isCheckMate(board.currentPlayer())) {
                msg.setLength(0);
                String winner = board.currentPlayer() == PlayerColor.WHITE ?
                        "noirs" : "blancs";
                msg.append("CHECKMATE! Les ").append(winner).append(" ont gagnés!");
            } else {
                msg.append(" CHECK!");
            }
        }
        
        view.displayMessage(msg.toString());
    }
    
    /**
     * Initialise le plateau, écoute les différents événements
     */
    private void initBoard() {
        board = new Board();
        
        // Events listeners
        board.setAddPieceListener((piece, cell) -> {
            if (view != null)
                view.putPiece(piece.getType(), piece.getColor(), cell.getX(), cell.getY());
        });
        
        board.setRemovePieceListener((piece, cell) -> {
            if (view != null)
                view.removePiece(cell.getX(), cell.getY());
        });
        
        board.setPromotionListener((piece) -> {
            Cell cell = piece.getCell();
            PlayerColor color = piece.getColor();
            Piece[] choices = {
                    new Queen(board, cell, color),
                    new Knight(board, cell, color),
                    new Rook(board, cell, color),
                    new Bishop(board, cell, color)
            };
            
            Piece userChoice;
            while ((userChoice = view.askUser("Promotion",
                    "Choisir une pièce pour la promotion", choices)) == null) {
            }
            board.removePiece(cell);
            board.setPiece(userChoice, cell);
        });
    }
    
    @Override
    public void start(ChessView view) {
        Objects.requireNonNull(view, "View invalide");
        this.view = view;
        view.startView();
        initBoard();
        board.fillBoard();
        updateDisplayMessage();
    }
    
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        if (board == null)
            return false;
        
        Cell from = new Cell(fromX, fromY);
        Cell to = new Cell(toX, toY);
        
        boolean canMove = board.move(from, to);
        updateDisplayMessage();
        
        return canMove;
    }
    
    @Override
    public void newGame() {
        board.resetBoard();
        board.fillBoard();
        updateDisplayMessage();
    }
}
