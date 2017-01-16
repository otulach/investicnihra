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
    @Property(name = "round", type = int.class),
    @Property(name = "current", type = Example.class),
})
final class DataModel {
    private static Data ui;

    @Model(className = "Example", properties = {
        @Property(name = "title", type = String.class),
        @Property(name = "invest", type = int.class),
        @Property(name = "gain", type = int.class),
        @Property(name = "mul", type = int.class),
        @Property(name = "div", type = int.class),
        
    })
    static class ExampleModel {
    }
    
    @ComputedProperty
    static boolean companyOK(String company) {
        return company != null && company.length() >= 4;
    }
    
    @Function
    static void start(Data model) {
        ui.setMoney(5_000_000);
        ui.setContinueScreen(true);
        ui.setWelcomeScreen(false);
        ui.setRound(1);
        
        ui.getCurrent().setTitle("Nemocnice na Bulovce");
        ui.getCurrent().setInvest(1000000);
        ui.getCurrent().setGain(1500000);
        ui.getCurrent().setMul(10);
        ui.getCurrent().setDiv(5);
    }
    
    @Function
    static void invest(Data model) {
        ui.setRound(ui.getRound() + 1);
        ui.setInvestmentScreen(true);
        ui.setContinueScreen(false);
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
