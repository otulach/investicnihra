package cz.xelfi.demo.investicnihra;

import cz.xelfi.demo.investicnihra.js.Firebase;
import cz.xelfi.demo.investicnihra.js.Firebase.Ref;
import cz.xelfi.demo.investicnihra.js.FirebaseConfig;
import net.java.html.json.ComputedProperty;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.Property;
import cz.xelfi.demo.investicnihra.js.Utilities;
import java.util.Collections;
import java.util.List;
import net.java.html.json.ModelOperation;
import net.java.html.json.OnReceive;
import net.java.html.charts.*;
import net.java.html.json.OnPropertyChange;

@Model(className = "Data", targetId="", instance = true, properties = {
    @Property(name = "money", type = int.class),
    @Property(name = "maximum", type = int.class),
    @Property(name = "welcomeScreen", type = boolean.class),
    @Property(name = "continueScreen", type = boolean.class),
    @Property(name = "investmentScreen", type = boolean.class),
    @Property(name = "finalScreen", type = boolean.class),
    @Property(name = "statsScreen", type = boolean.class),
    @Property(name = "company", type = String.class),
    @Property(name = "round", type = int.class),
    @Property(name = "initExamples", type = Example.class, array = true),
    @Property(name = "examples", type = Example.class, array = true),
    @Property(name = "results", type = Result.class, array = true),
    @Property(name = "current", type = Example.class),
    @Property(name = "time", type = int.class),
    @Property(name = "gains", type = Gain.class, array = true)
})
final class DataModel {
    private Ref ref;
    private Chart<Values, Config> chart;
    
    @ModelOperation
    void assignRef(Data data, Ref ref) {
        ref.on(data.getResults());
        this.ref = ref;
    }
                        
    @Model(className = "Gain", properties = {
        @Property(name = "minimum", type = int.class),
        @Property(name = "maximum", type = int.class),
        @Property(name = "player", type = int.class),
      
    })
    static class StatsModel {
    }
 

    @Model(className = "Example", properties = {
        @Property(name = "title", type = String.class),
        @Property(name = "min", type = int.class),
        @Property(name = "invest", type = int.class),
        @Property(name = "gain", type = int.class),
        @Property(name = "mul", type = int.class),
        @Property(name = "div", type = int.class),
        
    })
    static class ExampleModel {
        @ComputedProperty
        static int maximum(int invest, int gain, int mul, int div) {
            if (div == 0) {
                return gain - invest;
            }
            if (gain - invest > invest / div * mul - invest) {
                return gain - invest;
            } else {
                return invest / div * mul - invest;
            }

        }
    
        static Example choose(List<Example> examples, int money) {
            for (int i = 0; i < examples.size(); i++) {
                Example e = examples.get(i);
                if (money >= e.getMin()) {
                    return examples.remove(i);
                }
            }
            return null;
        }    
    }
    @Model(className = "Result", properties = {
        @Property(name = "place", type = int.class),
        @Property(name = "company", type = String.class),
        @Property(name = "average", type = int.class),
        @Property(name = "when", type = long.class),
      
    })
    static class ResultModel {
        @ComputedProperty
        static String placeText(int place) {
            return "" + place + ".";
        }
        
        static void insert(List<Result> topten, Result newresult, int max) {
            while (topten.size() < max) {
                topten.add(new Result(-1, "neznámá", 0, 0L));
            }
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
            assignPositions(topten);
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
    
    @OnPropertyChange("results")
    static void assignPositions(Data model) {
        List<Result> topten = model.getResults();
        assignPositions(topten);
    }

    static void assignPositions(List<Result> topten) {
        for (int i = 0; i < topten.size(); i++) {
            topten.get(i).setPlace(i + 1);
        }
    }
    
    @Function
    static void start(Data ui) {
        ui.setMoney(5_000_000);
        ui.setMaximum(5000000);
        ui.setContinueScreen(true);
        ui.setWelcomeScreen(false);
        ui.getExamples().clear();
        ui.getExamples().addAll(ui.getInitExamples());
        Collections.shuffle(ui.getExamples());
        ui.setRound(0);
        ui.getGains().clear();
        ui.getGains().add(new Gain(0, ui.getMaximum(), ui.getMoney()));
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
    void stats(Data ui) {
        ui.setContinueScreen(false);
        ui.setWelcomeScreen(false);
        ui.setFinalScreen(false);
        ui.setStatsScreen(true);
        if (chart != null) {
            chart.destroy();
        }
        chart = Chart.createLine(
                new Values.Set("maximum", Color.valueOf("#3366FF"), Color.valueOf("#4D4D4D")),
                new Values.Set("hráč", Color.valueOf("#F15854"), Color.valueOf("#4D4D4D"))
        );
        for (int i = 0; i < ui.getGains().size(); i++) {
            Gain g = ui.getGains().get(i);
            chart.getData().add(new Values("", g.getMaximum(), g.getPlayer()));
            
        }
        chart.applyTo("stats");
    }
    @Function
    static void back(Data ui) {
        ui.setContinueScreen(false);
        ui.setWelcomeScreen(false);
        ui.setFinalScreen(true);
        ui.setStatsScreen(false);
    }
    @Function
    void finish(Data ui) {
        ui.setContinueScreen(false);
        ui.setWelcomeScreen(false);
        Result newresult = new Result(-1, ui.getCompany(), ui.getAverage(), System.currentTimeMillis());
        DataModel.ResultModel.insert(ui.getResults(), newresult, 5);
        ref.set(ui.getResults());
        ui.setFinalScreen(true);
    }
    
    @Function
    static void invest(Data ui) {
        ui.setTime(30);
        ui.nextCountDown();
        Example e = DataModel.ExampleModel.choose(ui.getExamples(), ui.getMoney());
        ui.setCurrent(e);
        ui.setRound(ui.getRound() + 1);
        ui.setInvestmentScreen(true);
        ui.setContinueScreen(false);
    }
    
    @Function
    static void chooseGain(Data ui, Example data) {
        int delta = data.getGain() - data.getInvest(); 
        ui.setMoney(ui.getMoney() + delta);
        ui.setMaximum(ui.getMaximum() + data.getMaximum());
        ui.getGains().add(new Gain(0, ui.getMaximum(), ui.getMoney()));
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
        ui.setMaximum(ui.getMaximum() + data.getMaximum());
        ui.getGains().add(new Gain(0, ui.getMaximum(), ui.getMoney()));        
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
        ui.getInitExamples().clear();
        ui.getInitExamples().addAll(examples);
    }
    
    static void loadExamplesFailed(Data ui, Throwable error) {
        error.printStackTrace();
        ui.getInitExamples().clear();
        ui.getInitExamples().add(new Example("Cannot find examples", 0, 0, 0, 0, 1));
    }
    
    /**
     * Called when the page is ready.
     */
    static void onPageLoad() {
        Data ui = new Data();
        ui.setWelcomeScreen(true);
        ui.loadExamples("examples.json");
        ui.applyBindings();
        Firebase db = Firebase.create(new FirebaseConfig().
            useApiKey("AIzaSyARaHhhBpMCeJ0dV7dhUglMFvSc4AbVOgU").
            useAuthDomain("investicnihra.firebaseapp.com").
            useDatabaseURL("https://investicnihra.firebaseio.com").
            useProjectId("investicnihra").
            useStorageBucket("").
            useMessagingSenderId("854224066852")
        );
        final Firebase.Ref<Result> ref = db.ref(Result.class, "topten");
        ui.assignRef(ref);
    }
}
