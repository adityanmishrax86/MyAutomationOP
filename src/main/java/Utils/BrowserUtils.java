package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserUtils {

    private static final ThreadLocal<BrowserUtils> instance = new ThreadLocal<>();
    protected static Logger logger = LogManager.getLogger(BrowserUtils.class);

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
                logger.info("Setting up Chrome");
                setUpChromeBrowser();
                break;
            case "firefox":
                logger.info("Setting up Firefox");
                setUpFireFoxBrowser();
                break;
            case "edge":
                logger.info("Setting up Edge");
                setUpEdgeBrowser();
                break;
            case "safari":
                logger.info("Setting up Safari");
                setUpSafariBrowser();
                break;
            default:
                logger.error("Please select a valid browser from this options. Chrome/Firefox/Safari/Edge");
                System.exit(1);
                break;
        }
        setUpBasicCapabilities();
    }

    public void setUpBasicCapabilities() {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    protected ChromeOptions setupChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-default-apps");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-features=IsolateOrigins,site-per-process");
        if (Configurator.getProperty("browser.headless").equals("true")) {
            options.addArguments("--headless");
            logger.info("Chrome Browser will Run in Headless Mode");
        }
        return options;
    }

    protected FirefoxOptions setupFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-default-apps");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-features=IsolateOrigins,site-per-process");
        if (Configurator.getProperty("browser.headless").equals("true")) {
            options.addArguments("--headless");
            logger.info("FirefoxBrowser will Run in Headless Mode");
        }
        return options;
    }

    protected SafariOptions setupSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setCapability("Safari:options:allowPopups", false);
        return options;
    }


    protected void setUpChromeBrowser() {
        ChromeOptions options = setupChromeOptions();
        driver = new ChromeDriver(options);
    }

    protected void setUpFireFoxBrowser() {
        FirefoxOptions options = setupFirefoxOptions();
        driver = new FirefoxDriver(options);
    }
    protected void setUpEdgeBrowser() {
        driver = new EdgeDriver();
    }

    protected void setUpSafariBrowser() {
//        SafariOptions options = setupSafariOptions();
        driver = new SafariDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }



}
