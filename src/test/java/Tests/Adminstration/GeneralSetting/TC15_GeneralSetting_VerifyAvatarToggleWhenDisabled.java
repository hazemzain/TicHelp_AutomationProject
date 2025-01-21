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

public class TC15_GeneralSetting_VerifyAvatarToggleWhenDisabled extends TestBase {
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
    public void GeneralSetting_AvatarsShouldBeAppear_WhenToggleIsDisabled() {
        navigateToUrl();
        login.ValidLogin();
        // Enable "Disable avatars" toggle and save changes
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .checkCheckboxAvatarAndPerformAction(false) // Enable toggle
                .ClickSaveChanges();
       Boolean AvatarStatus= new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickOnUsers()
                .GoToFirstUserDetails()
                .CheckIfAvatarIsHideOrNot();
        Assert.assertTrue(AvatarStatus);
    }

    }
