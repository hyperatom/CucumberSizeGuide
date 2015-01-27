package sizeguide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementFinder {

    WebDriver browser;

    private static final String NEW_SIZE_GUIDE_SELECTOR
            = ".lightbox-size-guide";

    private static final String OLD_SIZE_GUIDE_SELECTOR
            = "div.infopanel-position";

    private static final String SIZE_GUIDE_BUTTON_SELECTOR
            = ".size-info .size-guide-trigger a.panel";

    public ElementFinder(WebDriver browser) {
        this.browser = browser;
    }

    public WebElement getSizeGuideButton() {
        return browser.findElements(By.cssSelector(SIZE_GUIDE_BUTTON_SELECTOR)).get(0);
    }

    public Boolean isSizeGuideButtonPresent() {
        return browser.findElements(By.cssSelector(SIZE_GUIDE_BUTTON_SELECTOR)).size() > 0;
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
