package ecole;

public class Student extends Person {
    private final int number;
    private Group group;
    
    public Student(String firstname, String lastname, int number) {
        super(firstname, lastname);
        this.number = number;
    }
    
    public Student(String firstname, String lastname, int number, Group group) {
        this(firstname, lastname, number);
        this.group = group;
    }

//    public int getNumber() {
//        return number;
//    }
    
    @Override
    public String toString() {
        return "Etud. " + super.toString() + " (#"+ number +")" + (group == null ? "" :
                " - " + group.name());
    }
}
