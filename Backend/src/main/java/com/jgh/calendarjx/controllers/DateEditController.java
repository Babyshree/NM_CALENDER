package com.jgh.calendarjx.controllers;

import com.jgh.calendarjx.services.ItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@RestController
public class DateEditController {

    private ItemService mItemService;

    public DateEditController(ItemService itemService) {
        mItemService = itemService;
    }

    @RequestMapping(value = "/updateDate", method = RequestMethod.POST)
    public String editItem(@RequestBody Map<String, Object> inputData) {
        int id = Integer.valueOf((String) inputData.get("id"));
        String date = (String) inputData.get("date");
        // validate date string
        if (date == null || date.isEmpty()) return "error";
        try {
            new SimpleDateFormat("d M YYYY").parse(date);
        } catch (ParseException e) {
            System.out.println("Failed to parse string " + date);
            return "error";
        }
        boolean success = true;
        try {
            mItemService.updateDate(id, date);

        } catch (Exception e) {
            success = false;
        }
        if (success)
            return "success";
        else return "error";
    }
}
