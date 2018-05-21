package com.example.acer.doctord;

/**
 * Created by ACER on 12/22/2017.
 */

public class Appointment {
    String appoId;
    String name;
    String date;
    String phoneNum;

    public  Appointment() {

    }

    public Appointment(String appoId, String name, String date, String phoneNum) {
        this.appoId = appoId;
        this.name = name;
        this.date = date;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAppoId() {
        return appoId;
    }
}
