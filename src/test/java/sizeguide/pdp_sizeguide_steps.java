package sizeguide;

import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class pdp_sizeguide_steps {

    private WebDriver       browser;
    private ElementFinder   finder;
    private SizeGuideErrors errors;
    private String          currentPlpUrl;

    public pdp_sizeguide_steps(SharedDriver driver) {
        this.browser = driver;
        this.finder  = new ElementFinder(driver);
        this.browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.browser.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Before
    public void setUp() {
        FileWriter.createGenerationDateFile();
        errors = new SizeGuideErrors();
    }

    @Given("^I visit the \"(.*?)\" PLP page using \"(.*?)\" and view the top (\\d+) best sellers$")
    public void i_visit_the_PLP_page_using_and_view_the_top_best_sellers(String name, String urlTag, int topBestSellers) throws Throwable {
        currentPlpUrl = UrlConstructor.constructPlpUrl(urlTag, topBestSellers);
        navigateTo(currentPlpUrl);
    }

    @When("^I visit the PDP page of each product and click the size guide button$")
    public void i_visit_the_PDP_page_of_each_product_and_click_the_size_guide_button() throws Throwable {
        int totalPlpProducts = getNumberOfProductsListed();

        // For each of the PLP products listed
        for (int i=0; i<totalPlpProducts; i++) {
            clickProduct(i);

            Thread.sleep(3000);

            if (!finder.isProductOneSized()) {
                if (finder.isSizeGuideButtonPresent()) {
                    clickSizeGuideButton();
                    checkTableVisibility();
                } else {
                    logButtonVisibilityError();
                }
            }

            navigateTo(currentPlpUrl);
        }
    }

    @Then("^the size guide button should be visible and the size guide view should appear\\.$")
    public void the_size_guide_button_should_be_visible_and_the_size_guide_view_should_appear() throws Exception {
        if (sizeGuidesHaveErrors()) {
            throw new Exception(new Gson().toJson(errors));
        }
    }

    private void navigateTo(String url) {
        browser.get(url);
    }

    private void logButtonVisibilityError() {
        errors.addButtonVisibilityError(browser.getCurrentUrl());
    }

    private void checkTableVisibility() {
        if (!isSizeGuideTableVisible()) {
            errors.addTableVisibilityError(browser.getCurrentUrl());
        }
    }

    private void clickProduct(int index) {
        WebElement elem = getProductListedAtIndex(index);
        elem.click();
    }

    private Boolean sizeGuidesHaveErrors() {
        return errors.getTableVisibilityErrors().size() > 0 ||
               errors.getButtonVisibilityErrors().size() > 0;
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
        //Thread.sleep(8000);
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

    private WebElement getProductListedAtIndex(int index) {
        WebElement product = browser.findElements(By.cssSelector("li[itemtype='http://schema.org/Product']")).get(index);
        return product.findElement(By.xpath(".//a[contains(@class, 'prodAnchor')]"));
    }

    private int getNumberOfProductsListed() {
        return browser.findElements(By.cssSelector("li[itemtype='http://schema.org/Product']")).size();
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) browser;
        jse.executeScript("window.scrollTo(0," + element.getLocation().y + ")");
    }
}
