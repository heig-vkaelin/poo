package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
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
        Piece piece = board.getPiece(fromX, fromY);
        Cell cell = new Cell(toX, toY);
        
        // Aucune pièce ou pas la bonne couleur
        if (piece == null || piece.getColor() != board.currentPlayer())
            return false;
        
        TypeMove move = piece.checkMove(board, cell);
        if (move == TypeMove.INVALID)
            return false;
        
        // Déplace la pièce
        piece.setCell(cell);
        
        // Vérifie que le roi du joueur n'est pas en échec à cause du dernier coup
//        if (todo) {
//         Cancel le move
//        return false;
//        }
        
        piece.setCell(cell);
        board.removePiece(toX, toY);
        board.setPiece(piece, toX, toY);
        
        view.removePiece(toX, toY);
        view.removePiece(fromX, fromY);
        view.putPiece(piece.getType(), piece.getColor(), cell.getX(), cell.getY());
        
        board.postUpdate();
        
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
