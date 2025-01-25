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

public class TC04_AutomationRules_VerifyUserCanDisableEnableRule extends TestBase {
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
    public void AutomationRules_TheRuleShouldDisable_WhenSwitchOffDisableButton () throws IOException {
        navigateToUrl();
        login.ValidLogin();
        String NameOfRule="HazemNewRule"+formattedDateTime;
        int Result=new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickInAutomationRules()
                .ClickOnCreateRuleButton()
                .EnterNewRuleName(NameOfRule)
                .selectWhenThisHappen(CreateRulePage.WhenThisRuleHappen.TICKET_CREATED)
                .ClickOnAddButtonToDoThis()
                .selectToDoType(CreateRulePage.ToDoAction.ASSIGN_TO)
                .AssignToType(CreateRulePage.AssigneeToType.ADMIN)
                .ClickOnSaveButton()
                .cloneRuleByName(NameOfRule)
                .DisableAllRuleWhichIsEnabled()
                .GetNumberOfEnableRule();
        Assert.assertTrue(Result==0);
        Assert.assertEquals(Result,0);
    }
}
