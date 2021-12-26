package engine.utils;

public class Cell {
    private final int x;
    private final int y;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Cell add(Cell cell) {
        return new Cell(x + cell.x, y + cell.y);
    }
    
    public Cell subtract(Cell cell) {
        return new Cell(x - cell.x, y - cell.y);
    }
    
    public Cell multiply(int n) {
        return new Cell(n * x, n * y);
    }
    
    public boolean isCollinear(Cell cell) {
        return x * cell.y == y * cell.x;
    }
    
    public boolean sameDirection(Cell cell) {
        return (x < 0 == cell.getX() < 0) && (y < 0 == cell.getY() < 0);
    }
}
