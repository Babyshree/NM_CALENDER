package com.jgh.calendarjx.services;

import java.io.IOException;

public interface ItemService {

    void updateItem(int id, String itemText) throws Exception;
    void updateNote(int id, String notes) throws Exception;
    void updateTime(int id, String time) throws Exception;
    void updateDate(int id, String date) throws Exception;
    void deleteItem(int id) throws Exception;
}
