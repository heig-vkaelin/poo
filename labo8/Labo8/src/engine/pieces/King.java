package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.moves.LinearMove;
import engine.utils.Cell;

public class King extends FirstMoveSpecificPiece {
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
    
    private boolean castle(Cell to) {
        // TODO: check castle
    
        /*
            Le petit et le grand roque doivent être fonctionnels. Leur mouvement est initié en bougeant le roi de deux
            cases vers la droite ou vers la gauche. Ce coup ne peut être effectué si le roi est en échec, s’il a déjà bougé,
            si la tour concernée a déjà bougé ou si une des cases sur lesquelles le roi passe est en échec.
        */
        
        return false;
    }

//    @Override
//    public boolean applyMove(Cell to) {
//
//        TODO: on devrait pas avoir besoin d'override ici psk on check déjà avant de faire le castle
//                  s'il nous fout en échec ou pas (sur toutes les cases qu'il passe dont la destination)
//
//        return super.applyMove(to);
//    }
}
