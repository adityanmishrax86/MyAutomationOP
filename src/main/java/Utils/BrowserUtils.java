package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserUtils {

    private static final ThreadLocal<BrowserUtils> instance = new ThreadLocal<>();

    public static BrowserUtils getInstance() {
        if (instance.get() == null) {
            instance.set(new BrowserUtils());
        }
        return instance.get();
    }

    private static WebDriver driver;

    public void setBrowser(String browser) {
        switch (browser) {
            case "chrome":
                System.out.println("Setting up Chrome");
                setUpChromeBrowser();
                break;
            case "firefox":
                System.out.println("Setting up Firefox");
                setUpCFireFoxBrowser();
                break;
            case "edge":
                System.out.println("Setting up Edge");
                setUpEdgeBrowser();
                break;
            case "safari":
                System.out.println("Setting up Safari");
                setUpSafariBrowser();
                break;
            default:
                System.out.println("Please select a valid browser from this options. Chrome/Firefox/Safari/Edge");
                break;
        }
    }


    protected void setUpChromeBrowser() {
        driver = new ChromeDriver();
    }

    protected void setUpCFireFoxBrowser() {
        driver = new FirefoxDriver();
    }
    protected void setUpEdgeBrowser() {
        driver = new EdgeDriver();
    }

    protected void setUpSafariBrowser() {
        driver = new SafariDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }



}
