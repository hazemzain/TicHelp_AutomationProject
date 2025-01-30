package Tests.Adminstration.EmailSetting;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC08_EmailSetting_VerifyInvalidEmailValidationInFormField extends TestBase {
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

    @DataProvider(name = "invalidEmails")
    public Object[][] provideInvalidEmails() {
        return new Object[][] {
                {"invalidemail"},          // Missing @ and domain
                {"missing@domain"},       // Missing top-level domain
                {"@domain.com"},          // Missing local part
                {"test@domain"},          // Incomplete domain
                {"test@.com"},            // Empty subdomain
                {"test@domain..com"},     // Double dots in domain
                {"test@domain_com"},      // Underscore in domain
                {"test@domain.c"},        // Short TLD
                {"test@123.45.67"},       // Numeric domain issues
                {"\"@example.com"}        // Special characters
        };
    }

    @Test(dataProvider = "invalidEmails")
    public void EmailSetting_TheErrorMessageShouldBeAppear_WhenEnterInvalidEmailInTheEmailFromInOutGoingEmailSetting (String invalidEmail) throws InterruptedException {
        navigateToUrl();
        login.ValidLogin();

        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickEmailSettingButton()
                .ModifyTheFromEmail(invalidEmail)
                .ClickSaveButton()
                .ValidateInvalidEmailFormatError();
    }

    @AfterMethod
    public void cleanUp() {
        // Clear cookies and local storage
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");

        // Force reload the browser
        driver.navigate().to("about:blank");
    }

}
