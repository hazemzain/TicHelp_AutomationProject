package Tests.Reports.Kanbans;

import Config.Config;
import Pages.HomePages.HomePage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.ReportPages.KanbanPage;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC01_Kanbans_VerifyDragAndDropFunctionality extends TestBase {
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
    public void Kanbans_TheTicketShouldBeMoveFromNewToInProgress_WhenDragTheTicketFromNewAndButItInprogress () {
        navigateToUrl();
        login.ValidLogin();
        String resultBefore=new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnKanban()
                .GetNameForFristTicketInNew();
        new KanbanPage(driver)
                .DragAndDropFirstTicketFromNewToInProgress()
                ;
        String ResultAfter=new KanbanPage(driver)
                .GetNameForFristTicketInProgress();


        Assert.assertEquals(ResultAfter,resultBefore);


    }
}
