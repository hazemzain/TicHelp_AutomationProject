package Tests.Adminstration.EmailSetting;

import Config.Config;
import HelperClasses.EmailSettingHelperClass;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TC07_EmailSetting_VerifyTheOutgoingEmailSettingWhenChangeFromValueFromDefualtToOtherEmail extends TestBase {
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
    public void EmailSetting_ErrorMessageWillAppear_WhenChangeTheEmailFormInOutGoingEmailSetting () throws InterruptedException {
        navigateToUrl();
        EmailSettingHelperClass Helper=new EmailSettingHelperClass(driver);
        String TempEmail=Helper.getTempEmail();
        login.ValidLogin();
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickEmailSettingButton()
                .ModifyTheFromEmail("hazemzain17@gmail.com")
                .ClickInTestSmtp()
                .acceptAlert(TempEmail)//put the email iwe will get from temp email ;
                .ValidateThatTheEmailErrorMessageIsDisplayed();


    }

}
