package Controllers;

import SeleniumDev.WebForm.WebForm;
import Utils.BrowserUtils;
import org.openqa.selenium.WebDriver;

public class WebFormController {

    WebForm webForm;
    WebDriver driver;

    public WebFormController() {
        this.webForm = new WebForm(BrowserUtils.getInstance().getDriver());
        this.driver = BrowserUtils.getInstance().getDriver();
    }

    public void initial() {
        if(null != driver)
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        else {
            System.out.println("Driver is null");
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
        webForm.chechRadioAndCheckbox();
        webForm.selectColorPicker();
        webForm.clickButton();
    }
}
