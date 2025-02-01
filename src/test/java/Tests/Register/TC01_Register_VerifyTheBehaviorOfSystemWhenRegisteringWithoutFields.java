package Tests.Register;

import Config.Config;
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

public class TC01_Register_VerifyTheBehaviorOfSystemWhenRegisteringWithoutFields extends TestBase {
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
    @Test(dataProvider = "emptyFieldData")
    public void Register_ErrorMessageShouldAppear_WhenFieldIsEmpty(
            String username, String email, String password, String confirmPassword, String firstName,
            String lastName, String phoneNumber, String fieldName) throws InterruptedException {
        navigateToUrl();
        new Login(driver).ClickInRegister()
                .EnterUserName(username)
                .EnterEmail(email)
                .EnterPassword(password)
                .EnterConfirmationPassword(confirmPassword)
                .EnterFirstName(firstName)
                .EnterLastName(lastName)
                .EnterPhoneNumber(phoneNumber)
                .selectLanguage(RegisterPage.LanguageOption.ENGLISH)
                .ClickInRegisterButton();

        // Assert that the user is not redirected (remains on the Register page)
        Assert.assertEquals(driver.getCurrentUrl(), "http://20.117.78.174:8060/User/Register",
                "Test failed for field: " + fieldName);
    }


    @DataProvider(name = "emptyFieldData")
    public Object[][] getTestData() {
        return new Object[][]{
                {"", "gbgzain@gmail.com", "Mmm@123456789", "Mmm@123456789", "GBG", "Company", "01283253154", "Username"},  // Missing Username
                {"TestUser", "", "Mmm@123456789", "Mmm@123456789", "GBG", "Company", "01283253154", "Email"},  // Missing Email
                {"TestUser", "gbgzain@gmail.com", "", "Mmm@123456789", "GBG", "Company", "01283253154", "Password"},  // Missing Password
                {"TestUser", "gbgzain@gmail.com", "Mmm@123456789", "", "GBG", "Company", "01283253154", "Confirm Password"},  // Missing Confirm Password
                {"TestUser", "gbgzain@gmail.com", "Mmm@123456789", "Mmm@123456789", "", "Company", "01283253154", "First Name"},  // Missing First Name
                {"TestUser", "gbgzain@gmail.com", "Mmm@123456789", "Mmm@123456789", "GBG", "", "01283253154", "Last Name"},  // Missing Last Name
                {"TestUser", "gbgzain@gmail.com", "Mmm@123456789", "Mmm@123456789", "GBG", "Company", "", "Phone Number"}  // Missing Phone Number
        };
    }
}
