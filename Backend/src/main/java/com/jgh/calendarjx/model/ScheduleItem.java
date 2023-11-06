package com.jgh.calendarjx.model;

public final class ScheduleItem {
    private int id;
    private long date;
    private String appointment_date;
    private String appointment;
    private String notes;
    private String appointment_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getAppointment() {
        return appointment;
    }

    public String getTime() {
        return appointment_time;
    }

    public void setTime(String time) {
        this.appointment_time = time;
    }

    public void setAppointment(String item) {
        this.appointment = item;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String note) {
        notes = note;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String date) {
        appointment_date = date;
    }
}

