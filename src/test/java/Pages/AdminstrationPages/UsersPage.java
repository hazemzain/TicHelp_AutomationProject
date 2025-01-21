package Pages.AdminstrationPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsersPage {

    BrowserActions browserActions;
    Assertion assertion;
    private  final By FristUserLocator= By.xpath("//*[@id=\"gridUsers\"]/tbody/tr[2]/td[1]/h3/a");
    public UsersPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public  UserDetailsPage GoToFirstUserDetails(){
        browserActions.click(FristUserLocator);
        return  new UserDetailsPage(browserActions.getDriver());
    }
}
