package Tests.Adminstration.AutomationRules;

import Config.Config;
import Pages.AdminstrationPages.AdminstrationPage;
import Pages.LoginPage.Login;
import Pages.NavBar.NavBar;
import Tests.TestBase;
import Utilities.FileOperation;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC02_AutomationRules_verifyRulesExportToXML extends TestBase {
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
    public void AutomationRules_TheXMLRulesShouldBeDownloaded_WhenClickInExpandXMlFile () throws IOException {
        new FileOperation().cleanClonedFiles(".xml");
        navigateToUrl();
        login.ValidLogin();
        new AdminstrationPage(driver)
                .ClickAdminstrationButton()
                .ClickInAutomationRules()
                .ClickInExpandXml()

                ;
        // Wait for file to download
        File downloadedFile = new File("C:\\Downloads\\AutomationRules.xml");
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(d -> downloadedFile.exists());

        // 3. Verify XML file downloaded
        File xmlFile = new FileOperation().waitForFileDownload(".xml", 15);
        Assert.assertNotNull(xmlFile, "XML file was not downloaded");
        Assert.assertTrue(xmlFile.length() > 0, "XML file is empty");
    }
}
