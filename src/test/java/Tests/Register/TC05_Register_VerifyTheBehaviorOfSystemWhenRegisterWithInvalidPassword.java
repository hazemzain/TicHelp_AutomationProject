package Tests.Register;

import Config.Config;
import HelperClasses.EmailSettingHelperClass;
import Pages.LoginPage.Login;
import Pages.LoginPage.RegisterPage;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC05_Register_VerifyTheBehaviorOfSystemWhenRegisterWithInvalidPassword extends TestBase {
    String formattedDateTime;
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    EmailSettingHelperClass Helper=new EmailSettingHelperClass(driver);
    String TempEmail;


    @BeforeMethod
    public void setupTest() throws InterruptedException {
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
    @Test(dataProvider = "InvalidPassword")
    public void Register_ErrorMessageShouldAppear_WhenRegisterWithInPassword(String Password, String expectedError) throws InterruptedException {
        navigateToUrl();
//        EmailSettingHelperClass Helper=new EmailSettingHelperClass(driver);
//        TempEmail=Helper.getTempEmail();

        new Login(driver).ClickInRegister()
                .EnterUserName("TestUser"+formattedDateTime)
                .EnterEmail("hazemzain17@gmail.com")
                .EnterPassword(Password)
                .EnterConfirmationPassword(Password)
                .EnterFirstName("GBG")
                .EnterLastName("Company")
                .EnterPhoneNumber("01283253154")
                .selectLanguage(RegisterPage.LanguageOption.ENGLISH)
                .ClickInRegisterButton();

        // Get the actual error message displayed for the email field
        String actualErrorMessage =new RegisterPage(driver).GetMessageErrorForPassword();
        // Assert that the actual error matches the expected error
        Assert.assertEquals(actualErrorMessage, expectedError);
    }
    @DataProvider(name = "InvalidPassword")
    public Object[][] getTestData() {
        return new Object[][]{
                {"Mmm@123", "Please enter at least 8 characters."},
                {"mmmmmmmm", "Password must contain a digit"},
                {"MMMMMMMM", "Password must contain a digit"},
                {"Mmmmmmmm", "Password must contain a digit"},
                {"Mmm@1", "Please enter at least 8 characters."},
                {"Mmm@١٢٣٤", "Password must contain a digit"},



        };
    }
    @AfterMethod
    public void AfterTest() throws InterruptedException {
       //driver.quit();


    }

}
