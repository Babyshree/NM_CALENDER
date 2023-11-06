import com.jgh.calendarjx.calendar.CalendarFactory;
import com.jgh.calendarjx.calendar.DateHolder;
import com.jgh.calendarjx.controllers.CalendarController;
import com.jgh.calendarjx.controllers.InputController;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;


public class InputControllerTest {

    private InputController mInputController = new InputController(new MockScheduleService());


    @Test
    public void shouldInputNewScheduleEvent(){
        Map<String, Object> map = new HashMap<>();
        map.put("date","");
        map.put("time","10:45");
        map.put("text", "some event");
        map.put("note","bring food");
        String response = mInputController.input(map);
        assertTrue("Input should succeed", response.equals("success"));
    }

    @Test
    public void shouldFailToInputBadData(){
        Map<String, Object> map = new HashMap<>();
        map.put("date","");
        map.put("time","10:45");
        map.put("note","bring food");
        String response = mInputController.input(map);
        assertTrue("Input should succeed", response.equals("error"));
    }
}
