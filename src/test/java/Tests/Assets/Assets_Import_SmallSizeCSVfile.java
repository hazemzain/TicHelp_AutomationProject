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

public class Assets_Import_SmallSizeCSVfile extends TestBase {
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
    public void Assets_ShouldUploadSmallSizeCsvFile_WhenImportSmallSizeCSVfile(){
        navigateToUrl();
        login.ValidLogin();
        int NumberBeforeUpdateFile=new AssetsPage(driver)
                .ClickAssertButton()
                .GetNumberOfAssetsInGrid();
        System.out.println("number of rows in grid before update ="+NumberBeforeUpdateFile);
        int NumberAfterUpdateFile=new AssetsPage(driver).
                ClickAssertButton()
                .ClickImportButton()
                .ChooseFile("D:\\GBG_Projects\\TicHelp-ddt-tickets\\assetssmall.csv")
                .ClickUploadButton()
                .GetNumberOfAssetsInGrid();

        System.out.println("number of rows in grid after update ="+NumberAfterUpdateFile);


        Assert.assertEquals(NumberAfterUpdateFile-NumberBeforeUpdateFile, 9, "The number of imported assets does not match the expected value.");




    }
}
