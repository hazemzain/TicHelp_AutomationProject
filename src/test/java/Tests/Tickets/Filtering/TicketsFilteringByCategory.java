package Tests.Tickets.Filtering;

import Config.Config;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.TicketsPage.CreateTicketForm;
import Pages.TicketsPage.TicketsFilterForm;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TicketsFilteringByCategory extends TestBase {
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
    public void Filter_Ticket_Categoty() {
        navigateToUrl();

        //create a ticket as an admin Anderson category
        login.ValidLogin();
        navBar.ClickNewTicket();
        createTicketForm.EnterSubject("New ticket to search by category");

        createTicketForm.ClickCatrgory();
        createTicketForm.SelectCategory();

        createTicketForm.EnterDetails("Detaillls aa detaillls");
       // createTicketForm.EnterAddress("Nasrcity");

        createTicketForm.ClickSubmitButton();
        //search for it
        navBar.NavigateToTickets();
        ticketsFilter.FilterByCategory();
        ticketsFilter.IsTicketsTableDisplayed();



    }
}
