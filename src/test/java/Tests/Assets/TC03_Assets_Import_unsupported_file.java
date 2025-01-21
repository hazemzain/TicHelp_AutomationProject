package Tests.Assets;

import Pages.AssetsPages.AssetsPage;
import Config.Config;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.Reports.Reports;
import Pages.TicketsPage.CreateTicketForm;
import Pages.TicketsPage.TicketDetailsPage;
import Pages.TicketsPage.TicketsCheckboxesActions;
import Pages.TicketsPage.TicketsFilterForm;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC03_Assets_Import_unsupported_file extends TestBase {
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

    @Test
    public void Assets_ShouldDisplayErrorMessage_WhenImportFileWithUnsupportedFormat(){
        navigateToUrl();
        login.ValidLogin();

        new AssetsPage(driver).
                ClickAssertButton()
                .ClickImportButton()
                .ChooseFile("D:\\GBG_Projects\\TicHelp-ddt-tickets\\assets_data.docx")
                .ClickUploadButton();
        String errorMessage = new AssetsPage(driver).GetErrorMessage();  // Example method you might need to implement
        Assert.assertTrue(errorMessage.contains("Some of the rows haven't been imported due to invalid format"), "Error message should indicate an unsupported file type.");




        //Assert.assertEquals(NumberAfterUpdateFile-NumberBeforeUpdateFile, 9, "The number of imported assets does not match the expected value.");




    }

}
