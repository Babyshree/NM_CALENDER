import com.jgh.calendarjx.model.ScheduleItem;
import com.jgh.calendarjx.services.ScheduleService;

import java.util.ArrayList;

public class MockScheduleService implements ScheduleService{
    @Override
    public void deleteAllAppointments() {
        //
    }

    @Override
    public ArrayList<ScheduleItem> getAllAppointments() {
        return new ArrayList<>();
    }

    @Override
    public String insertNewScheduleItem(ScheduleItem item) {
        return "empty";
    }

    @Override
    public ScheduleItem getItem(int id) {
        return new ScheduleItem();
    }
}
