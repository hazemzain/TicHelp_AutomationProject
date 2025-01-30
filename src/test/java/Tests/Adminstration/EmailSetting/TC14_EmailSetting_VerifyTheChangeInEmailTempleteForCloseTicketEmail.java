package Tests.Adminstration.EmailSetting;

import Config.Config;
import HelperClasses.EmailSettingHelperClass;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.TicketsPage.ThanksTicketPage;
import Pages.TicketsPage.TicketsPage;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TC14_EmailSetting_VerifyTheChangeInEmailTempleteForCloseTicketEmail extends TestBase {
    String formattedDateTime;
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;


    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the date and time as needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        formattedDateTime = currentDateTime.format(formatter);

    }
    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test
    public void EmailSetting_TheEmailTemplateShouldBeChanged_WhenChangeTheDefaultTemplateForCloseTicketEmail () throws InterruptedException {
        navigateToUrl();
        EmailSettingHelperClass Helper=new EmailSettingHelperClass(driver);
        String TempEmail=Helper.getTempEmailUsingGmail();
        login.ValidLogin();
        String FromName="HazemZain"+formattedDateTime;
        String Subject="Test"+formattedDateTime;
        String ChangedSubject="SubjectNew: #Subject#";
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickEmailSettingButton()
                .ModifyTheFromEmail("developer.masrawy@gmail.com")
                .ModifyTheFromName(FromName)
                .checkCheckboxConfirmationEmailAndPerformAction(true)
                .checkCheckboxDeleteTicketNotificationEmailAndPerformAction(true)
                .ChangeTheValueForSubjectEmailForCloseTicketEmailTemplete(ChangedSubject)
                .ClickSaveButton()
                .ClickAdminButton()
                .ClickLogoutButton();

        String ActualMessage=new Login(driver)
                .ClickSubmitNewTicketButton()
                .EnterMail(TempEmail)
                .EnterNewSubject(Subject)
                .EnterNewDetails("This is for Test Automation HelpDisk")
                .EnterNewAddress("Egypt")
                .ChoosePriority("High")
                .ClickNewSubmitButton()
                .GetThanksMessage();
        new ThanksTicketPage(driver)
                .ClickInSignInButton()
                .ValidLogin();
        new TicketsPage(driver)
                .ClickTicketButton()
                .ClickInLastTicket()
                .ClickInDeleteTicketButton();
        Thread.sleep(Duration.ofSeconds(20));
        Map<String, String> receivedEmail2 = Helper.getFirstEmailDetailsUsingGmail();

        //Assert.assertTrue(receivedEmail2.get("Name").contains("admin"));

        System.out.println(receivedEmail2.get("Sender"));
        System.out.println(receivedEmail2.get("Subject"));
        Assert.assertTrue(receivedEmail2.get("Subject").startsWith("SubjectNew:"));



    }
}
