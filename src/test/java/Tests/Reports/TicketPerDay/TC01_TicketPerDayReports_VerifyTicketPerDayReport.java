package Tests.Reports.TicketPerDay;

import Config.Config;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC01_TicketPerDayReports_VerifyTicketPerDayReport extends TestBase {
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
    public void Reports_TheCustomReportShouldBeGenerated_WhenChooseTheCorrectCategoryAndClickButton () {
        navigateToUrl();
        login.ValidLogin();
        new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnTicketPerDay()
                .ClickOnBuildButton()
                .ValidateTheVisibailtyOfImagesReports()
                ;


    }
}
