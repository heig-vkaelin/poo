package engine.utils;

public enum Direction {
    UP(0, 1), DOWN(0, -1);
    private final Cell value;
    
    Direction(int x, int y) {
        this.value = new Cell(x, y);
    }
    
    public Cell getValue() {
        return value;
    }
    
    public int intValue() {
        return value.getX() == 0 ? value.getY() : value.getX();
    }
}
