package Pages.AdminstrationPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class UserDetailsPage {
    BrowserActions browserActions;
    Assertion assertion;
    private final By AvatarLocator=By.xpath("//*[@id=\"content\"]/div[5]/div/table[1]/tbody[1]/tr/th/h3/img");
    public UserDetailsPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    public Boolean CheckIfAvatarIsHideOrNot()
    {
        try {
            // Check if log element is present and visible
            return browserActions.findElement(AvatarLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            // Element not found in DOM (fully hidden)
            return false;
        }
    }
}
