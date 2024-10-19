package Controllers;

import SeleniumDev.WebForm.WebForm;
import Utils.BrowserUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class WebFormController {

    WebForm webForm;
    WebDriver driver;
    Logger logger = LogManager.getLogger(WebFormController.class);

    public WebFormController() {
        this.webForm = new WebForm(BrowserUtils.getInstance().getDriver());
        this.driver = BrowserUtils.getInstance().getDriver();
    }

    /**
     * Opens the web form url in the browser
     */
    public void initial() {
        if(null != driver) {
            // Open the web form page
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            logger.info("Navigating to URL: {}", driver.getCurrentUrl());
        } else {
            logger.log(Level.DEBUG, "Driver is null. Exiting...");
            System.exit(1);
        }
    }

    public void fillUpAllInputField() {
        webForm.enterMyText("Hello There");
        webForm.enterMyPassword("A Long Secret Password");
        webForm.enterMyTextArea("Another Long Text to fit in the text area");
        webForm.checkDisabledElement();
        webForm.checkReadonlyElement();
        webForm.selectRandomOption();
        webForm.selectRandomDataList();
        webForm.checkRadioAndCheckbox();
        webForm.selectColorPicker();
        webForm.selectDatePicker();
        webForm.selectRange();
        webForm.clickSubmitButton();
        webForm.validateDataSubmittedOrNot();
    }
}
