package com.jgh.calendarjx.controllers;


import com.jgh.calendarjx.model.ScheduleItem;
import com.jgh.calendarjx.services.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {


    private ScheduleService mScheduleService;

    public ItemController(ScheduleService scheduleService){
        mScheduleService = scheduleService;
    }

    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.GET)
    public Map<String, String>  item(@PathVariable("id") String id) {
        int id_ = Integer.parseInt(id);

        ScheduleItem item = mScheduleService.getItem(id_);
        HashMap<String,String> map = new HashMap<>();
        map.put("text", item.getAppointment());
        map.put("date", item.getAppointment_date());
        map.put("time", item.getTime());
        String note = item.getNotes();
        if(note == null) note = "";
        map.put("note", note);
        return map;
    }
}
