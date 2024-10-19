package BasePageFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class AbstractPage {

    protected WebDriver driver;
    public static Logger logger = LogManager.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        logger.info("Page initialized");
    }


    /**
     * Finds an element on the page matching the given By locator.
     * If the element is found, its string representation is logged at debug level.
     * If the element is not found, a NoSuchElementException is caught and logged at error level.
     * @param locator the By locator to use when searching for the element
     * @return the matching element, or null if no element is found
     */
    public WebElement findElement(By locator) {
        WebElement element = null;
        logger.debug("Finding element: {}", locator);
        try {
            element = driver.findElement(locator);
            logger.debug(element.toString());
        } catch (NoSuchElementException ne) {
            logger.error("Element not found: {}", locator);
        }
        return element;
    }

    /**
     * Finds all elements on the page matching the given By locator.
     * @param locator the By locator to use when searching for the elements
     * @return a list of all matching elements
     */
    public List<WebElement> findElements(By locator) {
        List<WebElement> elements = null;
        try {
            elements = driver.findElements(locator);
        } catch (NullPointerException npe) {
            logger.error("NullPointerException when finding elements: {}", locator);
        } catch (UnhandledAlertException uae) {
            logger.error("Unhandled alert exception when finding elements: {}", locator);
            uae.printStackTrace();
        }
        if (elements == null) {
            logger.error("Elements is null when finding elements: {}", locator);
        } else {
            logger.debug("Found {} elements matching: {}", elements.size(), locator);
        }
        return elements;
    }

    /**
     * Clicks the given element.
     *
     * @param el the element to click
     */
    public void clickElement(WebElement el) {
        el.click();
    }

    /**
     * Fills in a text input with the given text.
     *
     * @param el the text input element
     * @param text the text to fill in
     */
    public void fillInput(WebElement el, String text) {
        el.sendKeys(text);
    }

    /**
     * Clicks the given element, using JavaScript to execute a click event.
     * <p>
     * This is necessary for some web pages where the normal {@link #clickElement(WebElement)} method does not work.
     * <p>
     * @param element the element to click
     */
    public void clickElementExecutingJavascript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            executor.executeScript("arguments[0].click();", element);
        } catch (JavascriptException je) {
            logger.error("Couldn't click element executing Javascript: {}", element.toString());
            logger.error(je.getMessage());
        }
    }

    public void executeJavascript(String script) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        logger.debug("Executing javascript: {}", script);
        try {
            executor.executeScript(script);
        } catch (JavascriptException je) {
            logger.error("Error executing javascript: {}", script);
            logger.error(je.getMessage());
            logger.error("Javascript Exception: {}", je);
        }
    }

    public void executeJavascript(String script, WebElement el, Object value) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        logger.debug("Executing javascript: {}", script);
        try {
            executor.executeScript(script, el, value);
        } catch (JavascriptException je) {
            logger.error("Error executing javascript on Element {}: {}", el.toString(),script);
            logger.error(je.getMessage());
        }
    }
}
