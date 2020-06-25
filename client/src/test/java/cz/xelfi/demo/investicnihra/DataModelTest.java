package cz.xelfi.demo.investicnihra;

import cz.xelfi.demo.investicnihra.js.Firebase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.java.html.junit.BrowserRunner;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;

/** Tests for behavior of your application in isolation. Verify
 * behavior of your MVVC code in a unit test.
 */
@RunWith(BrowserRunner.class)
public class DataModelTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test public void testUIModelWithoutUI() {
        Data model = new Data();
    }
    @Test public void newResult(){
        Result r1 = new Result(-1, "tul", 334444, 0);
        Result r2 = new Result(-1, "tul1", 334443, 0);
        Result r3 = new Result(-1, "tul2", 334442, 0);
        Result r4 = new Result(-1, "tul3", 334441, 0);
        Result r5 = new Result(-1, "tul4", 334440, 0);
        List<Result> list = new ArrayList<>(Arrays.asList(r1, r2, r3, r4, r5));
        Result newresult = new Result(-1, "jar", 334442, 0);
        DataModel.ResultModel.insert(list, newresult, 5);
        
        assertEquals("tul", list.get(0).getCompany());
        assertEquals(1, list.get(0).getPlace());
        assertEquals("tul1", list.get(1).getCompany());
        assertEquals(2, list.get(1).getPlace());
        assertEquals("jar", list.get(2).getCompany());
        assertEquals(3, list.get(2).getPlace());
        assertEquals("tul2", list.get(3).getCompany());
        assertEquals(4, list.get(3).getPlace());
        assertEquals("tul3", list.get(4).getCompany());
        assertEquals(5, list.get(4).getPlace());
        assertEquals(5, list.size());
    }
    @Test public void chooseExample(){
        Example r1 = new Example("a", 7000010, 1000001,1600001 , 71, 51);
        Example r2 = new Example("b", 7000002, 1000002,1600002 , 72, 52);
        Example r3 = new Example("c", 7000003, 1000003,1600003 , 73, 53);
        Example r4 = new Example("d", 7000004, 1000004,1600004 , 74, 54);
        Example r5 = new Example("e", 7000005, 1000005,1600005 , 75, 55);
        List<Example> list = new ArrayList<>(Arrays.asList(r1, r2, r3, r4, r5));
        Example res = DataModel.ExampleModel.choose(list, 7000003);
        
        assertEquals(r2, res);
        assertEquals("a", list.get(0).getTitle());
        assertEquals("c", list.get(1).getTitle());
        assertEquals("d", list.get(2).getTitle());
        assertEquals("e", list.get(3).getTitle());     
        assertEquals(4, list.size());
    }

    @Test
    public void maxExample() {
        Example exp = new Example("ahoj", 0, 1000, 1500, 8, 7);
        assertEquals(500, exp.getMaximum());
    }
    @Test
    public void maxExample2() {
        Example exp = new Example("ahoj", 0, 1000, 1500, 15, 5);
        assertEquals(2000, exp.getMaximum());
    }
    @Test
    public void exampleNull() {
        Example exp = new Example();
        assertEquals(0, exp.getMaximum());
    }
}