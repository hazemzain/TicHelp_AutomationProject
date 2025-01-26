package Tests.Adminstration.AutomationRules;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.AdminstrationPages.AutomationRulesPages.CreateRulePage;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.TicketsPage.ThanksTicketPage;
import Pages.TicketsPage.TicketsPage;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC13_AutomationRules_VerifyThatTestButtonWorkCorrectlyWithInValidTicketID extends TestBase {

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
    public void AutomationRules_TheErrorMessageShouldBeAppear_WhenEnterInvalidIdTicket () {
        navigateToUrl();
        login.ValidLogin();

        String NameOfRule="HazemNewRule"+formattedDateTime;
        String NewTicketName="Test"+formattedDateTime;




        String Result=new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickInAutomationRules()
                .ClickOnCreateRuleButton()
                .EnterNewRuleName(NameOfRule)
                .selectWhenThisHappen(CreateRulePage.WhenThisRuleHappen.TICKET_CREATED)
                .ClickOnAddButtonToDoThis()
                .selectToDoType(CreateRulePage.ToDoAction.ASSIGN_TO)
                .AssignToType(CreateRulePage.AssigneeToType.ADMIN)
                .ClickOnSaveButton()
                .SearchForRuleByName(NameOfRule)
                .clickSpecificRule(NameOfRule)
                .WriteTicketId("9")
                .ClickInTestButton()
                .GetTextAppear();
        Assert.assertEquals(Result,"No tickets found");

    }
}
