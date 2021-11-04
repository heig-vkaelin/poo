package ecole;

public class Teacher extends Person {

    private final String abbreviation;
    private Lesson[] lessons;

    public Teacher(String name, String surName, String abbreviation) {
        super(name, surName);
        this.abbreviation = abbreviation;
    }

    public Teacher(String name, String surName, String abbreviation, Lesson[] lessons) {
        this(name,surName,abbreviation);
        lessons = lessons;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String horaire(){
        return "";
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "abbreviation='" + abbreviation + '\'' +
                '}';
    }
}
