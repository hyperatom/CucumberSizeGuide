package sizeguide;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@CucumberOptions(format = { "pretty", "html:target/cucumber/html-report" })
@CucumberOptions(format = { "json:target/cucumber/report.json" })
public class TestRunner {

}
