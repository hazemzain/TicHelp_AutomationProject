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

/*create a ticket --> find it through a filter(user and period) --> delete it */

public class ticketsFilteringByUserAndPeriod extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    TicketsFilterForm ticketsFilter;
    CreateTicketForm createTicketForm;
    String jsonFilePath = "C:\\Users\\Administrator\\Desktop\\Tickhelp\\Automation_selenuimFrameWork\\src\\resources\\testData\\Period.json";


    @BeforeClass
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        ticketsFilter = new TicketsFilterForm(driver);
        createTicketForm = new CreateTicketForm(driver);
        navigateToUrl();
        login.ValidLogin();


    }
    @DataProvider(name = "PeriodDateAndUserData")
    public Object[][] getdueData() throws IOException, ParseException {
        JSONArray filterData = JSONReader.getdueData(jsonFilePath);
        Object[][] data = new Object[filterData.size()][2];

        for (int i = 0; i < filterData.size(); i++) {
            JSONObject filter = (JSONObject) filterData.get(i);
            data[i][0] = filter.get("date");
            data[i][1] = filter.get("user");

        }
        return data;
    }

    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test(dataProvider = "PeriodDateAndUserData")
    public void Filter_Ticket_User_period(String date, String user) {
        String subject = "yarabbb==New ticket by selenuim";
        String details = "Detaillls aa detaillls";
        //create a ticket as an admin

        navBar.NavigateToTickets();
        navBar.ClickNewTicket();
        createTicketForm.EnterSubject(subject);
        createTicketForm.ClickCatrgory();
        createTicketForm.SelectCategory();

        createTicketForm.EnterDetails(details);
        //createTicketForm.EnterAddress("Nasrcity");

        createTicketForm.ClickSubmitButton();
        //search for it by admin
        navBar.NavigateToTickets();
        ticketsFilter.OpenFilter();
        /**/
        ticketsFilter.SelectPeriod(date);
        ticketsFilter.EnterUser(user);
        /**/
        ticketsFilter.ClickSubmit();
        //login.logout();
        //is it created?
        ticketsFilter.IsFilterResultsShown(subject);
        ticketsFilter.ResetFilter();

    }
}