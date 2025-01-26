package Pages.AdminstrationPages.AutomationRulesPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.WebDriver;

public class ErrorPage {
    BrowserActions browserActions;
    Assertion assertion;
    public ErrorPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    }
