package Tests.Tickets.CheckboxesAction;

import Config.Config;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.Reports.Reports;
import Pages.TicketsPage.CreateTicketForm;
import Pages.TicketsPage.TicketsCheckboxesActions;
import Pages.TicketsPage.TicketsFilterForm;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TicketDeleteAction extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    TicketsFilterForm ticketsFilter;
    CreateTicketForm createTicketForm;
    TicketsCheckboxesActions ticketsCheckboxesActions;
    Reports reports;

    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        ticketsFilter = new TicketsFilterForm(driver);
        createTicketForm = new CreateTicketForm(driver);
        ticketsCheckboxesActions = new TicketsCheckboxesActions(driver);
        reports = new Reports(driver);

    }

    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test
    public void CheckTicket_delete()  {
        String TicketName = "Meow meww";
        String details = "to be deleted";

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

        //Check a ticket --> choose delete --> accept alert --> at reports --> deleted tickets --> check existance
        navBar.NavigateToTickets();
        ticketsCheckboxesActions.checkTicket(TicketName);
        ticketsCheckboxesActions.deleteSelectedTicket();

        navBar.NavigateToReprts();
        reports.NavigateToDeletedTickets();
        reports.ValidateDeletedTicketIsDisplyed(TicketName);


    }
}
