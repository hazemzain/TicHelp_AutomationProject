package Pages.AdminstrationPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Pages.AdminstrationPages.AutomationRulesPages.AutomationRulePage;
import Pages.LoginPage.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class AdminstrationPage {
    //*[@id="divBigHeader"]/ul/li/a[@href='/Admin']
    //*[@id="divBigHeader"]/ul[1]/li[6]/a
    private final  By AdminstrationButton= By.xpath("//*[@id=\"divBigHeader\"]/ul/li/a[@href='/Admin']");
    private  final By EmailSettingButton=By.xpath("//*[@id=\"emailSettings\"]/div/a");
    private final By DefualtEmailSettingCategory=By.id("MailCheckerNewIssuesCategoryID");
   private  final By MailCheckerLocator =By.xpath("//*[@id='MailCheckerAllowUnregisteredUsers']");
  private final By DisposeEmailCheckerLocator=By.xpath("//*[@id=\"AllowFreeEmails\"]");
   private final By SaveButtonLocator=By.xpath("//*[@id=\"aspnetForm\"]/div[1]/p[1]/input");
   private final By AdminButtonLocator=By.id("btnUser");
   private final By LogoutButtonLocator=By.xpath("//*[@id=\"divRecent\"]/a");
    private final By GeneralSettingLocator=By.xpath("//*[@id=\"generalSettings\"]/div/a");
   private  final By ExtractOriginalEmailCheckerLocator=By.xpath("//*[@id=\"ExtractOriginalSenderFromFwd\"]");
   private final By UserButtonLocator=By.xpath("//*[@id=\"content\"]/div[5]/table[1]/tbody/tr[2]/td/div/a");
   private final By CheckLanguageLocator=By.xpath("//*[@id=\"divBigHeader\"]/ul[1]/li[6]/a");
   private final By AutomationRuleButtonLocator=By.xpath("//*[@id=\"content\"]/div[5]/table[3]/tbody/tr[1]/td[1]/div/a");
   BrowserActions browserActions;
    Assertion assertion;
    public AdminstrationPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public AdminstrationPage ClickAdminstrationButton(){
        browserActions.click(AdminstrationButton);
        return new AdminstrationPage(browserActions.getDriver());
    }
    public AdminstrationPage ClickEmailSettingButton(){
        browserActions.click(EmailSettingButton);
        return new AdminstrationPage(browserActions.getDriver());
    }
    public AdminstrationPage ClickAdminButton(){
        browserActions.click(AdminButtonLocator);
        return new AdminstrationPage(browserActions.getDriver());
    }
    public Login ClickLogoutButton(){
        browserActions.click(LogoutButtonLocator);
        return new Login(browserActions.getDriver());
    }
    public  String GetDefaultEmailCategory(){
        WebElement dropdownElement = browserActions.getDriver().findElement(DefualtEmailSettingCategory);

        // Create a Select object
        Select dropdown = new Select(dropdownElement);

        // Get the default selected option
        String defaultValue = dropdown.getFirstSelectedOption().getText();
        return defaultValue;
        //return  browserActions.getText(DefualtEmailSettingCategory);
    }
    public AdminstrationPage checkCheckboxAndPerformAction() {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(MailCheckerLocator);

        // Check if the checkbox is checked using the isSelected() method
        if (checkbox.isSelected()) {
            System.out.println("Checkbox is checked");

        } else {
            System.out.println("Checkbox is not checked");

            checkbox.click();
            //click save button
            browserActions.click(SaveButtonLocator);

        }
        return new AdminstrationPage(browserActions.getDriver());
    }

    public AdminstrationPage checkCheckboxDisposableEmailAddressesAndPerformAction(boolean StatusWanted) {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(DisposeEmailCheckerLocator);

        // Check if the checkbox is checked using the isSelected() method
        if (checkbox.isSelected()) {
            System.out.println("Checkbox is checked");
            if (!StatusWanted){
                checkbox.click();
            }

        } else {
            System.out.println("Checkbox is not checked");
            if (StatusWanted){
                checkbox.click();
            }
            //click save button
            browserActions.click(SaveButtonLocator);

        }
        return new AdminstrationPage(browserActions.getDriver());
    }

    public AdminstrationPage checkCheckboxExtractOriginalSenderFromForwardedEmailsAndPerformAction(boolean StatusWanted) {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(ExtractOriginalEmailCheckerLocator);

        // Check if the checkbox is checked using the isSelected() method
        if (checkbox.isSelected()) {
            System.out.println("Checkbox is checked");
            if (!StatusWanted){
                checkbox.click();
            }

        } else {
            System.out.println("Checkbox is not checked");
            if (StatusWanted){
                checkbox.click();
            }
            //click save button
            browserActions.click(SaveButtonLocator);

        }
        return new AdminstrationPage(browserActions.getDriver());
    }

    public  GeneralSettingPage ClikInGeneralSetting(){
        browserActions.click(GeneralSettingLocator);
        return  new GeneralSettingPage(browserActions.getDriver());
    }

    public  UsersPage ClickOnUsers(){
        browserActions.click(UserButtonLocator);

        return new UsersPage(browserActions.getDriver());
    }

    public String CheckLanguage(){
        return browserActions.getText(CheckLanguageLocator);
    }

    public AutomationRulePage ClickInAutomationRules(){
        browserActions.click(AutomationRuleButtonLocator);
        return  new AutomationRulePage(browserActions.getDriver());
    }

}
