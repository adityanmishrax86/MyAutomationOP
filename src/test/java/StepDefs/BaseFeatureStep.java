package StepDefs;

import Controllers.WebFormController;
import Utils.BrowserUtils;

import Utils.Configurator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class BaseFeatureStep {

    @Before
    public void setUp() {
        try {
            Configurator.readConfigProperties();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Setting up browser");
        BrowserUtils.getInstance().setBrowser(Configurator.getProperty("browser"));
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
        System.out.println("I eat some cukes\n");
    }

    @Then("my belly should growl")
    public void iShouldHaveCukesInMyBelly() {
        System.out.println("I should have some more cukes in my belly\n");
    }


    @Then("I will run a base check")
    public void i_will_run_a_base_check() {
        WebFormController controller = new WebFormController();
        controller.initial();
        controller.fillUpAllInputField();
    }
}
