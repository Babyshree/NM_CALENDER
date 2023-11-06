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
public class RemoveScheduleController {

    private ItemService mItemService;

    public RemoveScheduleController(ItemService itemService) {
        mItemService = itemService;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public String deleteItem(@RequestBody Map<String, Object> inputData) {
        boolean success = true;
        try {
            int id = Integer.valueOf(String.valueOf(inputData.get("id")));
            mItemService.deleteItem(id);
        } catch (Exception e) {
            success = false;
        }
        if (success)
            return "success";
        else return "error";
    }


}
