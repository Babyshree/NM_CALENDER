package com.jgh.calendarjx.controllers;

import com.jgh.calendarjx.model.ScheduleItem;
import com.jgh.calendarjx.services.ScheduleService;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class InputController {

    private ScheduleService mScheduleService;

    public InputController(ScheduleService scheduleService){
        mScheduleService = scheduleService;
    }

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public String input(@RequestBody Map<String, Object> inputData
    ) {
        String date = (String) inputData.get("date");
        String scheduleEvent = (String) inputData.get("text");
        String eventNote = (String) inputData.get("note");
        String eventTime = (String) inputData.get("time");
        if (scheduleEvent == null || scheduleEvent.trim().length() == 0) {
            return "error";

        }
        long epoch = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        ScheduleItem si = new ScheduleItem();
        si.setDate(epoch);
        si.setAppointment(scheduleEvent);
        si.setAppointment_date(date);
        si.setNotes(eventNote);
        si.setTime(eventTime);
        mScheduleService.insertNewScheduleItem(si);
        return "success";
    }

}
