import ExChat.*;
import ExClassArray.*;
import ExObjectList.*;
import ExPoulailler.*;
import ExRace.Race;
import ExSeasonEnum.Season;
import Revisions.MagiciensOiseaux;
import Revisions.PigeonsVoyageurs;
import Revisions.Planetes;

public class Main {
    
    public static void main(String[] args) {
//        exClassArray();
//        exObjectList();
//        exChat();
//        exSeasonEnum();
//        exPoulailler();
//        exRace();
//        MagiciensOiseaux.main(args);
//        PigeonsVoyageurs.main(args);
        Planetes.main(args);
    }
    
    public static void exClassArray() {
        Object[] array = new Object[7];
        array[0] = 3;
        array[1] = 4;
        array[2] = 2;
        array[3] = "String";
        array[4] = "String2";
        array[5] = false;
        array[6] = true;
        
        ClassArray test = new ClassArray(7);
        test.printTypes(array);
    }
    
    public static void exObjectList() {
        Object o1 = 1;
        Object o2 = "Test";
        Object o3 = 32;
        ObjectList list = new ObjectList(o1);
        System.out.println(list);
        
        list.insert(o2);
        System.out.println(list);
        list.insert(o3);
        System.out.println(list);
        list.remove(o3);
        System.out.println(list);
        list.remove(o1);
        System.out.println(list);
        list.remove(o2);
        System.out.println(list + "\n APPEND \n");
        list.append(o1);
        System.out.println(list);
        
        list.append(o2);
        System.out.println(list);
        
        list.insert(o3);
        list.insert(o2);
        list.insert(o1);
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }
    
    public static void exChat() {
        Animal[] animals = {
                new Cat("Zion"),
                new Cat("Chandler"),
                new Dog("Doggo", "OUAF", "Bob"),
                new Dog("Alex", "Grrrrrr", "John")
        };
        
        for (Animal a : animals) {
            System.out.println(a);
        }
    }
    
    public static void exSeasonEnum() {
        System.out.println("Display all seasons");
        for (Season s : Season.values()) {
            System.out.println(s);
        }
        System.out.println();
        
        System.out.println("Tests previous");
        System.out.println("Before spring: " + Season.spring.previous());
        System.out.println("Before summer: " + Season.summer.previous());
        System.out.println("Before autumn: " + Season.autumn.previous());
        System.out.println("Before winter: " + Season.winter.previous());
        System.out.println();
        
        System.out.println("Tests next");
        System.out.println("After spring: " + Season.spring.next());
        System.out.println("After summer: " + Season.summer.next());
        System.out.println("After autumn: " + Season.autumn.next());
        System.out.println("After winter: " + Season.winter.next());
        System.out.println();
        
        System.out.println("Tests valueOf");
        System.out.println("Value of spring: " + Season.valueOf("Spring"));
        System.out.println("Value of summer: " + Season.valueOf("Summer"));
        System.out.println("Value of autumn: " + Season.valueOf("Autumn"));
        System.out.println("Value of winter: " + Season.valueOf("Winter"));
        System.out.println("Value of Alex: " + Season.valueOf("Alex"));
        System.out.println();
        
        System.out.println("Tests range");
        System.out.println("Range Summer to Winter: ");
        Season[] range1 = Season.range(Season.summer, Season.winter);
        for (Season s : range1) {
            System.out.println(s);
        }
        
        System.out.println("Range Summer to Summer: ");
        Season[] range2 = Season.range(Season.summer, Season.summer);
        for (Season s : range2) {
            System.out.println(s);
        }
        
        System.out.println("Range Spring to Winter: ");
        Season[] range3 = Season.range(Season.spring, Season.winter);
        for (Season s : range3) {
            System.out.println(s);
        }
        
        System.out.println("Range Winter to Summer: ");
        Season[] range4 = Season.range(Season.winter, Season.summer);
        for (Season s : range4) {
            System.out.println(s);
        }
    }
    
    public static void exPoulailler() {
        Poulailler p = new Poulailler();
        
        p.ajouter(new Poule("Cocotte"));
        p.ajouter(new Poule("Rosette"));
        for (int i = 0; i < 10; i++)
            p.tourSuivant();
    }
    
    public static void exRace() {
        Race.main();
    }
}
