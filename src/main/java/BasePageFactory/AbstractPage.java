package BasePageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /**
     * Finds an element on the page matching the given By locator.
     * @param locator the By locator to use when searching for the element
     * @return the located element
     */
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Finds all elements on the page matching the given By locator.
     * @param locator the By locator to use when searching for the elements
     * @return a list of all matching elements
     */
    public List<WebElement> findElements(By locator) { return driver.findElements(locator); } // <T extends WebElement>

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
        executor.executeScript("arguments[0].click();", element);
    }

    public void executeJavascript(String script) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(script);
    }
}
