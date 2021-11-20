package ExChat;

public class Dog extends Animal {
    private final String owner;
    
    public Dog(String name, String sound, String owner) {
        super(name, sound);
        
        this.owner = owner;
    }
    
    @Override
    public String type() {
        return "chien de " + owner;
    }
}
