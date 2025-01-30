package Tests.Adminstration.EmailSetting;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.Reports.Reports;
import Pages.TicketsPage.*;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC02_Adminstration_ValidateEmailAcceptanceFromUnregisteredUsers extends TestBase {
    String formattedDateTime;
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    TicketsFilterForm ticketsFilter;
    CreateTicketForm createTicketForm;
    TicketsCheckboxesActions ticketsCheckboxesActions;
    Reports reports;
    TicketDetailsPage ticketDetailsPage;

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
    public void Adminstration_TheTicketShouldBeAccepted_WhenSendEmailFromUnregisteredUserAccount() throws InterruptedException {
        navigateToUrl();
        login.ValidLogin();
         new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickEmailSettingButton()
                .checkCheckboxAndPerformAction()
                 .ClickAdminButton()
                 .ClickLogoutButton();

         String ActualMessage=new Login(driver)
                 .ClickSubmitNewTicketButton()
                 .EnterMail("hazemzain17@gmail.com")
                 .EnterNewSubject("Test"+formattedDateTime)
                 .EnterNewDetails("This is for Test Automation HelpDisk")
                 .EnterNewAddress("Egypt")
                 .ClickNewSubmitButton()
                 .GetThanksMessage();

         Assert.assertTrue(ActualMessage.contains("Your ticket has been submitted successfully"));

        new ThanksTicketPage(driver)
                .ClickInSignInButton()
                .ValidLogin();
        String ActualLastTicket=new TicketsPage(driver)
                .ClickTicketButton()
                .GetLastTicketSubject();
        Assert.assertEquals(ActualLastTicket,"Test"+formattedDateTime);
        //Assert.assertTrue(DefaultCategoryInNewTiket.contains(DefaultCategoryInEmailSetting),"The defualt Category in email Setting not equal the Defualt Category in NewTicket");


    }
}
