package Tests.Assets;

import Config.Config;
import Pages.AssetsPages.AssetsPage;
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

public class TC07_Assets_DeleteSomeAssets extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;


    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);

    }
    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }
    @Test
    public void Assets_SelectedAssetsShouldBeDeleted_WhenSelectSomeAssetsAndClickDelete(){
        navigateToUrl();
        login.ValidLogin();
        int NumberBeforeUpdate=new AssetsPage(driver)
                .ClickAssertButton()
                .GetNumberOfAssetsInGrid();
        System.out.println("number of rows in grid before update ="+NumberBeforeUpdate);
        int NumberAfterUpdate=new AssetsPage(driver).
                ClickAssertButton()
                .SelectSomeAssetsAndDoAnyOperationOnTheme(AssetsPage.operationInAssets.Delete,3)
                .acceptAlert()
                .ClickAssertButton()
                .GetNumberOfAssetsInGrid();

        System.out.println("number of rows in grid after update ="+NumberAfterUpdate);


        Assert.assertEquals(NumberBeforeUpdate-NumberAfterUpdate, 3, "The number of imported assets does not match the expected value.");




    }
}
