package sizeguide;

import java.util.ArrayList;
import java.util.List;

public class SizeGuideErrors {

    private List<Page> buttonVisibilityErrors = new ArrayList<Page>();
    private List<Page> tableVisibilityErrors  = new ArrayList<Page>();

    public List<Page> getButtonVisibilityErrors() {
        return this.buttonVisibilityErrors;
    }

    public List<Page> getTableVisibilityErrors() {
        return this.tableVisibilityErrors;
    }

    public void addButtonVisibilityError(String url, String description) {
        this.buttonVisibilityErrors.add(new Page(url, description));
    }

    public void addTableVisibilityError(String url, String description) {
        this.tableVisibilityErrors.add(new Page(url, description));
    }
}
