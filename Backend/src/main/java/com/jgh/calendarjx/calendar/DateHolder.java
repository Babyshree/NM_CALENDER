package com.jgh.calendarjx.calendar;

import java.time.LocalDate;

public final class DateHolder {

    private LocalDate mDate;
    private int mIndex;
    private boolean mIsInTargetMonth;

    public DateHolder(LocalDate date, int index, boolean isInTargetMonth){
        mDate = date;
        mIndex = index;
        mIsInTargetMonth = isInTargetMonth;
    }

    public LocalDate getDate(){
        return mDate;
    }

    public int getIndex(){
        return mIndex;
    }


    public boolean isIsInTargetMonth() {
        return mIsInTargetMonth;
    }

    public void setIsInTargetMonth(boolean isInTargetMonth) {
        this.mIsInTargetMonth = isInTargetMonth;
    }
}
