package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Pages.LoginPage.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ThanksTicketPage {
    BrowserActions browserActions;
    Assertion assertion;
    private final By ThanksMessageLocator=By.xpath("//*[@id='content']/div[3]/div/p[1]");
    private  final  By SignInButtonLocator=By.xpath("//div/div[@id='userinfo']/button");
    public ThanksTicketPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    public String GetThanksMessage(){
        return browserActions.getText(ThanksMessageLocator);

    }
    public Login ClickInSignInButton(){
        browserActions.click(SignInButtonLocator);
        return  new Login(browserActions.getDriver());
    }
}
