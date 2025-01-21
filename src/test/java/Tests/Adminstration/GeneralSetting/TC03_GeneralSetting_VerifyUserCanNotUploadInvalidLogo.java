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

public class TC03_GeneralSetting_VerifyUserCanNotUploadInvalidLogo extends TestBase {
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
    public void GeneralSetting_TheLogoShouldBeNotUpload_WhenChooseToUploadInvalidLogo() {
        navigateToUrl();
        login.ValidLogin();
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClikInGeneralSetting()
                .UploadLogo("D:\\GBG_Projects\\TicHelp-ddt-tickets\\assets_data.docx")
                .ClickSaveChanges();

        // Wait until the logo element is either visible or not, using WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoElement = null;

        try {
            logoElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"logo\"]/a/img")));
        } catch (Exception e) {
            // Log the exception if necessary, but don't let it fail the test
            System.out.println("Logo element not found: " + e.getMessage());
        }

        // If logoElement is null, it means the element was not found; assert that it is not displayed
        if (logoElement != null) {
            Assert.assertFalse( logoElement.isDisplayed());
        } else {
            // If element is not found, the test will pass (logo was not uploaded)
            System.out.println("Logo not uploaded, test passes.");
            Assert.assertTrue(logoElement == null || !logoElement.isDisplayed());
        }
    }


}
