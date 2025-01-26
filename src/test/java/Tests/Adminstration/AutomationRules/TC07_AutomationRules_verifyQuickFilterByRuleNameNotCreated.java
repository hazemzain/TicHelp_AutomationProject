package Tests.Adminstration.AutomationRules;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.AdminstrationPages.AutomationRulesPages.CreateRulePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC07_AutomationRules_verifyQuickFilterByRuleNameNotCreated extends TestBase {
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
    public void AutomationRules_NoRulesShouldAppear_WhenSearchingByRuleNameNotCreated () throws IOException {
        navigateToUrl();
        login.ValidLogin();
        String NameOfRule="HazemNewRule"+formattedDateTime;
        boolean Result=new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickInAutomationRules()
                .SearchForRuleByName(NameOfRule)
                .isRulePresent(NameOfRule);
        Assert.assertFalse(Result);
        Assert.assertEquals(Result,false);
    }
}
