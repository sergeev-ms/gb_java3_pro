package ru.sms.lesson6.task3.model;

public class Student {
    private int id;
    private String FIO;
    private int Score;

    public Student(int id, String FIO, int score) {
        this.id = id;
        this.FIO = FIO;
        Score = score;
    }

    public Student(String FIO, int score) {
        this.FIO = FIO;
        Score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                ", Score=" + Score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

//        if (id != student.id) return false;
        if (Score != student.Score) return false;
        return FIO != null ? FIO.equals(student.FIO) : student.FIO == null;
    }
}
