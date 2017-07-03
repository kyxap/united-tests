
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchFlightsTest extends BaseTest {
    private WebDriver driver;
    HomePage homePage;
    SearchFlightsPage searchFlightsPagePage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test(priority = 1)
    public void searchFlightByShortNamePDXtoLAX() {
        HomePage homePage = new HomePage(driver);
        searchFlightsPagePage = homePage.searchFlightsByShortName(IATA.PDX, IATA.LAX, "Dec 15 2017", "Dec 30 2017");
    }

    @Test(priority = 2)
    public void findFullNameOfDestination() {
        Assert.assertTrue(searchFlightsPagePage.findContainsText(IATA.LAX.getFull()), "Unable to find " + IATA.LAX.getFull());
    }

    @Test(priority = 2)
    public void findFullNameOfDeparture() {
        Assert.assertTrue(searchFlightsPagePage.findContainsText(IATA.PDX.getFull()), "Unable to find " + IATA.PDX.getFull());
    }
}
