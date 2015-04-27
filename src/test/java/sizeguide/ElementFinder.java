package sizeguide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ElementFinder {

    WebDriver browser;

    private static final String NEW_SIZE_GUIDE_SELECTOR
            = ".lightbox-size-guide";

    private static final String OLD_SIZE_GUIDE_SELECTOR
            = "div.infopanel-position";

    private static final String SIZE_GUIDE_BUTTON_SELECTOR
            = ".size-info a.panel";

    private static final String ONE_SIZE_LABEL_SELECTOR
            = ".product-detail-page .information .selected-unit";

    private static final String ONE_SIZE_LABEL_TEXT
            = "One Size";

    private static final String CSS_PRODUCT_LINK_SELECTOR
            = "h3 .prodAnchor";

    private static final String CSS_PRODUCT_CODE_SELECTOR
            = ".code";

    public ElementFinder(WebDriver browser) {
        this.browser = browser;
    }

    public void setBrowser(WebDriver browser) {
        this.browser = browser;
    }

    public String getProductCode() {
        List<WebElement> codeElems = browser.findElements(By.cssSelector(CSS_PRODUCT_CODE_SELECTOR));

        if (codeElems.size() == 0) {
            return "N/A";
        }

        return codeElems.get(0).getText().trim();
    }

    public List<String> getAllProductLinks() {

        List<WebElement> productAnchors = browser.findElements(
                By.cssSelector(CSS_PRODUCT_LINK_SELECTOR));

        List<String> links = new ArrayList<String>();

        for (WebElement anchor : productAnchors) {
            try {
                links.add(anchor.getAttribute("href"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return links;
    }

    public WebElement getSizeGuideButton() {
        return browser.findElements(By.cssSelector(SIZE_GUIDE_BUTTON_SELECTOR)).get(0);
    }

    public Boolean isSizeGuideButtonPresent() {
        return browser.findElements(By.cssSelector(SIZE_GUIDE_BUTTON_SELECTOR)).size() > 0;
    }

    public Boolean isProductOneSized() {
        List<WebElement> elems = browser.findElements(By.cssSelector(ONE_SIZE_LABEL_SELECTOR));

        return elems.size() > 0 && containsOneSizeLabel(elems);
    }

    private Boolean containsOneSizeLabel(List<WebElement> elems) {
        Boolean oneSize = false;

        for (WebElement elem : elems) {
            if (elem.getText().contains(ONE_SIZE_LABEL_TEXT)) {
                oneSize = true;
            }
        }

        return oneSize;
    }

    public Boolean isSizeGuidePresent() {
        return browser.findElements(By.cssSelector(getSizeGuideSelector())).size() > 0;
    }

    public WebElement getSizeGuide() {
        return browser.findElements(By.cssSelector(getSizeGuideSelector())).get(0);
    }

    private String getSizeGuideSelector() {
        return Config.IS_NEW_SIZE_GUIDES ? NEW_SIZE_GUIDE_SELECTOR : OLD_SIZE_GUIDE_SELECTOR;
    }
}
