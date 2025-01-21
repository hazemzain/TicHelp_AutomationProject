package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Pages.AssetsPages.AssetsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TicketsPage {
    private final By TicketButtonLocator=By.xpath("//*[@id=\"divBigHeader\"]/ul[1]/li[2]/a");
    //*[@id="ticketRow1455"]/td[1]/a
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
