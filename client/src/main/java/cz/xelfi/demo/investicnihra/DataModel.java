package cz.xelfi.demo.investicnihra;

import net.java.html.json.ComputedProperty;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.Property;
import cz.xelfi.demo.investicnihra.js.Dialogs;
import java.util.Collections;
import java.util.List;
import net.java.html.json.OnReceive;

@Model(className = "Data", targetId="", properties = {
    @Property(name = "money", type = int.class),
    @Property(name = "welcomeScreen", type = boolean.class),
    @Property(name = "continueScreen", type = boolean.class),
    @Property(name = "investmentScreen", type = boolean.class),
    @Property(name = "company", type = String.class),
    @Property(name = "round", type = int.class),
    @Property(name = "examples", type = Example.class, array = true),
    @Property(name = "current", type = Example.class),
})
final class DataModel {
    private static Data ui;

    @Model(className = "Example", properties = {
        @Property(name = "title", type = String.class),
        @Property(name = "min", type = int.class),
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
    static void start(Data ui) {
        ui.setMoney(5_000_000);
        ui.setContinueScreen(true);
        ui.setWelcomeScreen(false);
        Collections.shuffle(ui.getExamples());
        ui.setRound(1);
    }
    
    @Function
    static void invest(Data ui) {
        ui.setCurrent(ui.getExamples().get(ui.getRound() - 1));
        ui.setRound(ui.getRound() + 1);
        ui.setInvestmentScreen(true);
        ui.setContinueScreen(false);
    }
    
    @Function
    static void chooseGain(Data ui, Example data) {
        int delta = data.getGain() - data.getInvest(); 
        ui.setMoney(ui.getMoney() + delta);
        ui.setInvestmentScreen(false);
        ui.setContinueScreen(true);
    }
    
    @Function
    static void chooseMulDiv(Data ui, Example data) {
        int gain = data.getInvest() / data.getDiv() * data.getMul();
        int delta = gain - data.getInvest(); 
        ui.setMoney(ui.getMoney() + delta);        
        ui.setInvestmentScreen(false);
        ui.setContinueScreen(true);
        
    }
    
    @OnReceive(url = "{path}", onError = "loadExamplesFailed")
    static void loadExamples(Data ui, List<Example> examples) {
        ui.getExamples().clear();
        ui.getExamples().addAll(examples);
    }
    
    static void loadExamplesFailed(Data ui, Throwable error) {
        error.printStackTrace();
        ui.getExamples().clear();
        ui.getExamples().add(new Example("Cannot find examples", 0, 0, 0, 0, 1));
    }
    
    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new Data();
        ui.setWelcomeScreen(true);
        ui.loadExamples("examples.json");
        ui.applyBindings();
    }
}
