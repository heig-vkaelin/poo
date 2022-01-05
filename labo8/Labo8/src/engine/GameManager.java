package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;
import engine.utils.Cell;

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
     * Met à jour le message de la vue
     */
    private void updateDisplayMessage() {
        if (view == null || board == null)
            return;
        
        StringBuilder msg = new StringBuilder(
                "Aux " + (board.currentPlayer() == PlayerColor.WHITE ? "blancs" : "noirs")
        );
        if (board.isCheck(board.currentPlayer()))
            msg.append(" CHECK!");
        view.displayMessage(msg.toString());
    }
    
    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
        newGame();
    }
    
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        if (board == null)
            return false;
        
        Cell from = new Cell(fromX, fromY);
        Cell to = new Cell(toX, toY);
        
        if (!board.move(from, to)) {
            System.out.println("Move invalide");
            updateDisplayMessage();
            return false;
        }
        
        System.out.println("Move OK");
        System.out.println("-----------------------------------------");
        
        updateDisplayMessage();
        
        return true;
    }
    
    @Override
    public void newGame() {
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
        
        // Setup initial des pièces
        board.fillBoard();
        updateDisplayMessage();
    }
}
