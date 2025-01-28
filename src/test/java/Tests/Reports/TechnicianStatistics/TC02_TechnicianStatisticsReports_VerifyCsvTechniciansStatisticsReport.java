package Tests.Reports.TechnicianStatistics;

import Config.Config;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC02_TechnicianStatisticsReports_VerifyCsvTechniciansStatisticsReport extends TestBase {
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

    @Test()
    public void TechniciansStatistics_TechniciansStatisticsShouldBeGenerated_WhenChooseTheCorrectTimeAndClickButton () throws IOException {
        navigateToUrl();
        login.ValidLogin();

        new HomePage(driver)
                .ClickInNewTicketButton()
                .ChoosePriority("High")
                .EnterNewSubject("Test"+formattedDateTime)
                .EnterNewDetails("This is for Test Automation HelpDisk")
                .EnterNewAddress("Egypt")
                .ClickNewSubmitButton();
       boolean Result= new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnTechniciansStatistics()
                .SelectCheckAllInCategory()
                .selectPeriodDropdownValue("Last week")
                .CleanAllCsvFile()
                .ClickInBuildButton()
                .ValidateUserTable()
                .ClickOnCsvButton()
                .waitForFileDownload("csv",30);

        Assert.assertTrue(Result);

    }
}
