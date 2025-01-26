package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReportPage {
    BrowserActions browserActions;
    Assertion assertion;
    private final By EditReportPermissionButton= By.xpath("//*[@id=\"btnEdit\"]");
    private final By DrobDownsLocator=By.xpath("//*[@class='permissions']");
    private final By SummeryButtonLocator=By.xpath("//*[@id=\"content\"]/div[5]/form/table[1]/tbody/tr[1]/td[1]/div/a");
    private final By ResetToDefaultLocator=By.xpath("//*[@id=\"content\"]/div[5]/form/div/input[2]");
    private  WebDriver driver;
    private  WebDriverWait wait;

    // Locator for all permission dropdowns
    private final By dropdownsLocator = By.cssSelector("select.permissions");

    // Common option locator
    private final By dropdownOptions = By.cssSelector("select.permissions option");
    public ReportPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
public ReportPage ClickINEditButton(){
        browserActions.click(EditReportPermissionButton);
        return new ReportPage(browserActions.getDriver());
}
public int GetNumberOfDrobDownAppearAfterClickEditButton(){
        return browserActions.getDriver().findElements(DrobDownsLocator).size();
}
public SummeryPage ClickInSummeryButton(){
        browserActions.click(SummeryButtonLocator);

        return new SummeryPage(browserActions.getDriver());
}
    public Boolean CheckTheEditButtonIsVisable(){
        try {
            return browserActions.findElement(EditReportPermissionButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    // Get all permission dropdown elements
    public List<WebElement> getAllPermissionDropdowns() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownsLocator));
    }
    // Select by visible text for a specific dropdown
    public void selectByVisibleText(WebElement dropdown, String text) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    // Select by value for a specific dropdown
    public void selectByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // Get selected value for a specific dropdown
    public String getSelectedValue(WebElement dropdown) {
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }
    // Verify dropdown options for a specific dropdown
    public boolean verifyDropdownOptions(WebElement dropdown, List<String> expectedOptions) {
        Select select = new Select(dropdown);
        List<String> actualOptions = select.getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return actualOptions.equals(expectedOptions);
    }


    public void verifyAndSelectPermissions() {
        List<WebElement> dropdowns = getAllPermissionDropdowns();
        List<String> expectedOptions = Arrays.asList("Admins", "Technicians", "Technicians and managers");

        for(WebElement dropdown : dropdowns) {
            // Verify options
            Assert.assertTrue(verifyDropdownOptions(dropdown, expectedOptions),
                    "Dropdown options mismatch for: " + dropdown.getAttribute("id"));

            // Select different values
            selectByValue(dropdown, "1"); // Select Technicians
            Assert.assertEquals(getSelectedValue(dropdown), "Technicians");
        }
    }
    public ReportPage ClickInResetToDefault(){
        browserActions.click(ResetToDefaultLocator);
        return new ReportPage(browserActions.getDriver());
    }

    public void verifyResetButtonAfterChangeTheDropDowns(){
        List<WebElement> dropdowns = getAllPermissionDropdowns();
        List<String> expectedOptions = Arrays.asList("Admins", "Technicians", "Technicians and managers");

        for(WebElement dropdown : dropdowns) {

            // Select different values
            Assert.assertEquals(getSelectedValue(dropdown), "Admins");
        }

    }

}
