package com.jgh.calendarjx.model;

public class ScheduleTest {

    private int id;
    private long date;
    private String appointment;
    private String notes;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public long getDate(){
        return date;
    }

    public void setDate(long date){
        this.date=date;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String item) {
        this.appointment = item;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String item) {
        this.notes = item;
    }

}
