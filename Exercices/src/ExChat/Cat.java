package ExChat;

public class Cat extends Animal {
    private static final String CAT_SOUND = "Mreow";
    
    public Cat(String name) {
        super(name, CAT_SOUND);
    }
    
    @Override
    public String type() {
        return "chat";
    }
}
