package sizeguide;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "json:" + Config.REPORT_FILE })
public class TestRunner {

    @AfterClass
    public static void beforeTests() {
        System.out.println("Stopping test runner.");
    }

}
