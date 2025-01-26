package Pages.AdminstrationPages.AutomationRulesPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Pages.TicketsPage.TicketDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RuleDetailsPage {
    private final By TicketIdLocator= By.xpath("//*[@id=\"rule-form\"]/div[2]/p[7]/input[1]");
    private final By TestButtonLocator=By.xpath("//*[@id=\"rule-form\"]/div[2]/p[7]/input[2]");
    BrowserActions browserActions;
    Assertion assertion;
    public RuleDetailsPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    public RuleDetailsPage WriteTicketId(String TicketId){
        browserActions.type(TicketIdLocator,TicketId);
        return new RuleDetailsPage(browserActions.getDriver());
    }
    public TicketDetailsPage ClickInTestButton(){
        browserActions.click(TestButtonLocator);
        return new TicketDetailsPage(browserActions.getDriver());
    }
    public String GetTextAppear(){
        return browserActions.getText(By.xpath("/html/body/div/h3"));

    }

}
