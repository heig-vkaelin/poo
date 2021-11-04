package ecole;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Hello World from Labo6!");
        
        // Définir les trois leçons du cours de POO (PDO), la leçon
        // du cours SYE (DRE) ainsi qu’une leçon TIC (projet non encadré).
        
        // Professeurs
        Teacher pdo = new Teacher("Pier", "Donini", "PDO");
        Teacher dre = new Teacher("Daniel", "Rossier", "DRE");
        
        // Leçons
        Lesson poo1 = new Lesson("POO", 3, 6, 2, "H02", pdo);
        Lesson poo2 = new Lesson("POO", 4, 6, 2, "H02", pdo);
        Lesson poo3 = new Lesson("POO", 4, 8, 2, "H02", pdo);
        
        Lesson sye = new Lesson("SYE", 1, 1, 2, "G01", dre);
    }
}
