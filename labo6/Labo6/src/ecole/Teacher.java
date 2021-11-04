package ecole;

public class Teacher extends Person {
    
    private final String abbreviation;
    private Lesson[] lessons;
    
    public Teacher(String firstname, String lastname, String abbreviation) {
        super(firstname, lastname);
        this.abbreviation = abbreviation;
    }
    
    public Teacher(String firstname, String lastname, String abbreviation, Lesson[] lessons) {
        this(firstname, lastname, abbreviation);
        this.lessons = lessons;
    }
    
    public String getAbbreviation() {
        return abbreviation;
    }
    
    public String horaire() {
        return "";
    }
    
    @Override
    public String toString() {
        return "Teacher{" +
                "abbreviation='" + abbreviation + '\'' +
                '}';
    }
}
