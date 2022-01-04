package engine.utils;

/**
 * Énumération permettant de modéliser des déplacements dans une certaine direction
 * Les directions gauches et droites ne sont pas utilisées, mais sont implémentée
 * dans un souci d'harmonisation.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public enum Direction {
    UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0);
    private final Cell value;
    
    private Direction(int x, int y) {
        this.value = new Cell(x, y);
    }
    
    /**
     * @return la valeur de la direction sous forme d'une case
     */
    public Cell getValue() {
        return value;
    }
    
    /**
     * @return la valeur de la direction sous forme d'un nombre
     */
    public int intValue() {
        return value.getX() == 0 ? value.getY() : value.getX();
    }
}
