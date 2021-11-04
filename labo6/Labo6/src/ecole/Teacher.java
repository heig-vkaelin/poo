package ecole;

import java.util.Arrays;

public class Teacher extends Person {
    
    private final String abbreviation;
    private Lesson[] lessons;
    
    public Teacher(String firstname, String lastname, String abbreviation) {
        super(firstname, lastname);
        this.abbreviation = abbreviation;
    }
    
    public Teacher(String firstname, String lastname, String abbreviation, Lesson[] lessons) {
        this(firstname, lastname, abbreviation);
        this.lessons = Arrays.copyOf(lessons, lessons.length);
    }
    
    public String getAbbreviation() {
        return abbreviation;
    }
    
    public String schedule() {
        return "-- Horaire " + toString() + "\n\n" + Lesson.schedule(this.lessons);
    }
    
    @Override
    public String toString() {
        return "Prof. " + super.toString() + " (" + abbreviation + ")";
    }
}
