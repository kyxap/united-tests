import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    private WebDriver driver;
    private By fromInputField = By.id("Origin");
    private By toInputField = By.id("Destination");
    private By departDate = By.cssSelector("#DepartDate");
    private By returnDate = By.cssSelector("#ReturnDate");
    private By submitButton = By.id("flightBookingSubmit");
    private Actions action;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(this.driver);
    }

    public boolean trueTest() {
        return true;
    }
    public void actionMoveClickSendKey(WebElement element, String input) {
        this.action.moveToElement(element).click().sendKeys(input).build().perform();
    }

    public SearchFlightsPage searchFlightsByShortName(IATA from, IATA to, String dep, String ret){
        WebElement departDateElement = driver.findElement(departDate);
        WebElement returnDateElement = driver.findElement(returnDate);
        actionMoveClickSendKey(departDateElement, dep);
        actionMoveClickSendKey(returnDateElement, ret);
        WebElement fromInputFieldElement = driver.findElement(fromInputField);
        WebElement toInputFieldElement = driver.findElement(toInputField);
        actionMoveClickSendKey(fromInputFieldElement, from.getShort());
        actionMoveClickSendKey(toInputFieldElement, to.getShort());
        WebElement submitButtonElement = this.driver.findElement(submitButton);
        submitButtonElement.click();
        return new SearchFlightsPage(driver);
    }

    public String getPageTitle(){
        String title = driver.getTitle();
        return title;
    }

    public boolean verifyMainPageTitle() {
        String expectedTitle = "United Airlines – Airline Tickets, Travel Deals and Flights";
        return getPageTitle().contains(expectedTitle);
    }
}