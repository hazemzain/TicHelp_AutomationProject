package Tests.Reports.TicketPerDay;

import Config.Config;
import CustomAnnotation.RootCause;
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

public class TC02_TicketPerDay_VerifyCsvButtonFunctionalityInTicketPerDayReport extends TestBase {
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
    @RootCause("For Failure:when Click In Csv File The Zip File Is Downloaded")

    @Test(groups = "ReportFailure",description = "Verify the Functionality of Csv Button")

    public void TicketPerDay_TheCsvFileShouldBeGenerated_WhenClickInCsv () throws InterruptedException, IOException {
        navigateToUrl();
        login.ValidLogin();
        boolean Result=new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnTicketPerDay()
                .DeleteAllCsvFile()
                .ClickOnCsvButton()
                .CheckDownloadedZipFile();
        Thread.sleep(5000);

        Assert.assertTrue(Result);


    }
}
