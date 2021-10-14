public class Int {
    private int value;
    
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
    
    void swap(Int other) {
        int tmp = other.getValue();
        other.setValue(getValue());
        setValue(tmp);
    }
    
    static void swapInArray(Int[] tab, int index1, int index2) {
        Int tmp = new Int(tab[index1].getValue());
        tab[index1] = tab[index2];
        tab[index2] = tmp;
    }
    
    static void swapValues(Int i1, Int i2) {
        int tmp = i1.getValue();
        i1.setValue(i2.getValue());
        i2.setValue(tmp);
    }
}
