import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By fromInputField = By.id("Origin");
    private By toInputField = By.id("Destination");
    private By departDate = By.cssSelector("#DepartDate");
    private By returnDate = By.cssSelector("#ReturnDate");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean fillFlightDates(String dep, String ret) {
        WebElement departDateElement = driver.findElement(departDate);
        WebElement returnDateElement = driver.findElement(returnDate);
        departDateElement.sendKeys(dep);
        returnDateElement.sendKeys(ret);
        //TODO: state verification
        return true;
    }

    public boolean fillFromTo(String from, String to) {
        WebElement fromInputFieldElement = driver.findElement(fromInputField);
        WebElement toInputFieldElement = driver.findElement(toInputField);
        fromInputFieldElement.sendKeys(from);
        toInputFieldElement.sendKeys(to);

        //TODO: state verification
        return true;
    }

    public SearchFlightsPage submitSearchFormBy (WebElement element) {
        if (element == null) {
            WebElement fromInputFieldElement = driver.findElement(fromInputField);
            fromInputFieldElement.sendKeys(Keys.ENTER);
        } else element.sendKeys(Keys.ENTER);
        return new SearchFlightsPage(this.driver);
    }

    public SearchFlightsPage searchFlightsByShortName(IATA from, IATA to, String dep, String ret){
        SearchFlightsPage searchFlightsPage = new SearchFlightsPage();
        if (fillFlightDates(dep, ret) && fillFromTo(from.getShort(), to.getShort())) {
            searchFlightsPage = submitSearchFormBy(null);
        } else searchFlightsPage = null;
        return searchFlightsPage;
    }

    public String getPageTitle(){
        String title = driver.getTitle();
        return title;
    }

    public boolean verifyMainPageTitle() {
        String expectedTitle = "United Airlines – Airline Tickets, Travel Deals and Flights";
        System.out.println(getPageTitle());
        return getPageTitle().contains(expectedTitle);
    }
}