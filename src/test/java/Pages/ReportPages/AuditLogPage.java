package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AuditLogPage {
    private final By TicketsRowsLocator = By.xpath("//tbody/tr");
    private final By NameOfTicketLocator = By.xpath("//*[starts-with(@id, 'content')]/div[6]/table/tbody/tr/td");
    private final By BuildButtonLocator=By.xpath("//*[@id=\"btnBuild\"]");
    BrowserActions browserActions;
    Assertion assertion;
    public AuditLogPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public void verifyTheLastActionLogInAuditReport(String NameOfTicket) {
        // Get all ticket rows
        List<WebElement> rows = browserActions.getDriver().findElements(TicketsRowsLocator);
        boolean ticketFound = false;

        // Iterate through each row to find matching ticket name
        for (WebElement row : rows) {
            // Get ticket name from the row - adjust the XPath index if needed
           // WebElement nameElement = row.findElement(NameOfTicketLocator);
            String currentTicketName = row.getText().trim();
            System.out.println("the currentTicketName="+currentTicketName);
            if (currentTicketName.contains(NameOfTicket)) {

                ticketFound = true;
                break; // Exit loop after finding the ticket
            }
        }
        Assert.assertTrue(ticketFound);
    }
    public AuditLogPage ClickOnAuditLogTicket(){
        browserActions.click(BuildButtonLocator);

        return new AuditLogPage(browserActions.getDriver());
    }
}
