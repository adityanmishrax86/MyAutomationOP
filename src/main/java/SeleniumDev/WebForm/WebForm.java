package SeleniumDev.WebForm;

import BasePageFactory.AbstractPage;
import Utils.BasicUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebForm extends AbstractPage {

    @FindBy(xpath = "//input[@name='my-text']")
    WebElement myText;

    @FindBy(xpath = "//input[@name='my-password']")
    WebElement myPassword;

    @FindBy(xpath = "//textarea[@name='my-textarea']")
    WebElement myTextArea;

    @FindBy(name = "my-disabled")
    WebElement myDisabledInput;

    @FindBy(name="my-readonly")
    WebElement myReadonlyInput;

    @FindBy(className = "form-select")
    WebElement mySelect;

    @FindBy(how = How.ID, using = "my-check-1")
    WebElement myCheck1;

    @FindBy(id = "my-check-2")
    WebElement myCheck2;

    @FindBy(id = "my-radio-1")
    WebElement myRadio1;

    @FindBy(id = "my-radio-1")
    WebElement myRadio2;

    @FindBy(name = "my-date")
    WebElement myDate;

    @FindBy(name = "my-range")
    WebElement myRange;

    @FindBy(tagName = "button")
    WebElement button;

    public WebForm(WebDriver driver) {
        super(driver);
    }

    public void enterMyText(String text) {
        clickElement(myText);
        fillInput(myText, text);
    }

    public void enterMyPassword(String password) {
        clickElement(myPassword);
        fillInput(myPassword, password);
    }

    public void enterMyTextArea(String text) {
        clickElement(myTextArea);
        fillInput(myTextArea, text);
    }

    public void checkDisabledElement() {
        if(myDisabledInput.isEnabled()) {
            logger.info("The element is enabled");
        } else
            logger.info("The element is disabled");
        logger.info("Disabled Element Value: %s\n", myDisabledInput.getAttribute("placeholder"));
    }

    public void checkReadonlyElement() {
        if(myReadonlyInput.isDisplayed()) {
            logger.info("The element is displayed");
        } else
            logger.info("The element is not displayed");
        logger.info("Read only Value: %s\n", myReadonlyInput.getAttribute("value"));
    }

    public void selectRandomOption() {
        Select select = new Select(mySelect);
        List<WebElement> selectOptions = select.getOptions();
        if(!selectOptions.isEmpty()) {
            int index = (int) (Math.random() * selectOptions.size());
            select.selectByVisibleText(selectOptions.get(index).getText());
            logger.info("Selected option: %s\n", selectOptions.get(index).getText());
        }
    }

    public void selectRandomDataList() {
        WebElement myDatalist = findElement(By.cssSelector("[placeholder='Type to search...']"));
        if(null != myDatalist) {
            clickElement(myDatalist);
            List<WebElement> myDataLists = findElements(By.xpath("//datalist/option"));
            if (!myDataLists.isEmpty()) {
                int index = (int) (Math.random() * myDataLists.size());
                if (!findElements(By.xpath(String.format("//datalist/option[contains(@value,'%s')]", myDataLists.get(index).getAttribute("value")))).isEmpty()) {
                    fillInput(myDatalist, myDataLists.get(index).getAttribute("value"));
                }
                logger.info("Selected option from DataList: %s\n", myDataLists.get(index).getAttribute("value"));
            }
        } else
            System.out.println("Datalist is null");
    }

    public void checkRadioAndCheckbox() {
        clickElement(myCheck1);
        clickElement(myCheck2);
        clickElement(myRadio1);
        clickElement(myRadio2);
    }

    public void selectColorPicker() {
        String randomColor = BasicUtilities.generateHexColorString();
        executeJavascript(String.format("document.querySelector('.form-control-color').value='%s';",randomColor));
    }


    public void selectDatePicker() {
        String randomDate = BasicUtilities.generateRandomDate("dd");
        clickElement(myDate);
        WebElement datePicker = findElement(By.xpath(String.format("//div[contains(@class,'datepicker-days')]//tr//td[contains(text(),'%s')]", randomDate)));
        if(null != datePicker)
            datePicker.click();

        findElement(By.tagName("h1")).click();
    }

    public void selectRange()  {
            executeJavascript("arguments[0].value = arguments[1];", myRange, 7);
            logger.info(myRange.getAttribute("value"));
    }

    public void clickSubmitButton() {
        clickElement(button);
    }

    public void validateDataSubmittedOrNot() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
            WebElement receivedData = findElement(By.id("message"));
            logger.info("Received data: {}", receivedData.getText());
            logger.info("Current URL: {}", driver.getCurrentUrl());
        } catch (TimeoutException e) {
            logger.error("No data received");
        }
    }

}
