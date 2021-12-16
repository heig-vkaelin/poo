package engine.moves;

import engine.Board;
import engine.utils.Cell;

import java.util.ArrayList;

public abstract class Move {
    public boolean isPathFree(Board board, Cell from, Cell to) {
        ArrayList<Cell> cells = getAllCellsBetweenTwoCells(from, to);
        return true;
    }

    private ArrayList<Cell> getAllCellsBetweenTwoCells(Cell from, Cell to){
        ArrayList<Cell> cells = new ArrayList<>();
        int xMove = (to.getX() - from.getX()) < 0 ? -1 : 1;
        int yMove = (to.getY() - from.getY()) < 0 ? -1 : 1;
        int currentX = from.getX();
        int currentY = from.getY();
        while(!(currentX == to.getY() && currentY == to.getY())){
            currentX += xMove;
            currentY += yMove;
            cells.add(new Cell(currentX, currentY));
        }
        return cells;
    }
    
    public abstract boolean canMove(Board board, Cell from, Cell to);
}
