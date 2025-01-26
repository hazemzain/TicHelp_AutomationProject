package Pages.HomePages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Pages.ReportPages.ReportPage;
import Pages.TicketsPage.CreateTicketForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final By NewTicketLocator= By.xpath("//*[@id=\"newTicket\"]/a");
    private final By EditReportPermissionLocator=By.xpath("//*[@id=\"divBigHeader\"]/ul[1]/li[5]/a");
    BrowserActions browserActions;
    Assertion assertion;
    public HomePage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public CreateTicketForm ClickInNewTicketButton(){
        browserActions.click(NewTicketLocator);
        return new CreateTicketForm(browserActions.getDriver());
    }

    public  ReportPage ClickOnReportButton(){
        browserActions.click(EditReportPermissionLocator);

        return new ReportPage(browserActions.getDriver());
    }

}
