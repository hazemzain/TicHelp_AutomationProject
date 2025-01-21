package Tests.Adminstration.GeneralSetting;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC02_GeneralSetting_VerifyUserCanUploadValidFaviconFile extends TestBase {
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;
    WebDriverWait wait;

    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }

    @Test
    public void GeneralSetting_TheFaviconImageShouldBeUpload_WhenChooseToUploadFaviconImage() {
        navigateToUrl();
        login.ValidLogin();
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .UploadFavicon("D:\\GBG_Projects\\TicHelp-ddt-tickets\\allureScreen2.png")
                .ClickSaveChanges();

        //wait to check if it visible or not

        WebElement FaviconElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"aspnetForm\"]/div/table[1]/tbody[2]/tr[1]/td[4]/img")));

        Assert.assertTrue(FaviconElement.isDisplayed(),"The Logo Not Displayed");
    }
}
