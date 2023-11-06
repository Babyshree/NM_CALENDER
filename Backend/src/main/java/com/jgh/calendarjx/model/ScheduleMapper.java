package com.jgh.calendarjx.model;
import java.util.List;
import java.util.Map;

public interface ScheduleMapper {

    ScheduleItem getAppointment(int id);
    void insertSchedule(ScheduleItem item);
    List<ScheduleItem> getAllAppointments();
    void updateNotes(Note note);
    void updateItem(ScheduleItem item);
    void updateTime(ScheduleItem item);
    void updateDate(ScheduleItem item);
    void deleteAllAppointments();
    void delete(int id);
}
