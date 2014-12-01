package sizeguide;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.firefox.FirefoxBinary;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeUnit;

public class pdp_sizeguide_steps {

    private WebDriver browser;

    private String fullUrl;
    private List<String> nonVisibleSizeGuideButtonUrls;
    private List<String> failedSizeGuideViewUrls;

    private int totalPlpProducts;

    private static final String BASE_URL = "http://www.marksandspencer.com/";
    private static final String QUERY_URL_1 = "/?sortBy=product.best_selling|1&display=product&resultsPerPage=";
    private static final String QUERY_URL_2 = "&pageChoice=1&cachedFilters=%20generic-1090+product+";
    private static final String QUERY_URL_3 = "+0+480+0+480";

    private static final String SINGLE_SIZE_TEXT = "One Size";
    private static final String SIZE_GUIDE_VIEW_ERROR_TAG = "--SIZE_GUIDE_VIEW_ERROR--";
    private static final String BUTTON_VISIBILITY_ERROR_TAG = "--BUTTON_VISIBILITY_ERROR--";

    public pdp_sizeguide_steps(SharedDriver driver) {
        this.browser = driver;
        this.browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.browser.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        this.failedSizeGuideViewUrls = new ArrayList<String>();
        this.nonVisibleSizeGuideButtonUrls = new ArrayList<String>();
    }

    @Before
    public void setUp() {
        System.out.println("Setting up!");
    }

    @Given("^I visit the \"(.*?)\" PLP page using \"(.*?)\" and view the top (\\d+) best sellers$")
    public void i_visit_the_PLP_page_using_and_view_the_top_best_sellers(String name, String url, int topBestSellers) throws Throwable {
        // Construct the full url of the PDP to visit
        String fullQueryUrl = QUERY_URL_1 + topBestSellers + QUERY_URL_2 + topBestSellers + QUERY_URL_3;
        String fullUrl = BASE_URL + url + fullQueryUrl;
        this.fullUrl = fullUrl;

        browser.get(fullUrl);
    }

    @When("^I visit the PDP page of each product and click the size guide button$")
    public void i_visit_the_PDP_page_of_each_product_and_click_the_size_guide_button() throws Throwable {
        totalPlpProducts = getNumberOfProductsListed();

        // For each of the PLP products listed
        for (int i=0; i<totalPlpProducts; i++) {
            WebElement elem = getProductListedAtIndex(i);
            elem.click();
            System.out.println("Visiting: " + browser.getCurrentUrl());

            //if (productShouldHaveSizeGuide()) {
                if (isSizeGuideButtonVisible()) {
                    System.out.println("Should be clickable.");
                    clickSizeGuideButton();
                    System.out.println("Clicked button");

                    if (!isSizeGuideVisible()) {
                        System.out.println("Size guide not flying out.");
                        failedSizeGuideViewUrls.add(browser.getCurrentUrl());
                    }
                } else  {
                    System.out.println("Size guide button not visible.");
                    nonVisibleSizeGuideButtonUrls.add(browser.getCurrentUrl());
                }
            //}

            browser.get(fullUrl);
        }
    }

    @Then("^the size guide button should be visible and the size guide view should appear\\.$")
    public void the_size_guide_button_should_be_visible_and_the_size_guide_view_should_appear() throws Throwable {
        StringBuilder errorMsg = new StringBuilder();

        if (nonVisibleSizeGuideButtonUrls.size() > 0) {
            String urlsAsText = formatFailedUrls(nonVisibleSizeGuideButtonUrls);
            errorMsg.append(formatErrorMessage(BUTTON_VISIBILITY_ERROR_TAG, urlsAsText));
        }

        if (failedSizeGuideViewUrls.size() > 0) {
            String urlsAsText = formatFailedUrls(failedSizeGuideViewUrls);
            errorMsg.append(formatErrorMessage(SIZE_GUIDE_VIEW_ERROR_TAG, urlsAsText));
        }

        if (failedSizeGuideViewUrls.size() > 0 || nonVisibleSizeGuideButtonUrls.size() > 0) {
            throw new Throwable(errorMsg.toString());
        }
    }

    @After
    public void tearDown() {
        nonVisibleSizeGuideButtonUrls.clear();
        failedSizeGuideViewUrls.clear();
    }

    private Boolean productShouldHaveSizeGuide() {
        return !isProductOneSized();
    }

    private Boolean isSizeGuideButtonVisible() {
        return browser.findElements(By.cssSelector("a[href=\"#size-guide\"]")).size() != 0;
    }

    private Boolean isSizeGuideVisible() {
        List<WebElement> panels = browser.findElements(By.cssSelector("div.infopanel-position"));

        Boolean isFlyoutVisible = false;
        Boolean isSizeTableVisible = false;

        if (panels.size() != 0) {
            WebElement panel = panels.get(0);
            isFlyoutVisible = panel.isDisplayed();
            isSizeTableVisible = panel.findElements(By.cssSelector("table.size-information")).size() != 0;
        }

        return (isFlyoutVisible && isSizeTableVisible);
    }

    private void clickSizeGuideButton() throws Throwable {
        Thread.sleep(2000);
        WebElement sizeGuideBtn = browser.findElement(By.cssSelector("a[href=\"#size-guide\"]"));
        //scrollToElement(sizeGuideBtn);
        sizeGuideBtn.click();
        //sizeGuideBtn.sendKeys(Keys.RETURN);
    }

    private String formatErrorMessage(String errorTag, String errorMsg) {
        return errorTag + errorMsg + errorTag;
    }

    private String formatFailedUrls(List<String> urls) {
        return urls.toString();
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

    private Boolean isProductOneSized() {
        // Locate element to check if product only has one size
        List<WebElement> sizeInfoPanels = browser.findElements(By.cssSelector("div .size-info"));

        if (sizeInfoPanels.size() == 0) {
            return false;
        }

        WebElement selectedUnit = sizeInfoPanels.get(0).findElement(By.xpath(".//span[contains(@class, 'selected-unit')]"));
        return selectedUnit.getText().equals(SINGLE_SIZE_TEXT);
    }
}
