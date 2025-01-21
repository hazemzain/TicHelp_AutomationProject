package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ForwardTicketPage {
    BrowserActions browserActions;
    Assertion assertion;
    private  final By SubjectForwardLocator= By.xpath("//*[@id=\"Subject\"]");
    private final By ForwardMessageLocator=By.xpath("//*[@id=\"rteBody\"]");
    public ForwardTicketPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public  String GetSubjectINForwardEmail(){
        // Locate the input element using its ID
        WebElement subjectField = browserActions.getDriver().findElement(SubjectForwardLocator);

        // Retrieve the value of the 'value' attribute
        String subjectText = subjectField.getAttribute("value");
        return subjectText;
      //  return browserActions.getText(SubjectForwardLocator);
    }
    public String GetForwardMessage(){
        return browserActions.getText(ForwardMessageLocator);

    }
}
