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

public class TC07_GeneralSetting_ValidateResettingDesignSettingsToDefaults extends TestBase {
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
    public void GeneralSetting_AllDesignTabReturnToDefault_WhenClickInResetToDefault() {
        navigateToUrl();
        login.ValidLogin();
        String InitHeaderTextColor= new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .ChangeHeaderTextColor("3246c7")
                .GetHeaderTextColor();

        String InitHeaderBackGround=new GeneralSettingPage(driver)
                .ChangeColor("3246c7")
                .GetHeaderColor();
//        String InitMenuBarColor=new GeneralSettingPage(driver)
//                .ChangeMenuBarColor("3246c7")
//                .GetMenuBarColorColor();

        //change color
        String HeaderTextColorAfterChange= new GeneralSettingPage(driver)
                .ClickInResetToDefualtButton()
                .ClickSaveChanges()
                .GetHeaderTextColor();
        String NewHeaderBackGround=new GeneralSettingPage(driver)
                .GetHeaderColor();
        String NewMenuBarColor=new GeneralSettingPage(driver)
                .GetMenuBarColorColor();

        Assert.assertNotEquals( HeaderTextColorAfterChange, InitHeaderTextColor);
        Assert.assertNotEquals( NewHeaderBackGround, InitHeaderBackGround);
        //Assert.assertNotEquals( NewMenuBarColor, InitMenuBarColor);


    }

}
