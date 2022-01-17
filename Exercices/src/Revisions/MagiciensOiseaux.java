package Revisions;

import java.util.LinkedList;
import java.util.List;

enum Location {
    Lorien(Race.Elves),
    Mordor(Race.Orcs),
    Rohan(Race.Humans),
    Gondor(Race.Humans);
    
    private final Race race;
    
    private Location(Race race) {
        this.race = race;
    }
    
    public Race getRace() {
        return race;
    }
}

enum Race {
    Elves,
    Orcs,
    Humans
}

abstract class Entity {
    private Location location;
    private static final List<Entity> entities = new LinkedList<>();
    
    public Entity(Location location) {
        this.location = location;
        entities.add(this);
    }
    
    public abstract String name();
    
    public abstract String sentence();
    
    public void speak() {
        System.out.println(name() + " says '" + sentence() + "'");
    }
    
    public void move(Location location) {
        this.location = location;
        System.out.println(name() + " moves to " + location);
    }
    
    public Location getLocation() {
        return location;
    }
    
    public static void printEntities() {
        for (Entity e : entities)
            System.out.println(e.name());
    }
}

class Wizard extends Entity {
    private final String name;
    
    public Wizard(String name, Location location) {
        super(location);
        this.name = name;
    }
    
    public Bird invokeBird() {
        Bird bird = new Bird(getLocation()) {
            @Override
            public String sentence() {
                return "I see " + getLocation().getRace().name();
            }
            
            @Override
            public void move(Location location) {
                super.move(location);
                
                super.speak();
                
                if (location == Wizard.this.getLocation()) {
                    System.out.println(name() + " has found its master " +
                            Wizard.this.name() + " !");
                }
            }
        };
        System.out.println(name + " invokes " + bird.name());
        return bird;
    }
    
    @Override
    public String name() {
        return name;
    }
    
    @Override
    public String sentence() {
        return "I am " + name + " the Wise";
    }
}

class Bird extends Entity {
    private final int id;
    private static int counter = 0;
    
    public Bird(Location location) {
        super(location);
        id = ++counter;
    }
    
    @Override
    public String name() {
        return "Bird #" + id;
    }
    
    @Override
    public String sentence() {
        return "Chirp";
    }
}

public class MagiciensOiseaux {
    public static void main(String... args) {
        System.out.println("-- Standard bird");
        Bird b1 = new Bird(Location.Lorien);
        b1.speak();
        b1.move(Location.Mordor);
        
        System.out.println("\n-- Gandalf");
        Wizard gandalf = new Wizard("Gandalf", Location.Lorien);
        gandalf.speak();
        Bird b2 = gandalf.invokeBird();
        b2.move(Location.Rohan);
        
        System.out.println("\n-- Saruman");
        Wizard saruman = new Wizard("Saruman", Location.Rohan);
        saruman.speak();
        Bird b3 = saruman.invokeBird();
        b3.move(Location.Lorien);
        saruman.move(Location.Gondor);
        b3.move(Location.Gondor);
        
        System.out.println("\n-- Existing entities");
        Entity.printEntities();
    }
}
