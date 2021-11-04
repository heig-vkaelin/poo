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
    
    public static String schedule(Lesson[] lessons) {
        String[] days = new String[]{"Lun", "Mar", "Mer", "Jeu", "Ven"};
        String[] schedules = new String[]{
                "8:30", "9:15", "10:25", "11:15", "12:00", "13:15", "14:00",
                "14:55", "15:45", "16:35", "17:20"
        };
        StringBuilder[] rows = new StringBuilder[(schedules.length + 1) * 2];
        
        // TODO: this
//        for (int i = 0; i < schedules.length; i++) {
//            rows[i].append(schedules)
//        }
        
        String result = "";
        return result;
    }
}
