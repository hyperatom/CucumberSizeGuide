package sizeguide;

public class Category {

    private String         testDescription;
    private CategoryErrors categoryErrors = new CategoryErrors();

    public CategoryErrors getCategoryErrors() {
        return categoryErrors;
    }

    public void setCategoryErrors(CategoryErrors categoryErrors) {
        this.categoryErrors = categoryErrors;
    }

    public String getTestDescription () {
        return testDescription;
    }

    public void setTestDescription (String testDescription) {
        this.testDescription = testDescription;
    }
}
