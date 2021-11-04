package ecole;

public class Lesson {
    private final String subject;
    private final int dayOfTheWeek;
    private final int startPeriod;
    private final int duration;
    private final String room;
    private final Teacher teacher;
    
    public Lesson(String subject, int dayOfTheWeek, int startPeriod, int duration,
                  String room, Teacher teacher) {
        this.subject = subject;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startPeriod = startPeriod;
        this.duration = duration;
        this.room = room;
        this.teacher = teacher;
    }
    
    public Lesson(String subject, int dayOfTheWeek, int startPeriod, int duration,
                  String room) {
        this(subject, dayOfTheWeek, startPeriod, duration, room, null);
    }
    
    public static String schedule(String... args) {
        return "TODO";
    }
}
