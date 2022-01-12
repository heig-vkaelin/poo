package engine.utils;

import java.util.Objects;

/**
 * Classe représentant une case de l'échiquier
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class Cell {
    private final int x;
    private final int y;
    
    /**
     * @param x : coordonnée x de la case
     * @param y : coordonnée y de la case
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return la coordonnée X de la case
     */
    public int getX() {
        return x;
    }
    
    /**
     * @return la coordonnée Y de la case
     */
    public int getY() {
        return y;
    }
    
    /**
     * Additionne une seconde case
     *
     * @param cell : la case à ajouter
     * @return le résultat de l'addition via une nouvelle case
     * @throws RuntimeException si la case à additionner est invalide
     */
    public Cell add(Cell cell) {
        if (cell == null)
            throw new RuntimeException("Addition d'une case invalide");
        
        return new Cell(x + cell.x, y + cell.y);
    }
    
    /**
     * Soustrait une seconde case
     *
     * @param cell : la case à soustraire
     * @return le résultat de la soustraction via une nouvelle case
     * @throws RuntimeException si la case à soustraire est invalide
     */
    public Cell subtract(Cell cell) {
        if (cell == null)
            throw new RuntimeException("Soustraction d'une case invalide");
        
        return new Cell(x - cell.x, y - cell.y);
    }
    
    /**
     * Multiplie la case par un scalaire
     *
     * @param n : scalaire
     * @return le résultat de la multiplication via une nouvelle case
     */
    public Cell multiply(int n) {
        return new Cell(n * x, n * y);
    }
    
    /**
     * Vérifie qu'une case peut être atteinte depuis une autre
     *
     * @param cell : case de potentielle arrivée
     * @return true si la case est atteignable, false sinon
     */
    public boolean reachable(Cell cell) {
        return cell != null && x * cell.y == y * cell.x;
    }
    
    /**
     * Vérifie que deux cases ont les mêmes signes sur leurs deux coordonnées
     *
     * @param cell : la seconde case
     * @return true si les signes sont les mêmes, false sinon
     */
    public boolean sameDirection(Cell cell) {
        return cell != null && (x < 0 == cell.getX() < 0) && (y < 0 == cell.getY() < 0);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass() &&
                this.x == ((Cell) obj).x &&
                this.y == ((Cell) obj).y;
    }
}
