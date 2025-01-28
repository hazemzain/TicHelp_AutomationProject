package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Utilities.FileOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UserStaticsPage {
    private final By PeriodSelectorLocator=By.id("periodType");
    private final  By BuildButtonLocator=By.xpath("//*[@id=\"btnSearch\"]");
    private final By TableLocator= By.cssSelector("table.lightbg.outerroundedbox.horizseparated.sortable");
    private final By RowsLocator=By.xpath(".//tbody/tr");
    private final By UserNameLocator=By.xpath(".//td[1]/a");
    private final By CsvButtonLocator=By.xpath("//*[@id=\"btnExcel\"]");

    BrowserActions browserActions;
    Assertion assertion;
    public UserStaticsPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    public UserStaticsPage selectPeriodDropdownValue( String valueOrText) {
        WebElement dropdownElement = browserActions.getDriver().findElement(PeriodSelectorLocator);
        Select dropdown = new Select(dropdownElement);
        if (valueOrText.matches("\\d+|-\\d+")) {
            dropdown.selectByValue(valueOrText);
        } else {
            dropdown.selectByVisibleText(valueOrText);
        }

        return new UserStaticsPage(browserActions.getDriver());


    }
    public UserStaticsPage ClickInBuildButton(){
        browserActions.click(BuildButtonLocator);
        return new UserStaticsPage(browserActions.getDriver());
    }
    public UserStaticsPage ValidateUserTable(){
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));

        // Wait for table to be visible
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(TableLocator));
        Assert.assertTrue(table.isDisplayed(),"the table not displayed");

        // Get all rows in table body
        List<WebElement> rows = table.findElements(RowsLocator);

        boolean foundAdmin = false;
        boolean foundEmail = false;

        for (WebElement row : rows) {
            // Get the user email/name from first column
            WebElement userLink = row.findElement(UserNameLocator);
            String userText = userLink.getText().trim();

            if (userText.equals("admin")) {
                foundAdmin = true;
            } else if (userText.equals("hazemzain17@gmail.com")) {
                foundEmail = true;
            }

            // Early exit if both found
            if (foundAdmin && foundEmail) break;
        }
        Assert.assertTrue(foundAdmin,"the Admin User Not fOUND");
        Assert.assertTrue(foundEmail,"the Admin User Not fOUND");
        return new UserStaticsPage(browserActions.getDriver());


    }

    public int GetNumberOfRowsInTable(){
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));

        // Wait for table to be visible
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(TableLocator));

        // Get all rows in table body
        List<WebElement> rows = table.findElements(RowsLocator);
        return rows.size();

    }
    public UserStaticsPage CleanAllCsvFile() throws IOException {
        FileOperation operation=new FileOperation();
        operation.cleanClonedFiles("csv");
        return new UserStaticsPage(browserActions.getDriver());
    }
    public  UserStaticsPage ClickOnCsvButton(){
        browserActions.click(CsvButtonLocator);

        return new UserStaticsPage(browserActions.getDriver());
    }

    public boolean waitForFileDownload(String fileExtension, int timeoutSeconds) {

        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);
        while (System.currentTimeMillis() < endTime) {
            File lastDownloadedFile = getLastDownloadedFile(fileExtension);
            if (lastDownloadedFile != null) {
                System.out.println("Last downloaded file: " + lastDownloadedFile.getName());
                return true;
            }
            try {
                Thread.sleep(1000); // Wait for 1 second before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted.");
                return false;
            }
        }
        System.out.println("No file with extension '" + fileExtension + "' was found within the timeout.");
        return false;
    }

    /**
     * Retrieves the last downloaded file with the specified extension.
     *
     * @param fileExtension The file extension to look for (e.g., "csv").
     * @return The last downloaded file with the specified extension, or null if no such file exists.
     */
    public File getLastDownloadedFile(String fileExtension) {
        File dir = new File("C:\\Users\\SoftLaptop\\downloads");
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No files found in the directory.");
            return null;
        }

        // Sort files by last modified timestamp (most recent first)
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        // Find the most recent file with the specified extension
        for (File file : files) {
            if (file.getName().toLowerCase().endsWith("." + fileExtension.toLowerCase())) {
                return file;
            }
        }

        System.out.println("No file with extension '" + fileExtension + "' found.");
        return null;
    }
}
