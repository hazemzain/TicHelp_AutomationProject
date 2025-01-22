package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
    private final By LogLocator=By.xpath("//*[@id=\"tdComment2013\"]/div[1]");
    private final By TimeForLastTicket=By.xpath("//*[@id=\"lblTicketDate\"]");
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

    public Boolean CheckIfLogIsHideOrNot()
    {
     try {
            // Check if log element is present and visible
            return browserActions.findElement(LogLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            // Element not found in DOM (fully hidden)
            return false;
        }
    }
    public String GetTimeForLastTicket(){
        String result = browserActions.getText(TimeForLastTicket).trim(); // Trim whitespace
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US); // Add Locale

        LocalDateTime dateTime = LocalDateTime.parse(result, inputFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm ", Locale.US);

        String timeOnly = dateTime.format(timeFormatter);


        System.out.println(timeOnly);
        return timeOnly;
    }


}
