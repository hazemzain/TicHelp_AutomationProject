package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TicketDetailsPage {
    BrowserActions browserActions;
    Assertion assertion;

    public TicketDetailsPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    //Loctators

    By TicketStatus =  By.id("ticketStatus");
    private By TicketDetailsPage (String TicketName){
        return By.xpath("//tr[td/a[text()= '" + TicketName + "']]");
    }
    By CommentBody = By.xpath("//div[contains(@class,'commentBody')]/span");
    By AssignedTo = By.id("lnkAssignedTo");

     By tagLocator=  By.xpath("//*[@id=\"tbTags_tagsinput\"]/span/a[1]");
    private final By MoreOptionLocator=By.xpath("//*[@id=\"toolsbtn\"]");
    private final By ForwardEmailOptionLocator=By.xpath("//*[@id=\"toolbar\"]/div/div/ul/li[4]/a");
    private final By OriginalEmailLocator=By.xpath("//*[@id=\"lnkFrom\"]");


    //Actions
    public void NavigateToTicketDetailsPage(String TicketName){
        browserActions.click(TicketDetailsPage(TicketName));

    }

    public void CheckIfTicketClosed(){
        assertion.assertElementTextContains(TicketStatus, "Closed");
    }
    public void verifyTicketAssignee(String Tech){assertion.assertElementTextContains(AssignedTo , Tech);}

    public void ValidateTheReply(String expectedReply) {
        assertion.assertElementTextEquals(CommentBody, expectedReply);
    }

    public void verifyTagDisplayed() {
       // browserActions.scrollTillElement(By.xpath("//*[@id=\"content\"]/div[5]/div[2]/table[1]/tbody[1]/tr[14]/td[1]"));
        assertion.assertElementIsDisplayed(tagLocator);
    }

    public  TicketDetailsPage ClickInMoreOptionButton(){
        browserActions.click(MoreOptionLocator);
        return  new TicketDetailsPage(browserActions.getDriver());
    }

    public  ForwardTicketPage ClickForwardEmailOptionButton(){
        browserActions.click(ForwardEmailOptionLocator);
        return  new ForwardTicketPage(browserActions.getDriver());
    }

    public String GetTheOriginalEmailForTicket(){
        return browserActions.getText(OriginalEmailLocator);
    }


}
