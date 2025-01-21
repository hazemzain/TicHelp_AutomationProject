package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TicketsHomePage {
    private final By NewTicketButtonLocator=By.xpath("//*[@id=\"newTicket\"]/a");
    private final By LastTicketSendLocator=By.xpath("//tbody/tr[@data-categoryid='1'][1]/td/a[contains(@class, 'ticketLink')]");

    BrowserActions browserActions;
    Assertion assertion;
    public TicketsHomePage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public CreateTicketForm ClickNewTicketButton(){
        browserActions.click(NewTicketButtonLocator);
        return new CreateTicketForm(browserActions.getDriver());
    }
    public String GetLastTicketSubject(){
        System.out.println("the last Ticket subject is"+browserActions.getText(LastTicketSendLocator));
        return browserActions.getText(LastTicketSendLocator);
    }

    public TicketDetailsPage ClickInLastTicket(){
        browserActions.click(LastTicketSendLocator);
        return new TicketDetailsPage(browserActions.getDriver());
    }
}
