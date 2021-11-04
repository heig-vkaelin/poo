package ecole;

public class Student extends Person {
    private final int number;
    private Group goup;

    public Student(String name,String surName,int number){
        super(name,surName);
        this.number = number;
    }

    public Student(String name,String surName,int number,Group group) {
        this(name,surName,number);
        this.goup = group;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                '}';
    }
}
