package Tests.Register;

import Config.Config;
import Pages.LoginPage.Login;
import Pages.LoginPage.RegisterPage;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Listeners({AllureTestNg.class})

public class TC03_Register_VerifyTheBehaviorOfSystemWhenRegisterWithExistEmail extends TestBase {
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

    @Test(dataProvider = "ExistEmail")
    public void Register_ErrorMessageShouldAppear_WhenRegisterWithExistEmail(String email, String expectedError) throws InterruptedException {
        navigateToUrl();

        new Login(driver).ClickInRegister()
                .EnterUserName("TestUser")
                .EnterEmail(email)
                .EnterPassword("Mmm@123456789")
                .EnterConfirmationPassword("Mmm@123456789")
                .EnterFirstName("GBG")
                .EnterLastName("Company")
                .EnterPhoneNumber("01283253154")
                .selectLanguage(RegisterPage.LanguageOption.ENGLISH)
                .ClickInRegisterButton();

        // Get the actual error message displayed for the email field
        String actualErrorMessage =new RegisterPage(driver).GetMessageError();
        // Assert that the actual error matches the expected error
        Assert.assertEquals(actualErrorMessage, expectedError, "Email validation failed for: " + email);
    }

    @DataProvider(name = "ExistEmail")
    public Object[][] getTestData() {
        return new Object[][]{
               {"gbgzain@gmail.com", "This email address is already taken, Lost password?"},  // Missing domain

        };
    }
}
