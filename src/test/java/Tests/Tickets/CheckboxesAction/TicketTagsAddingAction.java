package Tests.Tickets.CheckboxesAction;

import Config.Config;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.Reports.Reports;
import Pages.TicketsPage.CreateTicketForm;
import Pages.TicketsPage.TicketDetailsPage;
import Pages.TicketsPage.TicketsCheckboxesActions;
import Pages.TicketsPage.TicketsFilterForm;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TicketTagsAddingAction extends TestBase {
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
        ticketsFilter = new TicketsFilterForm(driver);
        createTicketForm = new CreateTicketForm(driver);
        ticketsCheckboxesActions = new TicketsCheckboxesActions(driver);
        reports = new Reports(driver);
        ticketDetailsPage = new TicketDetailsPage(driver);
    }
    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test
    public void CheckTicket_TagAdding(){
        String TicketName = "Ticket with tag";
        String details = "mew by an admin";
        String TagName = "IAMaTag";

        //create ticket
        navigateToUrl();
        login.ValidLogin();
        navBar.ClickNewTicket();
        createTicketForm.EnterSubject(TicketName);
        // createTicketForm.ClickCatrgory();
        //createTicketForm.SelectCategory();
        createTicketForm.EnterDetails(details);
        // createTicketForm.EnterAddress("Nasrcity");
        createTicketForm.ClickSubmitButton();

        //take over as Tech and Check tech assigned to
        navBar.NavigateToTickets();
        ticketsCheckboxesActions.checkTicket(TicketName);
        ticketsCheckboxesActions.AddTagForSelectedTicket(TagName);

        ticketDetailsPage.NavigateToTicketDetailsPage(TicketName);
        ticketDetailsPage.verifyTagDisplayed();

        //delete it
        navBar.NavigateToTickets();
        ticketsCheckboxesActions.checkTicket(TicketName);
        ticketsCheckboxesActions.deleteSelectedTicket();




    }
}
