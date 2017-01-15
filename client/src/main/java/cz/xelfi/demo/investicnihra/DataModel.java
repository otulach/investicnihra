package cz.xelfi.demo.investicnihra;

import net.java.html.json.ComputedProperty;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.Property;
import cz.xelfi.demo.investicnihra.js.Dialogs;

@Model(className = "Data", targetId="", properties = {
    @Property(name = "money", type = int.class),
    @Property(name = "welcomeScreen", type = boolean.class),
    @Property(name = "continueScreen", type = boolean.class),
    @Property(name = "investmentScreen", type = boolean.class),
    @Property(name = "company", type = String.class),
})
final class DataModel {
    private static Data ui;
    
    @ComputedProperty
    static boolean companyOK(String company) {
        return company != null && company.length() >= 4;
    }
    
    @Function
    static void start(Data model) {
        ui.setMoney(5_000_000);
        ui.setContinueScreen(true);
        ui.setWelcomeScreen(false);
    }
    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new Data();
        ui.setWelcomeScreen(true);
        ui.applyBindings();
    }
}
