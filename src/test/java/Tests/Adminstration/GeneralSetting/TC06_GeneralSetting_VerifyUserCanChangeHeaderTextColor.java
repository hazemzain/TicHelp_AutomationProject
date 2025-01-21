package Tests.Adminstration.GeneralSetting;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.AdminstrationPages.GeneralSettingPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC06_GeneralSetting_VerifyUserCanChangeHeaderTextColor extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    WebDriverWait wait;

    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test
    public void GeneralSetting_TheHeaderTextColorShouldBeChanged_WhenChangeTheHeaderTextColor() {
        navigateToUrl();
        login.ValidLogin();
       String InitHeaderTextColor= new AdminstrationPage(driver)
               .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .GetHeaderTextColor();

       //change color
        String HeaderTextColorAfterChange= new GeneralSettingPage(driver)
                .ChangeHeaderTextColor("edea1a")
                .ClickSaveChanges()
                .GetHeaderTextColor();
        Assert.assertNotEquals( HeaderTextColorAfterChange, InitHeaderTextColor);
    }
}
