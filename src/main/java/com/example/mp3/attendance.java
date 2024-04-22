package com.example.mp3;

import com.mysql.cj.conf.IntegerProperty;

public class attendance {
    int id;
    String name;
    String attendance;

    public attendance(int id,  String name, String attendance){
        this.id = id;
        this.name=name;
        this.attendance=attendance;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAttendance(){
        return attendance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
