package ecole;

public class Group {
    private final int number;
    private final String orientation;
    private final int trimester;
    private final Student[] students;
//    private final Lesson[] lessons;
    
    public Group(int number, String orientation, int trimester, Student[] students) {
        this.number = number;
        this.orientation = orientation;
        this.trimester = trimester;
        
        // Assignation du groupe aux élèves
        this.students = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            students[i].setGroup(this);
            this.students[i] = students[i];
        }
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
