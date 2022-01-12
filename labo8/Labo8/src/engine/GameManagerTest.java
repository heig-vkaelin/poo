package engine;

import chess.ChessView;
import chess.PlayerColor;
import engine.pieces.*;
import engine.utils.Cell;

/**
 * Classe permettant de tester rapidement diverses situations initiales.
 * Il est possible de spécifier quelle pièce ou mouvement nous souhaitons tester
 * grâce au constructeur.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class GameManagerTest extends GameManager {
    enum Type {
        CHECK, CASTLE, ROOK, BISHOP, KING, KNIGHT, PAWN, QUEEN
    }
    
    private final Type type;
    
    /**
     * Crée une instance de test
     *
     * @param type : pièce ou mouvement à tester
     */
    public GameManagerTest(Type type) {
        this.type = type;
    }
    
    @Override
    public void start(ChessView view) {
        super.start(view);
        newGame();
    }
    
    @Override
    public void newGame() {
        getBoard().resetBoard();
        // Applique la situation initiale de test
        fillBoard(type);
    }
    
    /**
     * Remplit le plateau selon le test choisi
     *
     * @param type : pièce ou mouvement à tester
     */
    private void fillBoard(Type type) {
        switch (type) {
            case CHECK:
                testCheck();
                break;
            case CASTLE:
                testCastle();
                break;
            case ROOK:
                testRook();
                break;
            case BISHOP:
                testBishop();
                break;
            case KING:
                testKing();
                break;
            case KNIGHT:
                testKnight();
                break;
            case PAWN:
                testPawn();
                break;
            case QUEEN:
                testQueen();
                break;
            default:
                break;
        }
    }
    
    /**
     * Ajoute des pions de la couleur souhaitée autour de la position spécifiée
     *
     * @param pos   position que l'on souhaite entourer
     * @param color couleur de pions
     */
    private void pawnAroundPos(Cell pos, PlayerColor color) {
        for (int i = pos.getX() - 1; i < pos.getX() + 2; i++) {
            for (int j = pos.getY() - 1; j < pos.getY() + 2; j++) {
                if (i == pos.getX() && j == pos.getY()) {
                    continue;
                }
                getBoard().addPiece(new Pawn(getBoard(), new Cell(i, j), color));
            }
        }
    }
    
    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des
     * Cavaliers
     */
    private void testKnight() {
        Cell knight1 = new Cell(5, 5);
        Cell knight2 = new Cell(2, 5);
        Cell knight3 = new Cell(5, 2);
        Cell knight4 = new Cell(2, 2);
        getBoard().addPiece(new Knight(getBoard(), knight1, PlayerColor.WHITE));
        pawnAroundPos(knight1, PlayerColor.BLACK);
        getBoard().addPiece(new Knight(getBoard(), knight2, PlayerColor.WHITE));
        pawnAroundPos(knight2, PlayerColor.WHITE);
        getBoard().addPiece(new Knight(getBoard(), knight3, PlayerColor.BLACK));
        pawnAroundPos(knight3, PlayerColor.WHITE);
        getBoard().addPiece(new Knight(getBoard(), knight4, PlayerColor.BLACK));
        pawnAroundPos(knight4, PlayerColor.BLACK);
    }
    
    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des
     * Tours.
     */
    private void testRook() {
        Cell rookPos1 = new Cell(5, 4);
        Cell rookPos2 = new Cell(2, 4);
        getBoard().addPiece(new Rook(getBoard(), new Cell(0, 0), PlayerColor.WHITE));
        getBoard().addPiece(new Rook(getBoard(), new Cell(7, 7), PlayerColor.BLACK));
        getBoard().addPiece(new Rook(getBoard(), rookPos1, PlayerColor.WHITE));
        getBoard().addPiece(new Rook(getBoard(), rookPos2, PlayerColor.WHITE));
        pawnAroundPos(rookPos1, PlayerColor.WHITE);
        pawnAroundPos(rookPos2, PlayerColor.BLACK);
    }
    
    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des
     * Fous.
     */
    private void testBishop() {
        Cell bishopPos1 = new Cell(5, 4);
        Cell bishopPos2 = new Cell(2, 4);
        getBoard().addPiece(new Bishop(getBoard(), bishopPos1, PlayerColor.WHITE));
        getBoard().addPiece(new Bishop(getBoard(), bishopPos2, PlayerColor.WHITE));
        pawnAroundPos(bishopPos1, PlayerColor.WHITE);
        pawnAroundPos(bishopPos2, PlayerColor.BLACK);
    }
    
    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des
     * Pions.
     */
    private void testPawn() {
        for (int i = 0; i < 7; i++) {
            getBoard().addPiece(new Pawn(getBoard(), new Cell(i, 1),
                    PlayerColor.WHITE));
            getBoard().addPiece(new Knight(getBoard(), new Cell(i, 2), (i % 2 == 0) ?
                    PlayerColor.WHITE : PlayerColor.BLACK));
        }
        getBoard().addPiece(new Pawn(getBoard(), new Cell(7, 1), PlayerColor.BLACK));
        getBoard().addPiece(new Pawn(getBoard(), new Cell(4, 6), PlayerColor.BLACK));
        getBoard().addPiece(new Pawn(getBoard(), new Cell(3, 4), PlayerColor.WHITE));
    }
    
    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des
     * Reines.
     */
    private void testQueen() {
        Cell queenPos = new Cell(5, 4);
        Cell queenPos2 = new Cell(2, 4);
        getBoard().addPiece(new Queen(getBoard(), queenPos, PlayerColor.WHITE));
        getBoard().addPiece(new Queen(getBoard(), queenPos2, PlayerColor.WHITE));
        pawnAroundPos(queenPos, PlayerColor.BLACK);
        pawnAroundPos(queenPos2, PlayerColor.WHITE);
    }
    
    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement des
     * Rois.
     */
    private void testKing() {
        Cell kingPos = new Cell(4, 4);
        getBoard().addPiece(new King(getBoard(), kingPos, PlayerColor.WHITE));
        pawnAroundPos(kingPos, PlayerColor.BLACK);
    }
    
    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement
     * du roque.
     */
    private void testCastle() {
        getBoard().addPiece(new King(getBoard(), new Cell(4, 0), PlayerColor.WHITE));
        getBoard().addPiece(new Rook(getBoard(), new Cell(7, 0), PlayerColor.WHITE));
        getBoard().addPiece(new Rook(getBoard(), new Cell(0, 0), PlayerColor.WHITE));
        getBoard().addPiece(new Queen(getBoard(), new Cell(3, 7), PlayerColor.BLACK));
    }
    
    /**
     * Permet de rapidement position des pièces afin de tester le fonctionnement
     * de la mise en échec.
     */
    private void testCheck() {
        getBoard().addPiece(new King(getBoard(), new Cell(3, 5), PlayerColor.BLACK));
        getBoard().addPiece(new Rook(getBoard(), new Cell(4, 3), PlayerColor.WHITE));
        getBoard().addPiece(new Queen(getBoard(), new Cell(5, 3), PlayerColor.WHITE));
        getBoard().addPiece(new Bishop(getBoard(), new Cell(6, 3),
                PlayerColor.WHITE));
        getBoard().addPiece(new Knight(getBoard(), new Cell(7, 3),
                PlayerColor.WHITE));
        getBoard().addPiece(new King(getBoard(), new Cell(1, 3), PlayerColor.WHITE));
        getBoard().addPiece(new Pawn(getBoard(), new Cell(2, 3), PlayerColor.WHITE));
    }
}
