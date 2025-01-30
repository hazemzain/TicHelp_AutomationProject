package Pages.AdminstrationPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Pages.AdminstrationPages.AutomationRulesPages.AutomationRulePage;
import Pages.AssetsPages.AssetsPage;
import Pages.LoginPage.Login;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


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
   private final By FromEmailLocator=By.xpath("//*[@id=\"FromAddress\"]");
   private final By TestSmtpLocator=By.xpath("//*[@id=\"aspnetForm\"]/div[1]/table[2]/tbody[6]/tr/td/input[1]");
   private final By MessageEmailSendLocator=By.xpath("//*[@id=\"aspnetForm\"]/div[1]/div");
   private final By MessageErrorFromLocator=By.xpath("//*[@id=\"FromAddress-error\"]");
   private final By NameFromLocator=By.xpath("//*[@id=\"FromName\"]");
   private final By ConfirmationEmailButtonLocator=By.xpath("//*[@id=\"EnableTicketConfirmationNotification\"]");
   private final By DeleteTicketNotificationLocator=By.xpath("//*[@id=\"EnableCloseNotification\"]");
    private final By SubjectInTempleteLocator=By.xpath("//*[@id=\"EmailTemplates_NewIssueConfirmationSubj\"]");
    private final By SubjectForCloseTicketTemplete=By.xpath("//*[@id=\"EmailTemplates_ClosedTicketEmailSubject\"]");
    private final By ResetButtonLocator=By.xpath("//*[@id=\"aspnetForm\"]/div[1]/table[4]/tbody[2]/tr[8]/td/input");

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
    public AdminstrationPage ModifyTheFromEmail(String Email){
        browserActions.type(FromEmailLocator,Email);
        return new AdminstrationPage(browserActions.getDriver());
    }
    public AdminstrationPage ModifyTheFromName(String Name){
        browserActions.clear(NameFromLocator);
        browserActions.type(NameFromLocator,Name);
        return new AdminstrationPage(browserActions.getDriver());
    }
    public AdminstrationPage ClickInTestSmtp(){
        browserActions.click(TestSmtpLocator);
        return new AdminstrationPage(browserActions.getDriver());
    }
    public AdminstrationPage ValidateTheEmptyFromField(){
        String ActualResult=browserActions.getText(MessageErrorFromLocator);
        Assert.assertEquals(ActualResult,"This field is required.");
        return new AdminstrationPage(browserActions.getDriver());
    }
    public AdminstrationPage acceptAlert(String WantedEmail) {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(WantedEmail);
        alert.accept();
        return new AdminstrationPage(browserActions.getDriver());
    }
    public AdminstrationPage AcceptAlert() {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return new AdminstrationPage(browserActions.getDriver());
    }
    public AdminstrationPage ValidateThatTheEmailSendMessageIsDisplayed(){
        WebDriver driver = browserActions.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(MessageEmailSendLocator));
            Assert.assertTrue(messageElement.isDisplayed());
            if (messageElement.isDisplayed()) {
                System.out.println("Email send message is displayed.");
            } else {
                System.out.println("Email send message is not displayed.");
            }
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for the email send message to be displayed.");
        }

        return new AdminstrationPage(browserActions.getDriver());


    }

    public AdminstrationPage ValidateThatTheEmailErrorMessageIsDisplayed(){
        WebDriver driver = browserActions.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(MessageEmailSendLocator));
            Assert.assertTrue(messageElement.isDisplayed());
            String Message=browserActions.getText(MessageEmailSendLocator);
            Assert.assertTrue(Message.contains("ERROR sending email:"));
            if (messageElement.isDisplayed()) {
                System.out.println("Email send message is displayed.");
            } else {
                System.out.println("Email send message is not displayed.");
            }
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for the email send message to be displayed.");
        }

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

    public AdminstrationPage checkCheckboxConfirmationEmailAndPerformAction(boolean StatusWanted) {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(ConfirmationEmailButtonLocator);

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
    public AdminstrationPage checkCheckboxDeleteTicketNotificationEmailAndPerformAction(boolean StatusWanted) {
        // Locate the checkbox element
        WebElement checkbox = browserActions.getDriver().findElement(DeleteTicketNotificationLocator);

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
    public AdminstrationPage ChangeTheValueForSubjectEmailForNewTicketTemplete(String NewSubject){
        browserActions.type(SubjectInTempleteLocator,NewSubject);
        return new AdminstrationPage(browserActions.getDriver());

    }
    public AdminstrationPage ChangeTheValueForSubjectEmailForCloseTicketEmailTemplete(String NewSubject){
        browserActions.type(SubjectForCloseTicketTemplete,NewSubject);
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
    public AdminstrationPage ClickSaveButton(){
        browserActions.click(SaveButtonLocator);

        return new AdminstrationPage(browserActions.getDriver());


    }
    public AdminstrationPage ClickResetTemplateButton(){
        browserActions.click(ResetButtonLocator);

        return new AdminstrationPage(browserActions.getDriver());


    }

    public AdminstrationPage ValidateInvalidEmailFormatError() {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));

        WebElement errorElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(MessageErrorFromLocator));
        Assert.assertTrue(errorElement.isDisplayed());
        String ErrorMessage= browserActions.getText(MessageErrorFromLocator);
        Assert.assertTrue(ErrorMessage.contains("Please enter a valid email address."));
        return new AdminstrationPage(browserActions.getDriver());
    }


}
