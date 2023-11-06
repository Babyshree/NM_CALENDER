package com.jgh.calendarjx.calendar;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;


public class CalendarFactory {

    public static ArrayList<DateHolder> generateCalendar(LocalDate localDate) {
        LocalDate firstOfMonth = localDate.plusMonths(0).withDayOfMonth(1);
        int dim = localDate.lengthOfMonth();
        int offset = firstOfMonth.getDayOfWeek().getValue() % 7;

        int arraysize = (dim + offset) + ((7 - (dim + offset) % 7) % 7);

        ArrayList<DateHolder> array = new ArrayList<DateHolder>(arraysize);
        for (int i = 0; i < arraysize; i++) {
            int j = i - offset;
            LocalDate date = firstOfMonth.plusDays(j);
            array.add(new DateHolder(date, i, j >= 0 && j < dim));
        }
        return array;
    }
}
