package Tests.Reports.ResponseSpeed;

import Config.Config;
import CustomAnnotation.RootCause;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC01_ResponceSpedReports_VerifyResponseSpeedReport extends TestBase {
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
    @RootCause("For Failure:when choose check all from Category and Select Last week and click bulid 404 Eeror Page appear")

    @Test(groups = "ReportFailure",description = "Verify the Response Time Graphs")
    public void Reports_TheResponseSpeedShouldBeGenerated_WhenChooseTheCorrectCategoryAndClickButton () {
        navigateToUrl();
        login.ValidLogin();
        new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnResponseSpeed()
                .SelectCheckAllInCategory()
                .selectPeriodDropdownValue("Last week")
                .ClickInBuildButton()
                .ValidateTheVisibailtyOfImagesReports()
                ;


    }
}
