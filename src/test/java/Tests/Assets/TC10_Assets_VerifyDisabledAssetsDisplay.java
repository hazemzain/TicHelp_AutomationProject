package Tests.Assets;

import Config.Config;
import Pages.AssetsPages.AssetsPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC10_Assets_VerifyDisabledAssetsDisplay extends TestBase {
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
    public void Assets_DisabledAssetsShouldBeDisplayed_WhenClickOnShowDisableButton(){
        navigateToUrl();
        login.ValidLogin();
        int NumberBeforeUpdate=new AssetsPage(driver)
                .ClickAssertButton()
                .GetNumberOfAssetsInGrid();
        int NumberOfDisabledAssetsBeforeAnyOperation=new AssetsPage(driver)
                .ClickOnShowDisableAssetsButton()
                .GetNumberOfDisabledAsset();
        System.out.println("number of rows in grid before update ="+NumberBeforeUpdate);
        System.out.println("NumberOfDisabledAssetsBeforeAnyOperation of rows in grid before update ="+NumberOfDisabledAssetsBeforeAnyOperation);

        int NumberAfterUpdate=new AssetsPage(driver).
                ClickAssertButton()
                .SelectSomeAssetsAndDoAnyOperationOnTheme(AssetsPage.operationInAssets.Disable,3)
                .acceptAlert()
                .ClickAssertButton()
                .GetNumberOfAssetsInGrid();

        System.out.println("number of rows in grid after update ="+NumberAfterUpdate);


        Assert.assertEquals(NumberBeforeUpdate-NumberAfterUpdate, 3, "The number of imported assets does not match the expected value.");

        int NumberOfDisabledAssets=new AssetsPage(driver)
                .ClickOnShowDisableAssetsButton()
                .GetNumberOfDisabledAsset();
        System.out.println("NumberOfDisabledAssets of rows in grid after update ="+NumberOfDisabledAssets);


        Assert.assertEquals(NumberOfDisabledAssets, NumberOfDisabledAssetsBeforeAnyOperation+(NumberBeforeUpdate-NumberAfterUpdate), "The number of imported assets does not match the expected value.");






    }
}
