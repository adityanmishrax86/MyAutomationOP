package StepDefs;

import Controllers.WebFormController;
import SeleniumDev.WebForm.WebForm;
import Utils.BrowserUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseFeatureStep {

    @Before
    public void setUp() {
        System.out.println("Setting up browser");
        BrowserUtils.getInstance().setBrowser("firefox");
    }

    @After
    public void tearDown() {
        System.out.println("Closing browser");
        WebDriver driver = BrowserUtils.getInstance().getDriver();
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I have {int} cukes in my belly")
    public void iHaveCukesInMyBelly(int arg0) {
        System.out.format("I have %d cukes in my belly\n", arg0);
    }

    @When("I eat some")
    public void iEatCukes() {
        System.out.println("I eat %d cukes\n");
    }

    @Then("my belly should growl")
    public void iShouldHaveCukesInMyBelly() {
        System.out.println("I should have %d cukes in my belly\n");
    }


    @Then("I will run a base check")
    public void i_will_run_a_base_check() {
        WebFormController controller = new WebFormController();
        controller.initial();
        controller.fillUpAllInputField();
    }
}
