package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
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
        Piece piece = board.getPiece(fromX, fromY);
        Cell cell = new Cell(toX, toY);
        piece.setCell(cell);
        board.removePiece(toX, toY);
        
        view.removePiece(toX, toY);
        view.removePiece(fromX, fromY);
        view.putPiece(piece.getType(), piece.getColor(), cell.getX(), cell.getY());
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
