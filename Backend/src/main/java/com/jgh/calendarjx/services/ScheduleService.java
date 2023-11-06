package com.jgh.calendarjx.services;

import com.jgh.calendarjx.model.ScheduleItem;

import java.util.ArrayList;


public interface ScheduleService  {

    void deleteAllAppointments() throws Exception;
    ArrayList<ScheduleItem> getAllAppointments();
    String insertNewScheduleItem(ScheduleItem item);
    ScheduleItem getItem(int id);
}
