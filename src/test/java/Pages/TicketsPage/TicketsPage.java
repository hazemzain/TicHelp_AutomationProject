package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Pages.AssetsPages.AssetsPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class TicketsPage {
    private final By TicketButtonLocator=By.xpath("//*[@id=\"divBigHeader\"]/ul[1]/li[2]/a");

    BrowserActions browserActions;
    Assertion assertion;
    public TicketsPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public TicketsHomePage ClickTicketButton(){
        browserActions.click(TicketButtonLocator);
        return new TicketsHomePage(browserActions.getDriver());
    }



}
