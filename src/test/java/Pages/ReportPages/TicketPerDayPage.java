package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Utilities.FileOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;

public class TicketPerDayPage {
    private final By BuildButtonLocator= By.xpath("//*[@id=\"btnBuild\"]");
    private final By DiagramReportLocator=By.xpath("//*[@id=\"chartDiv\"]/div[1]/canvas");
    private final By CsvButtonLocator=By.xpath("//*[@id=\"btnExcel\"]");
    BrowserActions browserActions;
    Assertion assertion;
    public TicketPerDayPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public  TicketPerDayPage ClickOnBuildButton(){
        browserActions.click(BuildButtonLocator);

        return new TicketPerDayPage(browserActions.getDriver());
    }
    public  TicketPerDayPage ClickOnCsvButton(){
        browserActions.click(CsvButtonLocator);

        return new TicketPerDayPage(browserActions.getDriver());
    }

    public void ValidateTheVisibailtyOfImagesReports(){
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement diagramReport = wait.until(ExpectedConditions.visibilityOfElementLocated(DiagramReportLocator));
        Assert.assertTrue(diagramReport.isDisplayed(), "Diagram report is not visible.");

    }
    public boolean CheckDownloadedZipFile() throws IOException, InterruptedException {
        Thread.sleep(5000);
        FileOperation Operation=new FileOperation();
        return Operation.checkFileInZip("csv");
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

    public TicketPerDayPage DeleteAllCsvFile(){
        String downloadsPath = "C:\\Users\\SoftLaptop\\downloads";

        // Create a File object for the directory
        File downloadsDir = new File(downloadsPath);

        // Check if the directory exists and is a directory
        if (downloadsDir.exists() && downloadsDir.isDirectory()) {
            // Get a list of all files in the directory
            File[] files = downloadsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

            if (files != null && files.length > 0) {
                // Iterate over the files and delete each one
                for (File file : files) {
                    if (file.delete()) {
                        System.out.println("Deleted: " + file.getName());
                    } else {
                        System.out.println("Failed to delete: " + file.getName());
                    }
                }
            } else {
                System.out.println("No CSV files found in the Downloads folder.");
            }
        } else {
            System.out.println("Downloads directory not found.");
        }
        return new TicketPerDayPage(browserActions.getDriver());
    }

}
