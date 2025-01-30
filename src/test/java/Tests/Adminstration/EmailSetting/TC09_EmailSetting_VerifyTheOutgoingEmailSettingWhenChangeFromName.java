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

public class TC09_EmailSetting_VerifyTheOutgoingEmailSettingWhenChangeFromName extends TestBase {
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
    public void EmailSetting_TheEmailSendShouldHaveTheNameLikeInNameFrom_WhenChangeTheEmailNameFromInOutGoingEmailSetting () throws InterruptedException {
        navigateToUrl();
        EmailSettingHelperClass Helper=new EmailSettingHelperClass(driver);
        String TempEmail=Helper.getTempEmail();
        login.ValidLogin();
        String FromName="HazemZain"+formattedDateTime;
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickEmailSettingButton()
                .ModifyTheFromEmail("developer.masrawy@gmail.com")
                .ModifyTheFromName(FromName)
                .ClickInTestSmtp()
                .acceptAlert(TempEmail)//put the email iwe will get from temp email ;
                .ValidateThatTheEmailSendMessageIsDisplayed();
        Map<String, String> receivedEmail = Helper.getReceivedEmail();
        System.out.println(receivedEmail.get("Name"));
        Assert.assertEquals(receivedEmail.get("Name"),FromName);

    }

}
