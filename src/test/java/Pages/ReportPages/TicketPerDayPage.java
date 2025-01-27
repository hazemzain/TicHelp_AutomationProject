package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class TicketPerDayPage {
    private final By BuildButtonLocator= By.xpath("//*[@id=\"btnBuild\"]");
    private final By DiagramReportLocator=By.xpath("//*[@id=\"chartDiv\"]/div[1]/canvas");
    BrowserActions browserActions;
    Assertion assertion;
    public TicketPerDayPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public  TicketPerDayPage ClickOnBuildButton(){
        browserActions.click(BuildButtonLocator);

        return new TicketPerDayPage(browserActions.getDriver());
    }
    public void ValidateTheVisibailtyOfImagesReports(){
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement diagramReport = wait.until(ExpectedConditions.visibilityOfElementLocated(DiagramReportLocator));
        Assert.assertTrue(diagramReport.isDisplayed(), "Diagram report is not visible.");

    }
}
