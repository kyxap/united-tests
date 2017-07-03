import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest extends TestSetup {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void configure() {
        this.driver = getDriver();
    }

    @Test
    public void verifyHomePage() {
        HomePage homePage = new HomePage(this.driver);
        Assert.assertTrue(homePage.verifyMainPageTitle(), "Home page title doesn't match");
    }

}