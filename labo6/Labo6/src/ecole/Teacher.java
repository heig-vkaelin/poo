package ecole;

import java.util.Arrays;

public class Teacher extends Person {
    
    private final String abbreviation;
    private Lesson[] lessons;
    
    public Teacher(String firstname, String lastname, String abbreviation) {
        super(firstname, lastname);
        this.abbreviation = abbreviation;
        this.lessons = new Lesson[0];
    }
    
    public Teacher(String firstname, String lastname, String abbreviation, Lesson[] lessons) {
        this(firstname, lastname, abbreviation);
        this.lessons = Arrays.copyOf(lessons, lessons.length);
    }
    
    public String getAbbreviation() {
        return abbreviation;
    }
    
    public String schedule() {
        return "-- Horaire " + this + "\n\n" + Lesson.schedule(this.lessons);
    }
    
    public void addLesson(Lesson lesson) {
        this.lessons = Arrays.copyOf(this.lessons, this.lessons.length + 1);
        this.lessons[this.lessons.length - 1] = lesson;
    }
    
    @Override
    public String toString() {
        return "Prof. " + super.toString() + " (" + abbreviation + ")";
    }
}
