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

public class TC12_AutomationRules_VerifyThatTestButtonWorksWithValidTicketID extends TestBase {

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
    public void AutomationRules_TheRuleShouldBeCreated_WhenCreateNewRule () {
        navigateToUrl();
        login.ValidLogin();

        String NameOfRule="HazemNewRule"+formattedDateTime;
        String NewTicketName="Test"+formattedDateTime;
        String ActualMessage=new HomePage(driver)
                .ClickInNewTicketButton()
                .EnterMail("hazemzain17@gmail.com")
                .ChoosePriority("High")
                .EnterNewSubject("Test"+formattedDateTime)
                .EnterNewDetails("This is for Test Automation HelpDisk")
                .EnterNewAddress("Egypt")
                .ClickNewSubmitButton()
                .GetThanksMessage();
        new ThanksTicketPage(driver)
                .ClickInSignInButton()
                .ValidLogin();

        String ActualNumberOfTicket=new TicketsPage(driver)
                .ClickTicketButton()
                .ClickInLastTicket()
                .GetTheNumberOfTicket();

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
                .WriteTicketId(ActualNumberOfTicket)
                .ClickInTestButton()
                .AssignToWho();
        Assert.assertEquals(Result,"admin");

    }
}
