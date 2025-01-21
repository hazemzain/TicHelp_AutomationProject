package Tests.Tickets.Creation;

import Config.Config;
import Pages.LoginPage.Login;
import Pages.TicketsPage.CreateTicketForm;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubmitTicketUnregisteredUsers extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    CreateTicketForm createTicketForm;


    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        createTicketForm = new CreateTicketForm(driver);
    }

    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }
    @Test
    public void Submit_Ticket_Guest() {
        navigateToUrl();
        login.ClickSubmitTicketAsGuest();
        createTicketForm.EnterGuestMail("khloodAutomationGuest@gmail.com");
        createTicketForm.EnterSubject("guest ticket automation");

        createTicketForm.ClickCatrgory();
        createTicketForm.SelectCategory();

        createTicketForm.EnterDetails("Detaillls for a guest");
       // createTicketForm.EnterAddress("Nasrcity");

        createTicketForm.ClickSubmitButton();



    }

}
