package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TicketsHomePage {
    private final By NewTicketButtonLocator=By.xpath("//*[@id=\"newTicket\"]/a");
    private final By LastTicketSendLocator=By.xpath("//tbody/tr[@data-categoryid='1'][1]/td/a[contains(@class, 'ticketLink')]");
    private final By SelectTicketCheckBoxButtonLocator = By.xpath("//*[starts-with(@id, 'ticketRow')]/td[8]");
    private final By DeleteChooseLocator = By.xpath("//*[@id=\"bulk-actions-popup\"]/ul/li[1]/a");
    //*[@id="ticketRow1455"]/td[1]/a
    private final By TicketsRowsLocator = By.xpath("//*[starts-with(@id, 'ticketRow') ]");
    private final By NameOfTicketLocator= By.xpath("//*[starts-with(@id, 'ticketRow')]/td[1]/a");
    static List<String> RowsSelector;

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
    public TicketsHomePage DeleteTicketByName(String NameOfTicket) {
        // Get all ticket rows
        List<WebElement> rows = browserActions.getDriver().findElements(TicketsRowsLocator);
        boolean ticketFound = false;

        // Iterate through each row to find matching ticket name
        for (WebElement row : rows) {
            // Get ticket name from the row - adjust the XPath index if needed
            WebElement nameElement = row.findElement(NameOfTicketLocator);
            String currentTicketName = nameElement.getText().trim();

            if (currentTicketName.equals(NameOfTicket)) {
                // Find and click the checkbox in this specific row
                WebElement checkbox = row.findElement(SelectTicketCheckBoxButtonLocator);
                checkbox.click();
                ticketFound = true;
                break; // Exit loop after finding the ticket
            }
        }

        if (!ticketFound) {
            throw new RuntimeException("Ticket '" + NameOfTicket + "' not found in the table");
        }

        // Click the delete button
        browserActions.click(DeleteChooseLocator);
        return new TicketsHomePage(browserActions.getDriver());
    }

    public TicketsHomePage acceptAlert() {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return new TicketsHomePage(browserActions.getDriver());
    }
}
