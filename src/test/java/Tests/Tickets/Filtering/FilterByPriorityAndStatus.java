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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/*filter by priority and status DDT */

public class FilterByPriorityAndStatus extends TestBase {

    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    TicketsFilterForm ticketsFilter;
    CreateTicketForm createTicketForm;
    String jsonFilePath = "C:\\Users\\Administrator\\Desktop\\Tickhelp\\Automation_selenuimFrameWork\\src\\resources\\testData\\filterData.json";

    @BeforeClass
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        ticketsFilter = new TicketsFilterForm(driver);
        createTicketForm = new CreateTicketForm(driver);
        navigateToUrl();
        login.ValidLogin();

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
        String Ticketname = "new day New mental suffer2";
        String details = "details ticket with DDT";
        navBar.ClickNewTicket();
        createTicketForm.EnterSubject(Ticketname);
/*
        createTicketForm.ClickCatrgory();
        createTicketForm.SelectCategory();
*/
        createTicketForm.EnterDetails(details);
        //createTicketForm.EnterAddress("Nasr city");
        createTicketForm.ClickSubmitButton();

        // Search for the ticket by priority and  status

        navBar.NavigateToTickets();
        ticketsFilter.OpenFilter();
        ticketsFilter.SelectStatus(status);
        ticketsFilter.SelectPriority(priority);
        ticketsFilter.ClickSubmit();

        ticketsFilter.IsFilterResultsShown(Ticketname);
        ticketsFilter.ResetFilter();




    }
}
