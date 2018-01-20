package cz.xelfi.demo.investicnihra;

import net.java.html.json.ComputedProperty;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.Property;
import cz.xelfi.demo.investicnihra.js.Utilities;
import java.util.Collections;
import java.util.List;
import net.java.html.json.ModelOperation;
import net.java.html.json.OnReceive;

@Model(className = "Data", targetId="", properties = {
    @Property(name = "money", type = int.class),
    @Property(name = "welcomeScreen", type = boolean.class),
    @Property(name = "continueScreen", type = boolean.class),
    @Property(name = "investmentScreen", type = boolean.class),
    @Property(name = "finalScreen", type = boolean.class),
    @Property(name = "company", type = String.class),
    @Property(name = "round", type = int.class),
    @Property(name = "examples", type = Example.class, array = true),
    @Property(name = "results", type = Result.class, array = true),
    @Property(name = "current", type = Example.class),
    @Property(name = "time", type = int.class)
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
    @Model(className = "Result", properties = {
        @Property(name = "company", type = String.class),
        @Property(name = "average", type = int.class),
        @Property(name = "when", type = long.class),
      
    })
    static class ResultModel {
        static void insert(List<Result> topten, Result newresult, int max) {
            for (int i = 0; i < topten.size(); i++) {
                Result r = topten.get(i);
                if (newresult.getAverage() >= r.getAverage()) {
                    topten.add(i, newresult);
                    break;
                }
            }
            while (topten.size() > max) {
                topten.remove(topten.size() - 1);
            }
        }
    }
    
    @ComputedProperty
    static boolean companyOK(String company) {
        return company != null && company.length() >= 4;
    }
    
    @ComputedProperty
    static int nextRound(int round) {
        return round + 1;
    }
    
    @ComputedProperty
    static int average(int money, int round) {
        if (round == 0)  {
             return 0;   
        }
        return  (money - 5000000) / round;
    }
    
    @Function
    static void start(Data ui) {
        ui.setMoney(5_000_000);
        ui.setContinueScreen(true);
        ui.setWelcomeScreen(false);
        Collections.shuffle(ui.getExamples());
        ui.setRound(0);
        ui.setFinalScreen(false);
        
    }
    
    @Function
    static void name(Data ui) {
        ui.setContinueScreen(false);
        ui.setWelcomeScreen(true);
        ui.setFinalScreen(false);
        ui.setCompany("");
    }
    @Function
    static void finish(Data ui) {
        ui.setContinueScreen(false);
        ui.setWelcomeScreen(false);
        Result newresult = new Result(ui.getCompany(), ui.getAverage(), System.currentTimeMillis());
        DataModel.ResultModel.insert(ui.getResults(), newresult, 5);
        ui.setFinalScreen(true);
    }
    
    @Function
    static void invest(Data ui) {
        ui.setTime(30);
        ui.nextCountDown();
        ui.setCurrent(ui.getExamples().get(ui.getRound()));
        ui.setRound(ui.getRound() + 1);
        ui.setInvestmentScreen(true);
        ui.setContinueScreen(false);
    }
    
    @Function
    static void chooseGain(Data ui, Example data) {
        int delta = data.getGain() - data.getInvest(); 
        ui.setMoney(ui.getMoney() + delta);
        finishInvestment(ui);
    }

    private static void finishInvestment(Data ui1) {
        ui1.setInvestmentScreen(false);
        if (ui1.getRound() == 15) {
            ui1.setFinalScreen(true);
        } else {
            ui1.setContinueScreen(true);
        }
    }
    
    @Function
    static void chooseMulDiv(Data ui, Example data) {
        int gain = data.getInvest() / data.getDiv() * data.getMul();
        int delta = gain - data.getInvest(); 
        ui.setMoney(ui.getMoney() + delta);        
        finishInvestment(ui);
    }
    
    @ModelOperation
    static void nextCountDown(final Data ui) {
        Utilities.afterASecond(new Runnable() {
            @Override
            public void run() {
                ui.nextSecondGone();
            }
        });
    }
    
    @ModelOperation
    static void nextSecondGone(Data ui) {
        if (ui.getTime() == 0) {
            finishInvestment(ui);
        } else {
            if (ui.isInvestmentScreen()) {
                ui.setTime(ui.getTime() - 1);
                ui.nextCountDown();
            }
        }
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
