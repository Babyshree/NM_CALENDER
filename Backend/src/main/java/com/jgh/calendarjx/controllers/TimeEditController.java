package com.jgh.calendarjx.controllers;

import com.jgh.calendarjx.services.ItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

@RestController
public class TimeEditController {

    private ItemService mItemService;

    public TimeEditController(ItemService itemService) {
        mItemService = itemService;
    }

    @RequestMapping(value = "/updateTime", method = RequestMethod.POST)
    public String editItem(@RequestBody Map<String, Object> inputData) {
        int id = Integer.valueOf((String) inputData.get("id"));
        String time = (String) inputData.get("time");
        if(time == null || time.isEmpty()) return "error";
        boolean success = true;
        try {
            mItemService.updateTime(id, time);

        } catch (Exception e) {
            success = false;
        }
        if (success)
            return "success";
        else return "error";
    }
}