package ecole;

public class Lesson {
    private final String subject;
    private final int dayOfTheWeek;
    private final int startPeriod;
    private final int duration;
    private final String room;
    private final Teacher teacher;
    
    private static final int PERIOD_PER_DAY = 11;
    private static final int DAY_PER_WEEK = 5;
    
    private static final String[] DAYS = new String[]{
            "Lun", "Mar", "Mer", "Jeu", "Ven"};
    private static final String[] SCHEDULE_NAMES = new String[]{
            "8:30", "9:15", "10:25", "11:15", "12:00", "13:15", "14:00",
            "14:55", "15:45", "16:35", "17:20"
    };
    private static final int CASE_WIDTH = 13;
    private static final int PADDING_LEFT = 5;
    
    public Lesson(String subject, int dayOfTheWeek, int startPeriod, int duration,
                  String room, Teacher teacher) {
        this.subject = subject;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startPeriod = startPeriod;
        this.duration = duration;
        this.room = room;
        this.teacher = teacher;
        
        // Ajout de la leçon au professeur pour avoir la liaison dans les 2 sens
        if (teacher != null)
            this.teacher.addLesson(this);
    }
    
    public Lesson(String subject, int dayOfTheWeek, int startPeriod, int duration,
                  String room) {
        this(subject, dayOfTheWeek, startPeriod, duration, room, null);
    }
    
    public static String schedule(Lesson[] lessons) {
        int[][] stateLessons = new int[PERIOD_PER_DAY][DAY_PER_WEEK];
        String[][] titleLessons = new String[PERIOD_PER_DAY][DAY_PER_WEEK];
        
        for (Lesson lesson : lessons) {
            titleLessons[lesson.startPeriod][lesson.dayOfTheWeek]
                    = String.format("%-5s %3s %3s", lesson.subject, lesson.room,
                    lesson.teacher != null ? lesson.teacher.getAbbreviation() : "");
            for (int i = 0; i < lesson.duration; i++) {
                stateLessons[lesson.startPeriod + i][lesson.dayOfTheWeek] =
                        lesson.duration - i;
            }
        }
        
        StringBuilder schedule = new StringBuilder();
        schedule.append(" ".repeat(PADDING_LEFT));
        for (String day : DAYS) {
            schedule.append(String.format("| %-" + (CASE_WIDTH - 1) + "s", day));
        }
        String line = "|" + "-".repeat(CASE_WIDTH);
        schedule.append("|\n").append(" ".repeat(PADDING_LEFT))
                .append(line.repeat(DAY_PER_WEEK)).append("|\n");
        
        // Boucle sur chaque ligne
        for (int period = 0; period < PERIOD_PER_DAY; period++) {
            schedule.append(String.format("%" + PADDING_LEFT + "s|", SCHEDULE_NAMES[period]));
            StringBuilder separator = new StringBuilder();
            separator.append("\n").append(" ".repeat(PADDING_LEFT)).append("|");
            
            // Boucle sur chaque colonne
            for (int day = 0; day < DAY_PER_WEEK; day++) {
                schedule.append(titleLessons[period][day] != null ?
                        titleLessons[period][day] : " ".repeat(CASE_WIDTH)
                );
                
                separator.append(
                        (stateLessons[period][day] <= 1 ? "-" : " ").repeat(CASE_WIDTH)
                );
                
                separator.append("|");
                schedule.append("|");
            }
            schedule.append(separator).append("\n");
        }
        
        return schedule.toString();
    }
}
