package Tests.Reports.ReportHome;

import Config.Config;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC03_Reports_VerifyNavigationButtons extends TestBase {
    String formattedDateTime;
    String url = Config.getProperty("URL");
    Login login;
    NavBar navBar;


    @BeforeMethod
    public void setupTest() {
        login = new Login(driver);
        navBar = new NavBar(driver);
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the date and time as needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        formattedDateTime = currentDateTime.format(formatter);

    }
    public void navigateToUrl() {
        login.navigateToWebsite(url);
    }
    @Test
    public void Reports_TheEditReportPermissionShouldBeVisibility_WhenGoToReportsPage () {
        navigateToUrl();
        login.ValidLogin();
        String Url="http://20.117.78.174:8060/Reporting/Summary";
        new HomePage(driver)
                .ClickOnReportButton()
                .ClickInSummeryButton();

        Assert.assertEquals(driver.getCurrentUrl(),Url);


    }
}
