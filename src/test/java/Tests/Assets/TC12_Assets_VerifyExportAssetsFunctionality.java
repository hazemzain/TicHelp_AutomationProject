package Tests.Assets;

import Config.Config;
import Pages.AssetsPages.AssetsPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class TC12_Assets_VerifyExportAssetsFunctionality extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    protected String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";



    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);

    }
    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }
    @Test
    public void Assets_TheCSVFileShouldDownloadWithAllAssets_WhenClickOnExportButton(){
        navigateToUrl();
        login.ValidLogin();
        boolean result=new AssetsPage(driver)
                .ClickAssertButton()
                .ClickInExportButton()
                .waitForFileDownload(".csv",30);

        Assert.assertTrue(result);

    }
}
