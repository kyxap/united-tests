
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchFlightsTest extends TestSetup {
    private WebDriver driver;
    HomePage homePage;
    SearchFlightsPage searchFlightsPagePage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        this.homePage = new HomePage(driver);
        searchFlightsPagePage = homePage.searchFlightsByShortName(IATA.PDX, IATA.LAX, "Dec 15 2017", "Dec 30 2017");
    }

    @Test()
    public void findFullNameOfDestination() {
        Assert.assertTrue(searchFlightsPagePage.findContainsText(IATA.LAX.getFull()), "Unable to find " + IATA.LAX.getFull());
    }

    @Test()
    public void findFullNameOfDeparture() {
        Assert.assertTrue(searchFlightsPagePage.findContainsText(IATA.PDX.getFull()), "Unable to find " + IATA.PDX.getFull());
    }

    @Test()
    public void findShortNameOfDestination() {
        Assert.assertTrue(searchFlightsPagePage.findContainsText(IATA.LAX.getFull()), "Unable to find " + IATA.LAX.getShort());
    }

    @Test()
    public void findShortNameOfDeparture() {
        HomePage h = new HomePage(driver);
        Assert.assertTrue(searchFlightsPagePage.findContainsText(IATA.PDX.getFull()), "Unable to find " + IATA.PDX.getShort());
    }
}
