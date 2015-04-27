package sizeguide;

import com.google.gson.Gson;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class pdp_sizeguide_steps {

    private WebDriver       browser;
    private ElementFinder   finder;
    private Category        errors;
    private String          testCategory;
    private String          testDescription;

    public pdp_sizeguide_steps(SharedDriver driver) {

        errors = new Category();

        this.browser  = driver;
        this.finder   = new ElementFinder(driver);

        this.browser.manage().timeouts().implicitlyWait(5,   TimeUnit.SECONDS);
        this.browser.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    private void setTestCategory(Collection<String> sourceTags) {
        testCategory = ((String) sourceTags.toArray()[0]).substring(1);
    }


    public String getTestDescription(String name, String urlTag, int topBestSellers) {

        return "I visit the "       + name +
               " PLP page using "   + urlTag +
               " and view the top " + topBestSellers +
               " best sellers";
    }

    @Before
    public void setUp(Scenario scenario) {

        setTestCategory(scenario.getSourceTagNames());
    }

    @Given("^I visit the \"(.*?)\" PLP page using \"(.*?)\" and view the top (\\d+) best sellers$")
    public void i_visit_the_PLP_page_using_and_view_the_top_best_sellers(String name, String urlTag, int topBestSellers) throws Throwable {

        String currentPlpUrl = UrlConstructor.constructPlpUrl(urlTag, topBestSellers);
        testDescription = getTestDescription(name, urlTag, topBestSellers);

        navigateTo(currentPlpUrl);
    }

    @When("^I visit the PDP page of each product and click the size guide button$")
    public void i_visit_the_PDP_page_of_each_product_and_click_the_size_guide_button() throws Throwable {

        List<String> productLinks = finder.getAllProductLinks();

        // For each of the PLP products listed
        for (String link : productLinks) {

            navigateTo(link);

            Thread.sleep(3500);

            if (!finder.isProductOneSized()) {
                if (finder.isSizeGuideButtonPresent()) {
                    clickSizeGuideButton();
                    checkTableVisibility();
                } else {
                    logButtonVisibilityError();
                }
            }
        }
    }

    @Then("^the size guide button should be visible and the size guide view should appear\\.$")
    public void the_size_guide_button_should_be_visible_and_the_size_guide_view_should_appear() throws Exception {

        errors.setTestDescription(testDescription);

        TestInfo.setTestResults(testCategory, errors);

        if (sizeGuidesHaveErrors()) {
            throw new Exception(new Gson().toJson(errors));
        }
    }

    private void navigateTo(String url) {
        browser.get(url);
        finder.setBrowser(browser);
    }

    private void logButtonVisibilityError() {
        Product product = new Product(browser.getCurrentUrl(), finder.getProductCode());
        errors.getCategoryErrors().addButtonVisibilityError(product);
    }

    private void logTableVisibilityError() {
        Product product = new Product(browser.getCurrentUrl(), finder.getProductCode());
        errors.getCategoryErrors().addTableVisibilityError(product);
    }

    private void checkTableVisibility() {
        if (!isSizeGuideTableVisible()) {
            logTableVisibilityError();
        }
    }

    private Boolean sizeGuidesHaveErrors() {
        return errors.getCategoryErrors().getTableVisibilityErrors().size() > 0 ||
                errors.getCategoryErrors().getButtonVisibilityErrors().size() > 0;
    }

    private Boolean isSizeGuideTableVisible () {
        Boolean isOverlayVisible = false;
        Boolean isTableVisible   = false;

        if (finder.isSizeGuidePresent()) {
            WebElement panel = finder.getSizeGuide();
            isOverlayVisible = panel.isDisplayed();
            isTableVisible   = panel.findElements(By.cssSelector("table.size-information")).size() != 0;
        }

        return (isOverlayVisible && isTableVisible);
    }

    private void clickSizeGuideButton() throws Throwable {
        WebElement sizeGuideBtn = finder.getSizeGuideButton();

        if (isSeleniumHeadless()) {
            sizeGuideBtn.click();
        } else {
            scrollToElement(sizeGuideBtn);
            hoverElement(sizeGuideBtn);
            sizeGuideBtn.click();
        }
    }

    private void hoverElement(WebElement elem) {
        Actions builder = new Actions(browser);
        Actions hoverOverRegistrar = builder.moveToElement(elem);
        hoverOverRegistrar.perform();
    }

    private Boolean isSeleniumHeadless() {
        return Config.IS_SELENIUM_HEADLESS;
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) browser;
        jse.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
    }
}
