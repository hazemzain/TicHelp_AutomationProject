package Pages.AdminstrationPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
    private  final  By CustomThankMessageLocator=By.xpath("//*[@id=\"ThankYouText\"]");
    private final By HideLogCheckerLocator =By.xpath("//*[@id='HideSystemComments']");
    private final By AvatarButtonLocator=By.xpath("//*[@id=\"DisableAvatars\"]");
    private final By LanguageSelectorLocator=By.xpath("//*[@id='Language']");
    private final By TIMEZONE_DROPDOWN_LOCATOR=By.xpath("//*[@id=\"aspnetForm\"]/div/table[3]/tbody[2]/tr[2]/td[2]/select");
    private final By DisplayedTimeLocator=By.xpath("//*[@id=\"aspnetForm\"]/div/table[3]/tbody[2]/tr[2]/td[2]/p/b[1]");
    private final By DisableKnowledgeBaseLocator=By.xpath("//*[@id='DisableKB']");
    private final By KnowledgeTabLocator=By.xpath("//*[@id=\"divBigHeader\"]/ul[1]/li[3]/a");
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
    public GeneralSettingPage ChangeCustomThanksMessage(String Message){
        browserActions.type(CustomThankMessageLocator,Message);
        return  new GeneralSettingPage(browserActions.getDriver());
    }


    public GeneralSettingPage checkCheckboxHideLogAndPerformAction(boolean StatusWanted) {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(HideLogCheckerLocator);

        // Check if the checkbox is checked using the isSelected() method
        if (checkbox.isSelected()) {
            System.out.println("Hide Log is checked");
            if (!StatusWanted){
                checkbox.click();
            }

        } else {
            System.out.println("Hide Log is not checked");
            if (StatusWanted){
                checkbox.click();
            }


        }
        return new GeneralSettingPage(browserActions.getDriver());
    }


    public GeneralSettingPage checkCheckboxAvatarAndPerformAction(boolean StatusWanted) {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(AvatarButtonLocator);

        // Check if the checkbox is checked using the isSelected() method
        if (checkbox.isSelected()) {
            System.out.println("Avatar is checked");
            if (!StatusWanted){
                checkbox.click();
            }

        } else {
            System.out.println("Avatar is not checked");
            if (StatusWanted){
                checkbox.click();
            }


        }
        return new GeneralSettingPage(browserActions.getDriver());
    }

    public GeneralSettingPage selectLanguage(String language) {
        WebElement languageDropdown = browserActions.getDriver().findElement(LanguageSelectorLocator);
        Select dropdown = new Select(languageDropdown);
        switch (language){
            case "Arabic":
                dropdown.selectByValue("ar-SA"); // or use selectByValue() if needed
                break;
            case "English":
                dropdown.selectByValue("en-US");
                break;
            default:
                dropdown.selectByValue("en-US");
        }
        return new GeneralSettingPage(browserActions.getDriver());
    }
    public GeneralSettingPage selectTimeZone( String timezone) {
        WebElement dropdown = browserActions.findElement(TIMEZONE_DROPDOWN_LOCATOR);
        Select select = new Select(dropdown);
        select.selectByValue(timezone);
        return new GeneralSettingPage(browserActions.getDriver());

    }
public boolean CheckTimeNow(){
    String SELECTED_TIMEZONE = "Africa/Cairo";
    ZoneId zoneId = ZoneId.of(SELECTED_TIMEZONE);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String displayedTimeNow= browserActions.getText(DisplayedTimeLocator).trim();
    String displayedTime = displayedTimeNow.split(":")[0] + ":" + displayedTimeNow.split(":")[1];
    String expectedTime = ZonedDateTime.now(zoneId).format(formatter);
    System.out.println("The Actual Time Displayed is "+displayedTime);
    System.out.println("The Expected Time Displayed is "+expectedTime);

    return displayedTime.equals(expectedTime);

}
    public String GetTimeNow(){
        String SELECTED_TIMEZONE = "Africa/Cairo";
        ZoneId zoneId = ZoneId.of(SELECTED_TIMEZONE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");
        String expectedTime = ZonedDateTime.now(zoneId).format(formatter);
        System.out.println("The Expected Time Displayed is "+expectedTime);

        return expectedTime;

    }
    public GeneralSettingPage checkCheckboxKnowledgeBaseAndPerformAction(boolean StatusWanted) {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(DisableKnowledgeBaseLocator);

        // Check if the checkbox is checked using the isSelected() method
        if (checkbox.isSelected()) {
            System.out.println("KnowledgeBase is checked");
            if (!StatusWanted){
                checkbox.click();
            }

        } else {
            System.out.println("KnowledgeBase is not checked");
            if (StatusWanted){
                checkbox.click();
            }


        }
        return new GeneralSettingPage(browserActions.getDriver());
    }

    public String CheckIfKnowledgeBaseIsHideOrNot()
    {
             return browserActions.getText(KnowledgeTabLocator);

//        try {
//            // Check if log element is present and visible
//            return browserActions.findElement(KnowledgeTabLocator).isDisplayed();
//        } catch (NoSuchElementException e) {
//            // Element not found in DOM (fully hidden)
//            return false;
//        }
    }




}
