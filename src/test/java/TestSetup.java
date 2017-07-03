import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class TestSetup {

    private WebDriver driver;
    private static String driverPath = "./common/";

    public WebDriver getDriver() {
        return this.driver;
    }

    private void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(appURL);
                break;
            case "firefox":
                driver = initFirefoxDriver(appURL);
                break;
            case "headless":
                driver = initChromeDriverHeadLess(appURL);
                break;
            default:
                driver = initChromeDriverHeadLess(appURL);
        }
    }

    private WebDriver initChromeDriverHeadLess(String appURL) {
        System.out.println("Start Chrome in Headless");

//        System.setProperty("webdriver.chrome.driver", driverPath
//                + "chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome");
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        this.driver = new ChromeDriver(options);
        //driver.navigate().to(appURL);
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
            System.out.println("Error in browser Setup: " + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}