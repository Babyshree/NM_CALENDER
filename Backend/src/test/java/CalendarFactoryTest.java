import com.jgh.calendarjx.calendar.CalendarFactory;
import com.jgh.calendarjx.calendar.DateHolder;
import com.jgh.calendarjx.model.ScheduleItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

// tests
// https://github.com/bkielczewski/example-spring-boot-mvc/blob/master/src/test/java/eu/kielczewski/example/controller/UserCreateControllerTest.java
public class CalendarFactoryTest {

    @Test
    public void test1() {
        LocalDate ld = LocalDate.of(2018, 1, 1);
        ArrayList<DateHolder> list = CalendarFactory.generateCalendar(ld);
        assertTrue(list.get(0).getDate().equals(LocalDate.of(2017, 12, 31)));
    }

    @Test
    public void test2() {
        LocalDate ld = LocalDate.of(2018, 5, 5);
        ArrayList<DateHolder> list = CalendarFactory.generateCalendar(ld);
        assertTrue(list.get(0).getDate().equals(LocalDate.of(2018, 4, 29)));
    }

    @Test
    public void test3() {
        LocalDate ld = LocalDate.of(2018, 5, 10);
        ArrayList<DateHolder> list = CalendarFactory.generateCalendar(ld);
        assertTrue(list.get(0).getDate().equals(LocalDate.of(2018, 4, 29)));
    }

    @Test
    public void test4() {
        LocalDate ld = LocalDate.of(2017, 2, 10);
        ArrayList<DateHolder> list = CalendarFactory.generateCalendar(ld);
        assertTrue(list.get(list.size() - 1).getDate().equals(LocalDate.of(2017, 3, 4)));
    }

    @Test
    public void test5() {
        LocalDate ld = LocalDate.of(2016, 8, 1);
        ArrayList<DateHolder> list = CalendarFactory.generateCalendar(ld);
        assertTrue(list.get(list.size() - 1).getDate().equals(LocalDate.of(2016, 9, 3)));
    }
}