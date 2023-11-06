import com.jgh.calendarjx.calendar.CalendarFactory;
import com.jgh.calendarjx.calendar.DateHolder;
import com.jgh.calendarjx.controllers.CalendarController;
import com.jgh.calendarjx.model.ScheduleItem;
import com.jgh.calendarjx.services.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CalendarControllerTest {


    private CalendarController mCalendarController = new CalendarController(new MockScheduleService());


    @Test
    public void shouldShowCalendar(){
        ModelAndView mv = mCalendarController.showCalendar();
        assertEquals("Calendar should be created", "Calendar", mv.getModel().get("header"));
        assertEquals("Calendar should be created", String.class, mv.getModel().get("currentmonth").getClass());
        assertEquals("Calendar should be created", String.class, mv.getModel().get("cal").getClass());
    }
}
