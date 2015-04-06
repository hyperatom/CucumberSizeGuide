package sizeguide;

import java.util.ArrayList;
import java.util.List;

public class CategoryErrors {

    private List<String> buttonVisibilityErrors = new ArrayList<String>();
    private List<String> tableVisibilityErrors  = new ArrayList<String>();

    public List<String> getButtonVisibilityErrors() {
        return this.buttonVisibilityErrors;
    }

    public List<String> getTableVisibilityErrors() {
        return this.tableVisibilityErrors;
    }

    public void addButtonVisibilityError(String url) {
        this.buttonVisibilityErrors.add(url);
    }

    public void addTableVisibilityError(String url) {
        this.tableVisibilityErrors.add(url);
    }
}
