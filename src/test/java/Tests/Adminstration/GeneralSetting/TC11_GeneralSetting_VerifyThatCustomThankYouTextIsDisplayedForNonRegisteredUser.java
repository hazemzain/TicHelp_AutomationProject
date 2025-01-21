package Tests.Adminstration.GeneralSetting;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC11_GeneralSetting_VerifyThatCustomThankYouTextIsDisplayedForNonRegisteredUser extends TestBase {
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
    public void GeneralSetting_TheTicketShouldBeAcceptedAndCustomThanksMessageAppear_WhenSendEmailFromUnregisteredUserAccount() {
        navigateToUrl();
        login.ValidLogin();
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .ChangeCustomThanksMessage("Thank you for all thing")
                .ClickSaveChanges();
        new AdminstrationPage(driver)
                .ClickAdminButton()
                .ClickLogoutButton();

        String ActualMessage=new Login(driver)
                .ClickSubmitNewTicketButton()
                .EnterMail("hazemzain17@gmail.com")
                .EnterNewSubject("Test"+formattedDateTime)
                .EnterNewDetails("This is for Test Automation HelpDisk")
                .EnterNewAddress("Egypt")
                .ClickNewSubmitButton()
                .GetThanksMessage();
        Assert.assertEquals(ActualMessage,"Thank you for all thing");

    }
}
