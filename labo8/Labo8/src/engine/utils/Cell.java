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
    
    public boolean reachable(Cell cell) {
        return x * cell.y == y * cell.x;
    }
    
    public boolean sameDirection(Cell cell) {
        return (x < 0 == cell.getX() < 0) && (y < 0 == cell.getY() < 0);
    }
    
    /**
     * Source: https://stackoverflow.com/a/9135980/9188650
     *
     * @return l'hashcode de la cellule
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass() &&
                this.x == ((Cell) obj).x &&
                this.y == ((Cell) obj).y;
    }
}
