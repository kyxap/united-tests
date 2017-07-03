import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestSetup {

    private WebDriver driver;
    private static String driverPath = "./common/";

    public WebDriver getDriver() {
        return this.driver;
    }

    private void setDriver(String browserType, String appURL) throws MalformedURLException {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(appURL);
                break;
            case "firefox":
                driver = initFirefoxDriver(appURL);
                break;
            case "headless":
                driver = initChromeDriverHeadLess(appURL);
            default:
                driver = initChromeDriver(appURL);
        }
    }

    private WebDriver initChromeDriverHeadLess(String appURL) throws MalformedURLException {
        // --disable-gpu --dump-dom
        //System.setProperty("webdriver.chrome.driver", driverPath
         //       + "chromedriver");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.MOUNTAIN_LION);
        this.driver = new RemoteWebDriver(
                new URL("http://localhost:9222/"), capabilities);

        defConfiguration(driver);
        driver.navigate().to(appURL);
        return driver;
    }

    private WebDriver initChromeDriver(String appURL) {
        System.setProperty("webdriver.chrome.driver", driverPath
                + "chromedriver");
        this.driver = new ChromeDriver();
        defConfiguration(driver);
        driver.navigate().to(appURL);
        return driver;
    }

    private WebDriver initFirefoxDriver(String appURL) {
        this.driver = new FirefoxDriver();
        defConfiguration(driver);
        driver.navigate().to(appURL);
        return driver;
    }

    private static void defConfiguration (WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Parameters({ "browserType", "appURL" })
    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String appURL) {
        try {
            setDriver(browserType, appURL);

        } catch (Exception e) {
            System.out.println("Error: " + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}