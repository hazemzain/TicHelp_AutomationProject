package Tests.Reports.UserStatics;

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

public class TC01_UserStaticsReports_VerifyUserStaticsReport extends TestBase {
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
    public void UsersStatics_TheUserStaticsShouldBeGenerated_WhenChooseTheCorrectCategoryAndClickButton () {
        navigateToUrl();
        login.ValidLogin();
        new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnUserStatics()
                .selectPeriodDropdownValue("Last week")
                .ClickInBuildButton()
                .ValidateUserTable()
                ;


    }
}
