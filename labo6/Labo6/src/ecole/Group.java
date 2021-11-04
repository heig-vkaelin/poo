package ecole;

public class Group {
    private final int number;
    private final String orientation;
    private final int trimester;
    private final Student[] students;
    
    public Group(int number, String orientation, int trimester, Student[] students) {
        this.number = number;
        this.orientation = orientation;
        this.trimester = trimester;
        // TODO: deep copy
        this.students = students;
    }
    
    public String schedule() {
        return "TODO";
    }
    
    public String name() {
        // Exemple: IL6-1
        return orientation + trimester + "-" + number;
    }
    
    public int nbStudents() {
        return students.length;
    }
    
    public void defineLessons(String... args) {
    
    }
}
