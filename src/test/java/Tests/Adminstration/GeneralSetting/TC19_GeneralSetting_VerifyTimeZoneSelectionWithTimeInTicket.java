package Tests.Adminstration.GeneralSetting;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.TicketsPage.ThanksTicketPage;
import Pages.TicketsPage.TicketsPage;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC19_GeneralSetting_VerifyTimeZoneSelectionWithTimeInTicket extends TestBase {
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
    @Test(groups = "GeneralSettingTestCaseFails")
    public void GeneralSetting_TimeZoneShouldChangeToEgyptTimeInTicket_WhenTimeZoneIsSelectedToEgypt() {
        navigateToUrl();
        login.ValidLogin();
        String ExpectedResult=new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .selectTimeZone("Egypt Standard Time")
                .ClickSaveChanges()
                .GetTimeNow();
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickAdminButton()
                .ClickLogoutButton();
        String ActualMessage=new Login(driver)
                .ClickSubmitNewTicketButton()
                .EnterMail("testuser@mailinator.com")
                .ChoosePriority("High")
                .EnterNewSubject("Test"+formattedDateTime)
                .EnterNewDetails("This is for Test Automation HelpDisk")
                .EnterNewAddress("Egypt")
                .ClickNewSubmitButton()
                .GetThanksMessage();

        new ThanksTicketPage(driver)
                .ClickInSignInButton()
                .ValidLogin();

        String ActualLastTicketTime=new TicketsPage(driver)
                .ClickTicketButton()
                .ClickInLastTicket()
                .GetTimeForLastTicket();
        Assert.assertEquals(ActualLastTicketTime.trim(),ExpectedResult.trim());
    }
}
