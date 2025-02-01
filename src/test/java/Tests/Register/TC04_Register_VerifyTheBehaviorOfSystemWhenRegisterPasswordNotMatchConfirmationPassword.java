package Tests.Register;

import Config.Config;
import HelperClasses.EmailSettingHelperClass;
import Pages.LoginPage.Login;
import Pages.LoginPage.RegisterPage;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC04_Register_VerifyTheBehaviorOfSystemWhenRegisterPasswordNotMatchConfirmationPassword extends TestBase {
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
    public void Register_ErrorMessageShouldAppear_WhenRegisterWithExistEmail() throws InterruptedException {
        navigateToUrl();
    EmailSettingHelperClass Helper=new EmailSettingHelperClass(driver);
    TempEmail=Helper.getTempEmail();

        new Login(driver).ClickInRegister()
                .EnterUserName("TestUser"+formattedDateTime)
                .EnterEmail(TempEmail)
                .EnterPassword("Mmm@123456789")
                .EnterConfirmationPassword("Mmm@1234567")
                .EnterFirstName("GBG")
                .EnterLastName("Company")
                .EnterPhoneNumber("01283253154")
                .selectLanguage(RegisterPage.LanguageOption.ENGLISH)
                .ClickInRegisterButton();

        // Get the actual error message displayed for the email field
        String actualErrorMessage =new RegisterPage(driver).GetMessageErrorForConfirmationPassword();
        // Assert that the actual error matches the expected error
        Assert.assertEquals(actualErrorMessage, "Please enter the same value again.");
    }


}
