package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;
import engine.moves.TypeMove;
import engine.pieces.Piece;
import engine.utils.Cell;

public class GameManager implements ChessController {
    private ChessView view;
    private Board board;
    
    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
        newGame();
    }
    
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Cell from = new Cell(fromX, fromY);
        Cell to = new Cell(toX, toY);
        
        TypeMove move = board.move(from, to);
        if (move == TypeMove.INVALID) {
            System.out.println("Move invalide");
            return false;
        }
        
        System.out.println("Move OK");
        
        System.out.println("-----------------------------------------");
        
        // Déplace la pièce
        Piece piece = board.getPiece(from);
        piece.setCell(to);
        
        // Vérifie que le roi du joueur n'est pas en échec à cause du dernier coup
//        if (todo) {
//         Cancel le move
//        return false;
//        }
        
        board.removePiece(toX, toY);
        board.removePiece(fromX, fromY);
        board.setPiece(piece, toX, toY);
        
        view.removePiece(toX, toY);
        view.removePiece(fromX, fromY);
        view.putPiece(piece.getType(), piece.getColor(), to.getX(), to.getY());
        
        board.postUpdate();
        
        // TMP
        view.displayMessage("Aux " + (board.currentPlayer() == PlayerColor.WHITE ? "blancs" : "noirs"));
        
        return true;
    }
    
    @Override
    public void newGame() {
        board = new Board();
        
        // Afficher toutes les pièces
        for (int i = 0; i < Board.BOARD_SIZE; ++i) {
            for (int j = 0; j < Board.BOARD_SIZE; ++j) {
                Piece piece = board.getPiece(i, j);
                if (piece == null)
                    continue;
                Cell cell = piece.getCell();
                view.putPiece(piece.getType(), piece.getColor(), cell.getX(), cell.getY());
            }
        }
    }
}
