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
        Result r1 = new Result("tul", 334444, 0);
        Result r2 = new Result("tul1", 334443, 0);
        Result r3 = new Result("tul2", 334442, 0);
        Result r4 = new Result("tul3", 334441, 0);
        Result r5 = new Result("tul4", 334440, 0);
        List<Result> list = new ArrayList<>(Arrays.asList(r1, r2, r3, r4, r5));
        Result newresult = new Result("jar", 334442, 0);
        DataModel.ResultModel.insert(list, newresult, 5);
        
        assertEquals("tul", list.get(0).getCompany());
        assertEquals("tul1", list.get(1).getCompany());
        assertEquals("jar", list.get(2).getCompany());
        assertEquals("tul2", list.get(3).getCompany());
        assertEquals("tul3", list.get(4).getCompany());
        assertEquals(5, list.size());
    }

    /**
     * Test of assignRef method, of class DataModel.
     */
    @Test
    public void testAssignRef() {
        System.out.println("assignRef");
        Data data = null;
        Firebase.Ref ref = null;
        DataModel instance = new DataModel();
        instance.assignRef(data, ref);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of companyOK method, of class DataModel.
     */
    @Test
    public void testCompanyOK() {
        System.out.println("companyOK");
        String company = "";
        boolean expResult = false;
        boolean result = DataModel.companyOK(company);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextRound method, of class DataModel.
     */
    @Test
    public void testNextRound() {
        System.out.println("nextRound");
        int round = 0;
        int expResult = 0;
        int result = DataModel.nextRound(round);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minimum method, of class DataModel.
     */
    @Test
    public void testMinimum() {
        System.out.println("minimum");
        int money = 0;
        int expResult = 0;
        int result = DataModel.minimum(money);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of maximum method, of class DataModel.
     */
    @Test
    public void testMaximum() {
        System.out.println("maximum");
        int money = 0;
        int expResult = 0;
        int result = DataModel.maximum(money);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hráč method, of class DataModel.
     */
    @Test
    public void testHráč() {
        System.out.println("hr\u00e1\u010d");
        int money = 0;
        int expResult = 0;
        int result = DataModel.hráč(money);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of average method, of class DataModel.
     */
    @Test
    public void testAverage() {
        System.out.println("average");
        int money = 0;
        int round = 0;
        int expResult = 0;
        int result = DataModel.average(money, round);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class DataModel.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Data ui = null;
        DataModel.start(ui);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of name method, of class DataModel.
     */
    @Test
    public void testName() {
        System.out.println("name");
        Data ui = null;
        DataModel.name(ui);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stats method, of class DataModel.
     */
    @Test
    public void testStats() {
        System.out.println("stats");
        Data ui = null;
        DataModel.stats(ui);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of back method, of class DataModel.
     */
    @Test
    public void testBack() {
        System.out.println("back");
        Data ui = null;
        DataModel.back(ui);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finish method, of class DataModel.
     */
    @Test
    public void testFinish() {
        System.out.println("finish");
        Data ui = null;
        DataModel instance = new DataModel();
        instance.finish(ui);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invest method, of class DataModel.
     */
    @Test
    public void testInvest() {
        System.out.println("invest");
        Data ui = null;
        DataModel.invest(ui);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chooseGain method, of class DataModel.
     */
    @Test
    public void testChooseGain() {
        System.out.println("chooseGain");
        Data ui = null;
        Example data = null;
        DataModel.chooseGain(ui, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chooseMulDiv method, of class DataModel.
     */
    @Test
    public void testChooseMulDiv() {
        System.out.println("chooseMulDiv");
        Data ui = null;
        Example data = null;
        DataModel.chooseMulDiv(ui, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextCountDown method, of class DataModel.
     */
    @Test
    public void testNextCountDown() {
        System.out.println("nextCountDown");
        Data ui = null;
        DataModel.nextCountDown(ui);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextSecondGone method, of class DataModel.
     */
    @Test
    public void testNextSecondGone() {
        System.out.println("nextSecondGone");
        Data ui = null;
        DataModel.nextSecondGone(ui);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadExamples method, of class DataModel.
     */
    @Test
    public void testLoadExamples() {
        System.out.println("loadExamples");
        Data ui = null;
        List<Example> examples = null;
        DataModel.loadExamples(ui, examples);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadExamplesFailed method, of class DataModel.
     */
    @Test
    public void testLoadExamplesFailed() {
        System.out.println("loadExamplesFailed");
        Data ui = null;
        Throwable error = null;
        DataModel.loadExamplesFailed(ui, error);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onPageLoad method, of class DataModel.
     */
    @Test
    public void testOnPageLoad() {
        System.out.println("onPageLoad");
        DataModel.onPageLoad();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
