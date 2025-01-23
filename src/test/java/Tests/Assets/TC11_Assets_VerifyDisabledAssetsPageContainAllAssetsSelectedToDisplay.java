package Tests.Assets;

import Config.Config;
import Pages.AssetsPages.AssetsPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC11_Assets_VerifyDisabledAssetsPageContainAllAssetsSelectedToDisplay extends TestBase {
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

        int NumberAfterUpdate=new AssetsPage(driver).
                ClickAssertButton()
                .SelectSomeAssetsAndDoAnyOperationOnTheme(AssetsPage.operationInAssets.Disable,3)
                .acceptAlert()
                .ClickAssertButton()
                .GetNumberOfAssetsInGrid();


        boolean result=new AssetsPage(driver)
                .ClickOnShowDisableAssetsButton()
                .CheckThatAllAssetsSelectedIsMoveToDisablePage();

        Assert.assertTrue(result);








    }
}
