package ecole;

public class Person {
    private final String firstname;
    private final String lastname;

//    public String getName() {
//        return name;
//    }
//
//    public String getSurName() {
//        return surName;
//    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + firstname + '\'' +
                ", surName'" + lastname+ '\'' +
                '}';
    }
}
