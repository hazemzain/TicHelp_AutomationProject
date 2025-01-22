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

public class TC21_GeneralSetting_ValidateKnowledgeBaseToggleWhenEnable extends TestBase {
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
    public void GeneralSetting_KnowledgeBaseTabShouldBeHidden_WhenKnowledgeBaseCheckerIsEnable() {
        navigateToUrl();
        login.ValidLogin();
        String result=new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .checkCheckboxKnowledgeBaseAndPerformAction(true)
                .ClickSaveChanges()
                .CheckIfKnowledgeBaseIsHideOrNot()
                ;
        Assert.assertNotEquals(result,"Knowledge base");
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .checkCheckboxKnowledgeBaseAndPerformAction(false)
                .ClickSaveChanges();
    }
}
