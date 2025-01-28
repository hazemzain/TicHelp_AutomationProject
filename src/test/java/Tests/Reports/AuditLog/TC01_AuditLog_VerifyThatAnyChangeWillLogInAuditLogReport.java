package Tests.Reports.AuditLog;

import Config.Config;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.TicketsPage.TicketsPage;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC01_AuditLog_VerifyThatAnyChangeWillLogInAuditLogReport extends TestBase {
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
    public void AuditLog_TheCreateTicketActionShouldLogInAuditLogReport_WhenCreateNewTicket () throws IOException {
        navigateToUrl();
        login.ValidLogin();
        String TicketName="Test"+formattedDateTime;
        new HomePage(driver)
                .ClickInNewTicketButton()
                .ChoosePriority("High")
                .EnterNewSubject(TicketName)
                .EnterNewDetails("This is for Test Automation HelpDisk")
                .EnterNewAddress("Egypt")
                .ClickNewSubmitButton();
        new TicketsPage(driver)
                .ClickTicketButton()
                .DeleteTicketByName(TicketName)
                .acceptAlert();

         new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnAuditLogTicket()
                 .ClickOnAuditLogTicket()
                .verifyTheLastActionLogInAuditReport(TicketName)
                ;

    }
}
