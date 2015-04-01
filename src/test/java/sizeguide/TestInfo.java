package sizeguide;

import java.util.Map;

public class TestInfo {

    private static String TEST_CATEGORY = "";

    private static Map<String, SizeGuideErrors> TEST_RESULTS;


    public static SizeGuideErrors getTestResults(String categoryName) {
        return TEST_RESULTS.get(categoryName);
    }

    public static SizeGuideErrors setTestResults(String categoryName, SizeGuideErrors testResults) {
        return TEST_RESULTS.put(categoryName, testResults);
    }
}