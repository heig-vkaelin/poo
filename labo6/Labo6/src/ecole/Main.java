package ecole;

public class Main {
    
    public static void main(String[] args) {
        // 1. Professeurs
        Teacher pdo = new Teacher("Pier", "Donini", "PDO");
        Teacher dre = new Teacher("Daniel", "Rossier", "DRE");
        
        // 2. Leçons
        Lesson poo1 = new Lesson("POO", 3, 6, 2, "H02", pdo);
        Lesson poo2 = new Lesson("POO", 4, 6, 2, "H02", pdo);
        Lesson poo3 = new Lesson("POO", 4, 8, 2, "H02", pdo);
        Lesson sye = new Lesson("SYE", 1, 1, 2, "G01", dre);
        Lesson tic = new Lesson("SYE", 2, 10, 1, "F06");
        
        // 3. Élèves
        Student john = new Student("John", "Lennon", 1234);
        Student paul = new Student("Paul", "Mc Cartney", 2341);
        Student ringo = new Student("Ringo", "Starr", 3241);
        Student george = new Student("George", "Harisson", 4321);
        Student roger = new Student("Roger", "Waters", 1324);
        Student david = new Student("David", "Gilmour", 4312);
        
        // 4. Groupes
        Group il6 = new Group(1, "IL", 6, new Student[]{john, paul, ringo, george});
        Group si6 = new Group(1, "SI", 6, new Student[]{roger, david});
        
        // 5. TODO
        
        // 6. Personnes
        Person[] people = new Person[]{
                pdo, dre, john, paul, ringo, george, roger, david
        };
        for (Person p : people) {
            System.out.println(p);
        }
        
        
        
        /*
        5. Affecter au groupe IL6-1 toutes les leçons existantes. Affecter au
        groupe SI6-1 toutes les leçons de POO.
        7. Afficher les informations relatives au groupe IL6-1 (nom, nombre
        d’étudiants, horaire).
        8. Afficher l’horaire du professeur PDO.
         */
    }
}
