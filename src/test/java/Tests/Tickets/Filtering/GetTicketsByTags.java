package Tests.Tickets.Filtering;

import Config.Config;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.TicketsPage.CreateTicketForm;
import Pages.TicketsPage.TicketsFilterForm;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetTicketsByTags extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    TicketsFilterForm ticketsFilter;
    CreateTicketForm createTicketForm;


    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        ticketsFilter = new TicketsFilterForm(driver);
        createTicketForm = new CreateTicketForm(driver);

    }

    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test
    public void Get_Tagged_Tickets() {
        //create Tagged ticket
        navigateToUrl();
        login.ValidLogin();
        navBar.ClickNewTicket();
        createTicketForm.EnterSubject("Tagged ticket auto");
        createTicketForm.ClickCatrgory();
        createTicketForm.SelectCategory();
        createTicketForm.EnterDetails("Detaillls aa detaillls");
       // createTicketForm.EnterAddress("Nasrcity");
        createTicketForm.ClickAdvancedButton();
        createTicketForm.EnterTag();
        createTicketForm.ClickSubmitButton();

        navBar.NavigateToTickets();
        ticketsFilter.ClickTag();
        ticketsFilter.IsTicketsTableDisplayed();



    }
}
