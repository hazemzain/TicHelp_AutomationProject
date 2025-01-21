package Pages.AdminstrationPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeneralSettingPage {
    BrowserActions browserActions;
    Assertion assertion;
    public GeneralSettingPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    private final  By UploadLogoLocator= By.xpath("//*[@id=\"aspnetForm\"]/div/table[1]/tbody[2]/tr[1]/td[2]/label");
    private final By UploadFaviconLocator=By.xpath("//*[@id=\"aspnetForm\"]/div/table[1]/tbody[2]/tr[1]/td[4]/label");
    private final By SaveChangesLocator=By.xpath("//*[@id=\"aspnetForm\"]/div/p[1]/input");
    private final By HeaderTextColor=By.xpath("//*[@id=\"HeaderTextColor\"]");
    private final By HeaderColorLocator=By.xpath("//*[@id=\"HeaderBGColor\"]");
    private final By FlipButtonLocator=By.xpath("//*[@id=\"IsFlipMenuHorizontal\"]");
    private  final By MenuBarColorLocator=By.xpath("//*[@id=\"MenuBarBGColor\"]");
    private  final  By ResetToDefault=By.xpath("//*[@id=\"aspnetForm\"]/div/table[1]/tbody[2]/tr[8]/td[2]/input");
    public GeneralSettingPage UploadLogo(String FilePath){
        browserActions.click(UploadLogoLocator);
        WebElement fileInput = browserActions.getDriver().findElement(By.xpath("//*[@id=\"fileLogo\"]"));

        // Send the file path to the input field
        fileInput.sendKeys(FilePath);


        return new GeneralSettingPage(browserActions.getDriver());
    }

    public GeneralSettingPage UploadFavicon(String FilePath){
        browserActions.click(UploadFaviconLocator);
        WebElement fileInput = browserActions.getDriver().findElement(By.xpath("//*[@id=\"fileFavicon\"]"));

        // Send the file path to the input field
        fileInput.sendKeys(FilePath);


        return new GeneralSettingPage(browserActions.getDriver());
    }
    public GeneralSettingPage ChangeColor(String WantedColor){
        browserActions.type(HeaderColorLocator,WantedColor);
        browserActions.click(HeaderColorLocator);
        return new GeneralSettingPage(browserActions.getDriver());
    }
    public GeneralSettingPage ClickSaveChanges(){
        browserActions.click(SaveChangesLocator);
        return new GeneralSettingPage(browserActions.getDriver());


    }
    public String GetHeaderColor(){
        WebElement header = browserActions.findElement(HeaderColorLocator);
        String Color = header.getAttribute("value");
        return Color;

    }

    public String GetMenuBarColorColor(){
        WebElement header = browserActions.findElement(MenuBarColorLocator);
        String Color = header.getAttribute("value");
        return Color;

    }
    public GeneralSettingPage ChangeMenuBarColor(String WantedColor){
        browserActions.type(MenuBarColorLocator,WantedColor);
        browserActions.click(MenuBarColorLocator);
        return new GeneralSettingPage(browserActions.getDriver());
    }

    public GeneralSettingPage ChangeHeaderTextColor(String WantedColor){
        browserActions.type(HeaderTextColor,WantedColor);
        browserActions.click(HeaderTextColor);
        return new GeneralSettingPage(browserActions.getDriver());
    }
    public String GetHeaderTextColor(){
        WebElement header = browserActions.findElement(HeaderTextColor);
        String Color = header.getAttribute("value");
        return Color;

    }

    public  GeneralSettingPage ClickInResetToDefualtButton(){
        browserActions.click(ResetToDefault);
        // Wait for the alert to be present and switch to it
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        // Switch to the alert and handle it
        Alert alert = browserActions.getDriver().switchTo().alert();

        // Get the alert text (optional, but useful for debugging)
        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);

        // Handle the alert - Accept the alert (if you want to confirm)
        alert.accept();
        return  new GeneralSettingPage(browserActions.getDriver());
    }
    public GeneralSettingPage checkCheckboxFlipHorizontalToVerticalAndPerformAction(boolean StatusWanted) {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(FlipButtonLocator);

        // Check if the checkbox is checked using the isSelected() method
        if (checkbox.isSelected()) {
            System.out.println("Flip is checked");
            if (!StatusWanted){
                checkbox.click();
            }

        } else {
            System.out.println("Flip is not checked");
            if (StatusWanted){
                checkbox.click();
            }


        }
        return new GeneralSettingPage(browserActions.getDriver());
    }

    public String GetValueOfLayout()
    {
        WebElement tabMenu = browserActions.findElement(By.xpath("/html/body"));
        String LayoutDisplay = tabMenu.getAttribute("id");
        return LayoutDisplay;

    }





}
