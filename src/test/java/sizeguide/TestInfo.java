package sizeguide;

import java.util.HashMap;
import java.util.Map;

public class TestInfo {

    private static Map<String, SizeGuideErrors> TEST_RESULTS = new HashMap<String, SizeGuideErrors>();


    public static SizeGuideErrors getTestResults(String categoryName) {

        if (TEST_RESULTS.get(categoryName) == null) {
            setTestResults(categoryName, new SizeGuideErrors());
        }

        return TEST_RESULTS.get(categoryName);
    }

    public static SizeGuideErrors setTestResults(String categoryName, SizeGuideErrors testResults) {
        return TEST_RESULTS.put(categoryName, testResults);
    }
}