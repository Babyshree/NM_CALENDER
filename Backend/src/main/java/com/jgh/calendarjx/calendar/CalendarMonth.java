package com.jgh.calendarjx.calendar;

import com.jgh.calendarjx.model.ScheduleItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class CalendarMonth {


    private ArrayList<DateHolder> mDatHolderArrayList;
    private StringBuilder mStringBuilder;
    private LocalDate mLocalDate;
    private HashMap<String, ArrayList<ScheduleItem>> m_scheduleItems = new HashMap<>();

    /**
     * @param date
     * @param dataHolders
     * @param scheduleItems
     */
    public CalendarMonth(LocalDate date, ArrayList<DateHolder> dataHolders, ArrayList<ScheduleItem> scheduleItems) {
        mDatHolderArrayList = dataHolders;
        mLocalDate = date;
        for (ScheduleItem item : scheduleItems) {
            if (m_scheduleItems.containsKey(item.getAppointment_date())) {
                m_scheduleItems.get(item.getAppointment_date()).add(item);
            } else {
                ArrayList<ScheduleItem> itemList = new ArrayList<>();
                itemList.add(item);
                m_scheduleItems.put(item.getAppointment_date(), itemList);
            }
        }
        initialize();
    }

    private void initialize() {
        mStringBuilder = new StringBuilder();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d M yyyy");
        DateTimeFormatter formatString = DateTimeFormatter.ofPattern("d MMMM yyyy");
        mStringBuilder.append("<div class=\"panel panel-default\">");
        mStringBuilder.append("<table class=\"table table-striped table-bordered table-condensed\" id=\'calendar\' data-id='" + format.format(mLocalDate) +
                "'>");
        mStringBuilder.append("<tr>");
        mStringBuilder.append("<th>Sunday</th>");
        mStringBuilder.append("<th>Monday</th>");
        mStringBuilder.append("<th>Tuesday</th>");
        mStringBuilder.append("<th>Wednesday</th>");
        mStringBuilder.append("<th>Thursday</th>");
        mStringBuilder.append("<th>Friday</th>");
        mStringBuilder.append("<th>Saturday</th>");
        mStringBuilder.append("</tr>");

        for (int i = 0; i < mDatHolderArrayList.size(); i++) {

            DateHolder dh = mDatHolderArrayList.get(i);
            if (dh.getIndex() % 7 == 0) {
                mStringBuilder.append("<tr>");
            }
            if (dh.isIsInTargetMonth()) {
                mStringBuilder.append(" <td ondrop='drop(event)' ondragover='allowDrop(event)' data-id=\'" + format.format(dh.getDate()) + "\' id=\'calendar\' class=\'row\' style=\'background: "+Settings.TARGET_MONTH_BG_COLOR+"\'>");

            } else {
                mStringBuilder.append(" <td ondrop='drop(event)' ondragover='allowDrop(event)' data-id=\'" + format.format(dh.getDate()) + "\' id=\'calendar\' class=\'row\' style='background: "+Settings.OTHER_MONTH_BG_COLOR+"\'>");
            }
            mStringBuilder.append("<div>");
            mStringBuilder.append(formatString.format(dh.getDate()));
            mStringBuilder.append("</div>");
            mStringBuilder.append("<div>  <button type=\"button\" data-id=\'" + format.format(dh.getDate()) + "\' onclick='dateClick(this)'" + " class=\"btn-info\">Add New Item</button></div>");
            mStringBuilder.append("<div class=\"list-container\">");

            if (m_scheduleItems.containsKey(format.format(dh.getDate()))) {
                mStringBuilder.append("<ul class=\"list-group\">");
                for (ScheduleItem item : m_scheduleItems.get(format.format(dh.getDate()))) {

                    mStringBuilder.append("<li draggable='true' ondragstart=\"drag(event)\" class=\"list-group-item\" data-id=\'" + item.getId() + "\' onclick='editSchedule(this)' >");
                    mStringBuilder.append(item.getAppointment());
                    mStringBuilder.append("</li>");
                }
                mStringBuilder.append("</ul>");
            }

            mStringBuilder.append("</div>");
            mStringBuilder.append("</td>");
            if (dh.getIndex() % 7 == 6) {
                mStringBuilder.append("</tr>");
            }
        }
        mStringBuilder.append("</table>");
        mStringBuilder.append("</div>");
    }


    public String getCalendar() {
        return mStringBuilder.toString();
    }
}
