package com.jgh.calendarjx.controllers;

import com.jgh.calendarjx.calendar.CalendarFactory;
import com.jgh.calendarjx.calendar.CalendarMonth;
import com.jgh.calendarjx.model.ScheduleItem;
import com.jgh.calendarjx.model.ScheduleMapper;
import com.jgh.calendarjx.model.ScheduleTest;
import com.jgh.calendarjx.services.ScheduleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.sqlite.SQLiteException;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@RestController
public class CalendarController {

    private ScheduleService mScheduleService;

    @Autowired
    public CalendarController(ScheduleService scheduleService) {
        mScheduleService = scheduleService;
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public String deleteAll() {

        try {
            mScheduleService.deleteAllAppointments();
        } catch (Exception e) {
            return "error";
        }

        return "success";
    }

    @RequestMapping("/calendar")
    public ModelAndView showCalendar() {

        ModelAndView mav = new ModelAndView("calendar");
        ArrayList<ScheduleItem> si = mScheduleService.getAllAppointments();
        CalendarMonth cm = new CalendarMonth(LocalDate.now(),
                CalendarFactory.generateCalendar(LocalDate.now()), si);
        String cal = cm.getCalendar();
        mav.addObject("header", "Calendar");
        mav.addObject("currentmonth", LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + LocalDate.now().getYear());
        mav.addObject("cal", cal);
        System.out.println(mav.toString());

        return mav;
    }


    @RequestMapping(value = "/changemonth/{dir}/{current}", method = RequestMethod.GET)
    public Map<String, String> changeMonth(
            @PathVariable("dir") String searchId,
            @PathVariable("current") String currentMonth

    ) {
        Map<String, String> m = new HashMap<String, String>();
        int s = Integer.parseInt(searchId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate month = LocalDate.parse(currentMonth.replace('-', '/'), formatter);
        CalendarMonth cm = new CalendarMonth(month.plusMonths(s),
                CalendarFactory.generateCalendar(month.plusMonths(s)), mScheduleService.getAllAppointments());
        String cal = cm.getCalendar();
        m.put("header", month.plusMonths(s).getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + month.plusMonths(s).getYear());
        m.put("cal", cal);
        return m;
    }
}
