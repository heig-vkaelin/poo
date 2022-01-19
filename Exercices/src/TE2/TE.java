package TE2;

// Nom et prénom : Kaelin Valentin

import java.util.*;

// Cylons

class Human {
    private String name;
    
    public Human(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
}

class Model {
    private final int number;
    private int serialCounter;
    
    private Model(int number) {
        this.number = number;
    }
    
    public static final Model
            m6 = new Model(6),
            m8 = new Model(8);
    
    public int number() {
        return number;
    }
    
    public int getNextSerialNumber() {
        return ++serialCounter;
    }
}

class Cylon extends Human {
    private final Model model;
    private final int serialNumber;
    
    public Cylon(String name, Model model) {
        super(name);
        
        if (model == null)
            throw new RuntimeException("Invalid model in Cylon creation.");
        
        this.model = model;
        this.serialNumber = model.getNextSerialNumber();
    }
    
    @Override
    public String toString() {
        return "#" + model.number() + "/" + serialNumber + " " + super.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cylon cylon = (Cylon) o;
        return model == cylon.model; // comparaison comme via une enum
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}


// Pigeons voyageurs

abstract class Bird {
    private String name;
    
    public Bird(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
    abstract public void fly();
}

class Town {
    private final String name;
    private final List<Bird> birds;
    private static int counter = 0;
    
    public Town(String name) {
        this.name = name;
        birds = new LinkedList<>();
    }
    
    public Bird getPigeon(Town destination, String message) {
        Bird bird = new Bird("Pigeon #" + ++counter) {
            private boolean atHome = true;
            
            @Override
            public void fly() {
                atHome = !atHome;
                String greetings = atHome ? "back home" :
                        "to " + destination.name + " and says '" + message + "'";
                
                System.out.println("Flap! " + this + " flies " + greetings);
                
                if (atHome) {
                    destination.birds.remove(this);
                    Town.this.birds.add(this);
                } else {
                    Town.this.birds.remove(this);
                    destination.birds.add(this);
                }
            }
        };
        birds.add(bird);
        return bird;
    }
    
    @Override
    public String toString() {
        return name + " " + birds;
    }
}

public class TE {
    public static void main(String... args) {
        System.out.println("PARTIE 1\n");
        Human
                h1 = new Human("Kara Thrace"),
                h2 = new Human("Lee Adama"),
                c1 = new Cylon("Caprica", Model.m6),
                c2 = new Cylon("Boomer", Model.m8),
                c3 = new Cylon("Athena", Model.m8);
        
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3 + "\n");
        
        // La méthode equals() de la classe Human créée par défaut suffit, car elle
        // compare uniquement les références. Ce que l'on souhaite dans notre cas
        // (un humain n'est égal qu'à lui-même).
        System.out.println("h1/h2: " + h1.equals(h2));
        System.out.println("c1/c2: " + c1.equals(c2));
        System.out.println("c2/c3: " + c2.equals(c3));
        
        System.out.println("\nPARTIE 2\n");
        
        Town
                lausanne = new Town("Lausanne"),
                yverdon = new Town("Yverdon");
        
        Bird
                p1 = yverdon.getPigeon(lausanne, "Hfr gur sbepr Yhxr"),
                p2 = lausanne.getPigeon(yverdon, "Joli lac!");
        
        System.out.println(lausanne);
        System.out.println(yverdon);
        
        p1.fly();
        p2.fly();
        p2.fly();
        p1.fly();
        p1.fly();
        
        System.out.println(lausanne);
        System.out.println(yverdon);
    }
}
