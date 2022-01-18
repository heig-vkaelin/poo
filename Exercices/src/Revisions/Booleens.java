package Revisions;

class Boolean {
    private final boolean value;
    
    public static Boolean
            TRUE = new Boolean(true),
            FALSE = new Boolean(false);
    
    private Boolean(boolean value) {
        this.value = value;
    }
    
    public boolean booleanValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "" + value;
    }
    
    public static Boolean valueOf(boolean b) {
        return b ? TRUE : FALSE;
    }
    
    public static Boolean[] values() {
        return new Boolean[]{FALSE, TRUE};
    }
}

public class Booleens {
    public static void main(String[] args) {
        Boolean b1 = Boolean.valueOf(false);
        boolean b2 = b1.booleanValue();
        
        System.out.println(b2); // affiche: false
        
        Boolean[] array = Boolean.values(); // affiche: false true
        
        for (Boolean aBoolean : array) {
            System.out.print(aBoolean.booleanValue() + " ");
        }
    }
}
