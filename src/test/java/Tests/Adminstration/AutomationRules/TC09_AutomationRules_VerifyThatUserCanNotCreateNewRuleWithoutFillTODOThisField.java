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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TC09_AutomationRules_VerifyThatUserCanNotCreateNewRuleWithoutFillTODOThisField extends TestBase {
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
    public void AutomationRules_ValidationMessagesToDoThisWillAppear_WhenSavingToDoThisWithEmptyFields () {
        navigateToUrl();
        login.ValidLogin();
        String NameOfRule="HazemNewRule"+formattedDateTime;
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickInAutomationRules()
                .ClickOnCreateRuleButton()
                .EnterNewRuleName(NameOfRule)
                .ClickOnSaveButton()
                ;
        List<String>ActualMessagesError=new CreateRulePage(driver)
                .GetMessageValidationError();
        List<String> expectedErrors = Arrays.asList(
                "Please add at least one action."

        );

        Assert.assertEquals(ActualMessagesError, expectedErrors, "Validation messages mismatch");

    }
}
