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
        
        if (!board.move(from, to)) {
            System.out.println("Move invalide");
            return false;
        }
        
        System.out.println("Move OK");
        System.out.println("-----------------------------------------");
        
        // TMP
        view.displayMessage("Aux " + (board.currentPlayer() == PlayerColor.WHITE ? "blancs" : "noirs"));
        
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
        
        board.fillBoard();
    }
}
