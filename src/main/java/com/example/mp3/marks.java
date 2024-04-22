package com.example.mp3;

public class marks {
    int id;
    String name;
    int credits;
    String marks;

    public marks(int id,  String name, int credits, String marks){
        this.id = id;
        this.name=name;
        this.credits=credits;
        this.marks=marks;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getMarks() {
        return marks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
