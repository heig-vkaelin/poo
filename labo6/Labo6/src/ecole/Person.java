package ecole;

public class Person {
    private final String firstname;
    private final String lastname;
    
    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
