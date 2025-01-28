package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Pages.TicketsPage.TicketsHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DeletedTicketPage {
    private final By TicketsRowsLocator = By.xpath("//*[starts-with(@id, 'ticketRow') ]");
    private final By NameOfTicketLocator = By.xpath("//*[starts-with(@id, 'ticketRow')]/td[1]/a");

    BrowserActions browserActions;
    Assertion assertion;

    public DeletedTicketPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    public boolean FindDeletedTicketByNameInDeletedPage(String NameOfTicket) {
        // Get all ticket rows
        List<WebElement> rows = browserActions.getDriver().findElements(TicketsRowsLocator);
        boolean ticketFound = false;

        // Iterate through each row to find matching ticket name
        for (WebElement row : rows) {
            // Get ticket name from the row - adjust the XPath index if needed
            WebElement nameElement = row.findElement(NameOfTicketLocator);
            String currentTicketName = nameElement.getText().trim();

            if (currentTicketName.equals(NameOfTicket)) {

                ticketFound = true;
                break; // Exit loop after finding the ticket
            }
        }
        return ticketFound;

    }
}
