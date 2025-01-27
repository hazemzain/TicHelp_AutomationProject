package Tests.Reports.ReportHome;

import Config.Config;
import CustomAnnotation.RootCause;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.ReportPages.ReportPage;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC05_Reports_VerifyResetButtonSelectionDropdownToDefault extends TestBase {
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
    @RootCause("For Failure:when Click In Edit Button To Change the Default Values For DropDown And Click Save and Click In Reset Button To Restore the default Value The APP is crash")

    @Test(groups = "ReportFailure",description = "Verify the Functionality of Reset To default Button")

    public void Reports_TheEditReportPermissionShouldBeVisibility_WhenGoToReportsPage () {
        navigateToUrl();
        login.ValidLogin();
       new HomePage(driver)
                .ClickOnReportButton()
                .ClickINEditButton()
                .verifyAndSelectPermissions();

       new ReportPage(driver)
               .ClickInResetToDefault()
               .ClickINEditButton()
               .verifyResetButtonAfterChangeTheDropDowns();

    }
}
