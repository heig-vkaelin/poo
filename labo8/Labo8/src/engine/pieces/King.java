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
        // Si le roi à bougé ou qu'il est en échec, il ne peut plus rock
        if(hasMoved()/* || getBoard().isCheck(getColor())*/) { return false; } // si on met ça y'a un crash
        // Exception in thread "AWT-EventQueue-0" java.lang.StackOverflowError
        // Si le roi va pas deux cases à gauche ou à droite, c'est faux
        int deltaY = to.getY() - getCell().getY();
        int deltaX = to.getX() - getCell().getX();
        if(Math.abs(deltaX) != 2 || Math.abs(deltaY) != 0){
            return false;
        }
        // A droite
        if(deltaX > 0){
            for(int i = 1; i < 3; i++){
                // Si la case n'est pas vide
                if(getBoard().getPiece(new Cell(getCell().getX() + i,getCell().getY())) != null){ return false; }
                // Si la case est attaquée
                if(getBoard().isAttacked(getColor(), new Cell(getCell().getX() + i,getCell().getY()))){ return false; }
            }
            return true;
        } else { // A gauche
            for(int i = 1; i < 3; i++){
                // Si la case n'est pas vide
                if(getBoard().getPiece(new Cell(getCell().getX() - i,getCell().getY())) != null){ return false; }
                // Si la case est attaquée
                if(getBoard().isAttacked(getColor(), new Cell(getCell().getX() - i,getCell().getY()))){ return false; }
            }
            // on doit uniquement controler que cette case est vide, vu que le roi ne passe pas dessus.
            if(getBoard().getPiece(new Cell(getCell().getX() - 3,getCell().getY())) != null){ return false; }
            return true;
        }
        /*
            Le petit et le grand roque doivent être fonctionnels. Leur mouvement est initié en bougeant le roi de deux
            cases vers la droite ou vers la gauche. Ce coup ne peut être effectué si le roi est en échec, s’il a déjà bougé,
            si la tour concernée a déjà bougé ou si une des cases sur lesquelles le roi passe est en échec.
        */
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
