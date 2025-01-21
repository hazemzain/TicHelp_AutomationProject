package Tests.Adminstration;

import Assertions.Assertion;
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

public class TC01_Adminstration_VerifyDefaultTicketCategory extends TestBase {
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

    }
    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test(groups = "AssetTestCaseFails")
    public void Adminstration_TheTicketShouldBeTest_WhenDefaultCategoryInEmailSettingIsTest(){
        navigateToUrl();
        login.ValidLogin();
       String DefaultCategoryInEmailSetting= new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickEmailSettingButton()
                .GetDefaultEmailCategory();
       System.out.println("the Default Category in email setting ="+DefaultCategoryInEmailSetting);
       String DefaultCategoryInNewTiket=new TicketsPage(driver)
               .ClickTicketButton()
               .ClickNewTicketButton()
               .GetCategory();
        System.out.println("the Default Category in New Ticket is  ="+DefaultCategoryInNewTiket);
        Assert.assertTrue(DefaultCategoryInNewTiket.contains(DefaultCategoryInEmailSetting),"The defualt Category in email Setting not equal the Defualt Category in NewTicket");


    }
}
