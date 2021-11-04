package ecole;

public class Person {
    private final String name;
    private final String surName;

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public Person(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surName'" + surName+ '\'' +
                '}';
    }
}
