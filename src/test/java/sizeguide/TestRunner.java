package sizeguide;

import com.google.gson.Gson;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "json:" + Config.REPORT_FILE })
public class TestRunner {

    @AfterClass
    public static void afterTests() {

        FileWriter.createGenerationDateFile();

        generateReportJson(Config.KIDS_REPORT_FILE,  getReportJson("kids"));
        generateReportJson(Config.WOMEN_REPORT_FILE, getReportJson("women"));
    }

    private static void generateReportJson(String filePath, String contents) {
        try {
            FileWriter.writeFile(filePath, contents);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getReportJson(String categoryName) {
        System.out.println("Getting " + categoryName + "...");

        try {
            System.out.println(new Gson().toJson(TestInfo.getTestResults(categoryName)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new Gson().toJson(TestInfo.getTestResults(categoryName));
    }
}