package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TicketsCheckboxesActions {
    BrowserActions browserActions;
    Assertion assertion;

    public TicketsCheckboxesActions(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    //Loctators
    private By getTicketCheckboxLocator(String ticketName) {
        return By.xpath("//tr[td/a[text() = '" + ticketName + "']]//td[@class='cbcell']//input[@type='checkbox']");
    }
    By DeleteAction = By.xpath("//a[@title=\"Delete selected tickets\"]");
    By CloseAction = By.xpath("//a[@title=\"Close selected tickets\"]");
    By ReplyBeforeClose = By.id("comment");
    By ConfirmClose = By.xpath("//input[@onclick=\"OnClose()\"]");
    By TakeOverAction = By.xpath("//a[@onclick=\"TakeoverSelected()\"]");

    By TagAction = By.xpath("//a[@title=\"Tag ticket(s)\"]");
    By TagNameField = By.id("Tags_tag");
    By AddTagButton = By.xpath("//input[@value=\"Add\"]");

    By PrintAction = By.xpath("//a[@title=\"Print selected tickets\"]");
    private By TicketHeaderInprint(String TicketName) { return By.xpath("//h1[text()='" + TicketName  +"']");}
    By PrintButton = By.xpath("//button[@onclick=\"self.print()\"]");

    By ChangeDueDateAction = By.xpath("//a[@title=\"Change due date\"]");

    By DateField = By.id("dueDate");



    //Actions
    public void checkTicket(String ticketName) {
        browserActions.click(getTicketCheckboxLocator(ticketName));
    }
    public void deleteSelectedTicket(){
        browserActions.click(DeleteAction);
        browserActions.acceptAlert();
    }
    public void AddReplyBeforeClose(String Reply){
        browserActions.type(ReplyBeforeClose, Reply );
    }
    public void CloseSelectedTicketWithReply(String reply){
        browserActions.click(CloseAction);
        AddReplyBeforeClose(reply);
        browserActions.click(ConfirmClose);
        browserActions.acceptAlert();
    }
    public void TakeOverSelectedTicket(){
        browserActions.click(TakeOverAction);
        browserActions.acceptAlert();

    }
    public void AddTagForSelectedTicket(String tagName){
        browserActions.click(TagAction);
        browserActions.type(TagNameField , tagName);
        browserActions.click(AddTagButton);
    }
    public void PrintSelectedTicket(String TicketName){
        browserActions.click(PrintAction);
        assertion.assertElementIsDisplayed(TicketHeaderInprint(TicketName));
         assertion.assertElementIsDisplayed(PrintButton);
    }
    public void ChangDueDate(){
        browserActions.click(ChangeDueDateAction);
        browserActions.type(DateField, "2025-01-20T15:30");
       // browserActions.ty
    }


}
