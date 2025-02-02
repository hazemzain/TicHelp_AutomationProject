package Tests.Register;

import Config.Config;
import HelperClasses.EmailSettingHelperClass;
import Pages.LoginPage.Login;
import Pages.LoginPage.RegisterPage;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import Tests.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
@Listeners(TestListener.class)

public class TC06_Register_VerifyTheBehaviorOfSystemWhenRegisterWithValidData extends TestBase {
    String formattedDateTime;
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    EmailSettingHelperClass Helper=new EmailSettingHelperClass(driver);
    String TempEmail;


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
    public void Register_TheSystemShouldAcceptTheData_WhenRegisterWithValidData() throws InterruptedException {
        navigateToUrl();
    EmailSettingHelperClass Helper=new EmailSettingHelperClass(driver);
    TempEmail=Helper.getTempEmail();
        String UserName="TestUser"+formattedDateTime;
        new Login(driver).ClickInRegister()
                .EnterUserName(UserName)
                .EnterEmail(TempEmail)
                .EnterPassword("Mmm@123456789")
                .EnterConfirmationPassword("Mmm@123456789")
                .EnterFirstName("GBG")
                .EnterLastName("Company")
                .EnterPhoneNumber("01283253154")
                .selectLanguage(RegisterPage.LanguageOption.ENGLISH)
                .ClickInRegisterButton();

        String actualMessage =new RegisterPage(driver).GetMessageForSuccessRegister();
        Assert.assertEquals(actualMessage, "A verification email has been sent: "+TempEmail+ " please check your mailbox.");

    Map<String, String> receivedEmail = Helper.getReceivedEmail();
    System.out.println(receivedEmail.get("Name"));
    System.out.println(receivedEmail.get("Subject"));

    Assert.assertEquals(receivedEmail.get("Subject"),"Welcome to Helpdesk!");
    }


}
