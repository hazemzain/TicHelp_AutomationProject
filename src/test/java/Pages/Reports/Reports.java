package Pages.Reports;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Reports {
    BrowserActions browserActions;
    Assertion assertion;
    public Reports(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    //Locators
    By AdminstrationSection = By.xpath("//*[@id=\"content\"]/div[5]/form/table[3]");
    By DeletedTickets = By.xpath("//a[text()='Deleted tickets']");
    //Loctators
    private By getDeletedTicketLocator(String ticketName) {
        return By.xpath("//tr[td/a[@class='ticketLink']//text()='" + ticketName + "']");
    }
    //"//tr[td/a[@class='ticketLink']//text()='Saraaa']"

    //Actions [Adminstration section]
    public void NavigateToDeletedTickets(){
        browserActions.scrollTillElement(AdminstrationSection);
        browserActions.click(DeletedTickets);}

    //to know whether the deleted ticket deleted successfully

    public void ValidateDeletedTicketIsDisplyed(String TicketName){
        assertion.assertElementIsDisplayed(getDeletedTicketLocator(TicketName));
    }

}
