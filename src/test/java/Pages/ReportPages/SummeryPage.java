package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.WebDriver;

public class SummeryPage {
    BrowserActions browserActions;
    Assertion assertion;
    public SummeryPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

}
