package ExChat;

public abstract class Animal {
    protected final String name;
    protected final String sound;
    
    public Animal(String name, String sound) {
        this.name = name;
        this.sound = sound;
    }
    
    public abstract String type();
    
    public String makeNoise() {
        return sound;
    }
    
    @Override
    public String toString() {
        return "Le " + type() + " " + name + " : " + makeNoise();
    }
}
