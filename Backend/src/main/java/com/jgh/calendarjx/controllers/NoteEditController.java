package com.jgh.calendarjx.controllers;

import com.jgh.calendarjx.services.ItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * Rest Controller for Editing appointment notes.
 */
@RestController
public class NoteEditController {

    private ItemService mItemService;

    public NoteEditController(ItemService itemService) {
        mItemService = itemService;
    }

    @RequestMapping(value = "/updateNote", method = RequestMethod.POST)
    public String editNote(@RequestBody Map<String, Object> inputData) {
        int id = Integer.valueOf((String) inputData.get("id"));
        String notes = (String) inputData.get("note");
        boolean success = true;
        try {
            mItemService.updateNote(id, notes);
        } catch (Exception e) {
            success = false;
        }
        if (success)
            return "success";
        else return "error";
    }


}
