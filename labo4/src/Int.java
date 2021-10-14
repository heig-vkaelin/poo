public class Int {
    private int value;
    
    static void swapInArray(Int[] tab, int index1, int index2) {
        Int tmp = tab[index1];
        tab[index1] = tab[index2];
        tab[index2] = tmp;
    }
    
    static void swapValues(Int i1, Int i2) {
        int tmp = i1.getValue();
        i1.setValue(i2.getValue());
        i2.setValue(tmp);
    }
    
    void swap(Int other) {
        int tmp = other.getValue();
        other.setValue(getValue());
        setValue(tmp);
    }
    
    Int(int val) {
        this.value = val;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int val) {
        this.value = val;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
