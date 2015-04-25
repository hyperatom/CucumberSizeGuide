package sizeguide;

import java.util.ArrayList;
import java.util.List;

public class CategoryErrors {

    private List<Product> buttonVisibilityErrors = new ArrayList<Product>();
    private List<Product> tableVisibilityErrors  = new ArrayList<Product>();

    public List<Product> getButtonVisibilityErrors() {
        return this.buttonVisibilityErrors;
    }

    public List<Product> getTableVisibilityErrors() {
        return this.tableVisibilityErrors;
    }

    public void addButtonVisibilityError(Product product) {
        this.buttonVisibilityErrors.add(product);
    }

    public void addTableVisibilityError(Product product) {
        this.tableVisibilityErrors.add(product);
    }
}
