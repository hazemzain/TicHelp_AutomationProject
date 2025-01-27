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

public class TC02_Kanbans_VerifyDragAndDropFromInProgressToDelete extends TestBase {
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
    public void Kanbans_TheTicketShouldBeMoveFromInProgressToDelete_WhenDragTheTicketFromItInprogressAndPutItInDeletePlace () {
        navigateToUrl();
        login.ValidLogin();
        int resultBefore=new HomePage(driver)
                .ClickOnReportButton()
                .ClickOnKanban()
                .GetNumberOfTicketInProgress();
        new KanbanPage(driver)
                .DragAndDropFirstTicketFromInProgressToClosed()
                ;
        int ResultAfter=new KanbanPage(driver)
                .GetNumberOfTicketInProgress();


        Assert.assertEquals(resultBefore-ResultAfter,1);


    }
}
