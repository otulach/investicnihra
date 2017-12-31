package cz.xelfi.demo.investicnihra;

import java.util.Arrays;
import java.util.List;
import net.java.html.junit.BrowserRunner;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.Test;

/** Tests for behavior of your application in isolation. Verify
 * behavior of your MVVC code in a unit test.
 */
@RunWith(BrowserRunner.class)
public class DataModelTest {
    @Test public void testUIModelWithoutUI() {
        Data model = new Data();
    }
    @Test public void newResult(){
        Result r1 = new Result("tul", 334444, 0);
        Result r2 = new Result("tul1", 334443, 0);
        Result r3 = new Result("tul2", 334442, 0);
        Result r4 = new Result("tul3", 334441, 0);
        Result r5 = new Result("tul4", 334440, 0);
        List<Result> list = Arrays.asList(r1, r2, r3, r4, r5);
        Result newresult = new Result("jar", 334442, 0);
        DataModel.ResultModel.insert(list, newresult, 5);
        
        assertEquals("tul", list.get(0).getCompany());
        assertEquals("tul1", list.get(1).getCompany());
        assertEquals("jar", list.get(2).getCompany());
        assertEquals("tul2", list.get(3).getCompany());
        assertEquals("tul3", list.get(4).getCompany());
        assertEquals(5, list.size());
    }
}
