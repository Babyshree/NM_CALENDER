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
public class ItemEditController {


    private ItemService mItemService;

    public ItemEditController(ItemService itemService){
        mItemService = itemService;
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public String editItem(@RequestBody Map<String, Object> inputData) {
        int id = Integer.valueOf((String)inputData.get("id"));
        String itemText = (String) inputData.get("item");
        boolean success = true;
        try {
            mItemService.updateItem(id, itemText);
        }catch(Exception e){
            success=false;
        }
        if (success)
            return "success";
        else return "error";
    }


}
