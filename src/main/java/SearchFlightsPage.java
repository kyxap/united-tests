import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchFlightsPage {

    private WebDriver driver;

    SearchFlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean findContainsText(String text) {
        WebElement textEl = this.driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        return textEl.isDisplayed();
    }
}
