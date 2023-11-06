import com.jgh.calendarjx.controllers.RemoveScheduleController;
import com.jgh.calendarjx.services.ItemService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class RemoveScheduleControllerTest {
    private RemoveScheduleController mItemService = new RemoveScheduleController(new MockItemService());
    @Test
    public void test1() {
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("id",1);
        String response = mItemService.deleteItem(inputData);
        assertTrue("Input should succeed", response.equals("success"));

    }

    @Test
    public void test2() {
        Map<String, Object> inputData = new HashMap<>();
        String response = mItemService.deleteItem(inputData);
        assertTrue("Input should fail", response.equals("error"));

    }

}
