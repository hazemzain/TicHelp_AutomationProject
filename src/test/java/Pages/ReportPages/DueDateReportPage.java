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

public class DueDateReportPage {
    private final By CategorySelectorLocator= By.xpath("//*[@id=\"CategoryId\"]");
    private final  By BuildButtonLocator=By.xpath("//*[@id=\"btnSearch\"]");
    private final By DiagramReportLocator=By.xpath("//*[@id=\"calendar\"]");
    private final By ExportIcalButtonLocator=By.xpath("//*[@id=\"btnICal\"]");
    BrowserActions browserActions;
    Assertion assertion;
    public DueDateReportPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public DueDateReportPage ClickInBuildButton(){
        browserActions.click(BuildButtonLocator);
        return new DueDateReportPage(browserActions.getDriver());
    }
    public void ValidateTheVisibailtyOfImagesReports(){
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement diagramReport = wait.until(ExpectedConditions.visibilityOfElementLocated(DiagramReportLocator));
        Assert.assertTrue(diagramReport.isDisplayed(), "Diagram report is not visible.");

    }
    public  DueDateReportPage ClickOnExportIcalButton(){
        browserActions.click(ExportIcalButtonLocator);

        return new DueDateReportPage(browserActions.getDriver());
    }
    public DueDateReportPage CleanAllIcalFile() throws IOException {
        FileOperation operation=new FileOperation();
        operation.cleanClonedFiles("ics");
        return new DueDateReportPage(browserActions.getDriver());
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
    public DueDateReportPage selectPeriodDropdownValue( String valueOrText) {
        WebElement dropdownElement = browserActions.getDriver().findElement(CategorySelectorLocator);
        Select dropdown = new Select(dropdownElement);
        if (valueOrText.matches("\\d+|-\\d+")) {
            dropdown.selectByValue(valueOrText);
        } else {
            dropdown.selectByVisibleText(valueOrText);
        }

        return new DueDateReportPage(browserActions.getDriver());


    }
}
