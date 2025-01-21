package Tests.Adminstration;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Pages.TicketsPage.TicketsPage;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC04_VerifyForwardingEmailBehavior extends TestBase {

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
    public void Adminstration_TheTicketShouldBeForwardWithExtractOriginalSender_WhenExtractOriginalSenderFromForwardedEmailsIsEnable ()
    {
        navigateToUrl();
        login.ValidLogin();
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickEmailSettingButton()
                .checkCheckboxExtractOriginalSenderFromForwardedEmailsAndPerformAction(true);
        String ForwardSubject=new TicketsPage(driver)
                .ClickTicketButton()
                .ClickInLastTicket()
                .ClickInMoreOptionButton()
                .ClickForwardEmailOptionButton()
                .GetSubjectINForwardEmail();
        Assert.assertTrue(ForwardSubject.contains("Fwd"),"the Subject not Contain FWd");

        String OriginalEmail=new TicketsPage(driver)
                .ClickTicketButton()
                .ClickInLastTicket()
                .GetTheOriginalEmailForTicket();

        String ForwardMessage=new TicketsPage(driver)
                .ClickTicketButton()
                .ClickInLastTicket()
                .ClickInMoreOptionButton()
                .ClickForwardEmailOptionButton()
                .GetForwardMessage();
        Assert.assertTrue(ForwardMessage.contains(OriginalEmail),"the Forward Email Not Contain the Orignal Email");
    }


    }
