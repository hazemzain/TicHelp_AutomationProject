package Tests.Reports.UserStatics;

import Config.Config;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.ReportPages.UserStaticsPage;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC03_UserStaticsReports_VerifyCsvButtonFuncationality extends TestBase {
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
    public void UsersStatics_TheUserStaticsShouldBeGenerated_WhenClickInCSVButton () throws IOException {
        navigateToUrl();
        login.ValidLogin();
        boolean Result=new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnUserStatics()
                .selectPeriodDropdownValue("Last week")
                .CleanAllCsvFile()
                .ClickInBuildButton()
                .ClickOnCsvButton()
                .waitForFileDownload("csv",30);
        Assert.assertTrue(Result);




    }
}
