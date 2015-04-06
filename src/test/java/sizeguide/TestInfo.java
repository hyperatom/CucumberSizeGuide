package sizeguide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestInfo {

    private static Map<String, List<Category>> TEST_RESULTS = new HashMap<String, List<Category>>();


    public static List<Category> getTestResults(String categoryName) {

        if (TEST_RESULTS.get(categoryName) == null) {
            TEST_RESULTS.put(categoryName, new ArrayList<Category>());
        }

        return TEST_RESULTS.get(categoryName);
    }

    public static List<Category> setTestResults(String categoryName, Category testResults) {

        List<Category> categories = TEST_RESULTS.get(categoryName);

        if (categories == null) {
            categories = new ArrayList<Category>();
        }

        System.out.println(testResults.getTestDescription());

        categories.add(testResults);

        return TEST_RESULTS.put(categoryName, categories);
    }
}