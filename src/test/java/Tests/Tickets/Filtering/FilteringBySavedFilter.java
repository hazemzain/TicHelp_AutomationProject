package Tests.Tickets.Filtering;

import Config.Config;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.TicketsPage.CreateTicketForm;
import Pages.TicketsPage.TicketsFilterForm;
import Tests.TestBase;
import Utilities.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class FilteringBySavedFilter extends TestBase {


    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    TicketsFilterForm ticketsFilter;
    CreateTicketForm createTicketForm;
    String jsonFilePath = "C:\\Users\\Administrator\\Desktop\\Tickhelp\\Automation_selenuimFrameWork\\src\\resources\\testData\\filterData.json";

    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        ticketsFilter = new TicketsFilterForm(driver);
        createTicketForm = new CreateTicketForm(driver);
    }

    @DataProvider(name = "filterData")
    public Object[][] getFilterData() throws IOException, ParseException {
        JSONArray filterData = JSONReader.getFilterData(jsonFilePath);
        Object[][] data = new Object[filterData.size()][2];

        for (int i = 0; i < filterData.size(); i++) {
            JSONObject filter = (JSONObject) filterData.get(i);
            data[i][0] = filter.get("status");
            data[i][1] = filter.get("priority");

        }
        return data;
    }

    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test(dataProvider = "filterData")
    public void Filter_Ticket_priority_status(String status , String priority) {
        String Ticketname = "New  ticket to search by saved filter";
        String details = "details ticket with DDT";
        String filter_name = "Automated filter" ;

        navigateToUrl();
        login.ValidLogin();

        // Create a ticket
        navBar.ClickNewTicket();
        createTicketForm.EnterSubject(Ticketname);
/*
        createTicketForm.ClickCatrgory();
        createTicketForm.SelectCategory();
*/
        createTicketForm.EnterDetails(details);
        //createTicketForm.EnterAddress("Nasr city");
        createTicketForm.ClickSubmitButton();

        // Search for the ticket by priority
        navBar.NavigateToTickets();
        ticketsFilter.OpenFilter();
        ticketsFilter.SelectStatus(status);
        ticketsFilter.SelectPriority(priority);
        ticketsFilter.ClickSubmit();
        ticketsFilter.ClickSave();
        ticketsFilter.EnterFilterName(filter_name);
        ticketsFilter.SaveFilter();
        ticketsFilter.ClickTheSavedFilter(filter_name);

        //click the saved filter created

        ticketsFilter.IsFilterResultsShown(Ticketname);
        // ticketsFilter.DeleteCreatedTicket();



        // Assertion: Verify that the tickets filtered by priority are displayed

    }
}
