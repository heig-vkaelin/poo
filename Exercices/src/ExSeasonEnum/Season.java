package ExSeasonEnum;

import java.util.Arrays;

public class Season {
    private final String name;
    private final int ordinal;
    private static int count = 0;
    
    private Season(String name) {
        this.name = name;
        this.ordinal = count++;
    }
    
    public static final Season
            spring = new Season("Spring"),
            summer = new Season("Summer"),
            autumn = new Season("Autumn"),
            winter = new Season("Winter");
    
    private static final Season[] seasons =
            new Season[]{spring, summer, autumn, winter};
    
    public static Season[] values() {
        return seasons;
    }
    
    public String toString() {
        return name + "(" + ordinal + ")";
    }
    
    public Season next() {
        return seasons[(ordinal + 1) % seasons.length];
    }
    
    public Season previous() {
        int i = (ordinal - 1) % seasons.length;
        return seasons[i < 0 ? seasons.length - 1 : i];
    }
    
    public int ordinal() {
        return ordinal;
    }
    
    public String name() {
        return name;
    }
    
    public static Season valueOf(String name) {
        return Arrays.stream(seasons)
                .filter(s -> s.name.equals(name))
                .findFirst()
                .orElse(null);
    }
    
    public static Season[] range(Season first, Season last) {
        int size = Math.abs(last.ordinal - first.ordinal) + 1;
        
        Season[] rangeSeasons = new Season[size];
        for (int i = 0; i < size; i++)
            rangeSeasons[i] = seasons[(first.ordinal + i) % seasons.length];
        return rangeSeasons;
    }
}
