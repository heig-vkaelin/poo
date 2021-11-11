package ecole;

public class Main {
    
    public static void main(String[] args) {
        // 1. Professeurs
        Teacher pdo = new Teacher("Pier", "Donini", "PDO");
        Teacher dre = new Teacher("Daniel", "Rossier", "DRE");
        
        // 2. Leçons
        Lesson poo1 = new Lesson("POO", 2, 5, 2, "H02", pdo);
        Lesson poo2 = new Lesson("POO", 3, 5, 2, "H02", pdo);
        Lesson poo3 = new Lesson("POO", 3, 7, 2, "H02", pdo);
        Lesson sye1 = new Lesson("SYE", 0, 0, 2, "G01", dre);
        Lesson sye2 = new Lesson("SYE", 3, 2, 2, "A09", dre);
        Lesson tic = new Lesson("TIC", 1, 9, 1, "F06");
        
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
        
        // 5. Affecter les leçons aux groupes
        il6.defineLessons(new Lesson[]{poo1, poo2, poo3, sye1, sye2, tic});
        si6.defineLessons(new Lesson[]{poo1, poo2, poo3});
        
        // 6. Personnes
        Person[] people = new Person[]{
                pdo, dre, john, paul, ringo, george, roger, david
        };
        System.out.println("-- Membres de l'ecole\n");
        for (Person p : people) {
            System.out.println(p);
        }
        System.out.println();
        
        // 7. Infos du groupe IL6-1
        System.out.println(il6.schedule());
        
        // 8. Afficher l’horaire du professeur PDO.
        System.out.println(pdo.schedule());
    }
}
