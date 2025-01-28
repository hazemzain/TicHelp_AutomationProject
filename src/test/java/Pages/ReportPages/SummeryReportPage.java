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

public class SummeryReportPage {
    private final By CategorySelectorLocator= By.xpath("//*[@id=\"searchForm\"]/div/div/div[1]/span[1]/button");
    private final By CheckAllOptionLocator=By.xpath("/html/body/div[7]/div/ul/li[1]/a/span[2]");
    private final By PeriodSelectorLocator=By.xpath("//*[@id=\"periodType\"]");
    private final  By BuildButtonLocator=By.xpath("//*[@id=\"btnSearch\"]");
    private final By FirstCharLocator=By.xpath("//*[@id=\"catChartDiv\"]/div[1]/canvas");
    private final By SecendCharLocator=By.xpath("//*[@id=\"statusChartDiv\"]/div[1]/canvas");
    private final By ThridCharLocator=By.xpath("//*[@id=\"originChartDiv\"]/div[1]/canvas");
    private final By FourthCharLocator=By.xpath("//*[@id=\"priorityChartDiv\"]/div[1]/canvas");
    private final By FiveCharLocator=By.xpath("//*[@id=\"overdueChartDiv\"]/div[1]/canvas");
    private final By CSVButton=By.xpath("//*[@id=\"btnExcel\"]");

    BrowserActions browserActions;
    Assertion assertion;
    public SummeryReportPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public  SummeryReportPage SelectCheckAllInCategory(){
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(50));
        WebElement dropdownButton = wait.until(
                ExpectedConditions.elementToBeClickable(CategorySelectorLocator)
        );
        dropdownButton.click();
        WebElement checkAllOption = wait.until(
                ExpectedConditions.visibilityOfElementLocated(CheckAllOptionLocator)
        );
        checkAllOption.click();
        dropdownButton.click();
        return new SummeryReportPage(browserActions.getDriver());
    }
    public SummeryReportPage selectPeriodDropdownValue( String valueOrText) {
        WebElement dropdownElement = browserActions.getDriver().findElement(PeriodSelectorLocator);
        Select dropdown = new Select(dropdownElement);
        if (valueOrText.matches("\\d+|-\\d+")) {
            dropdown.selectByValue(valueOrText);
        } else {
            dropdown.selectByVisibleText(valueOrText);
        }

        return new SummeryReportPage(browserActions.getDriver());


    }
    public SummeryReportPage ClickInBuildButton(){
        browserActions.click(BuildButtonLocator);
        return new SummeryReportPage(browserActions.getDriver());
    }
    public SummeryReportPage ValidateThatSummeryReportIsGenerated(){
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement diagramReport = wait.until(ExpectedConditions.visibilityOfElementLocated(FirstCharLocator));
        Assert.assertTrue(diagramReport.isDisplayed(), "Diagram report is not visible.");
        WebElement diagramReport2 = wait.until(ExpectedConditions.visibilityOfElementLocated(SecendCharLocator));
        Assert.assertTrue(diagramReport2.isDisplayed(), "Diagram report is not visible.");
        WebElement diagramReport3 = wait.until(ExpectedConditions.visibilityOfElementLocated(ThridCharLocator));
        Assert.assertTrue(diagramReport3.isDisplayed(), "Diagram report is not visible.");
        WebElement diagramReport4= wait.until(ExpectedConditions.visibilityOfElementLocated(FourthCharLocator));
        Assert.assertTrue(diagramReport4.isDisplayed(), "Diagram report is not visible.");
        WebElement diagramReport5= wait.until(ExpectedConditions.visibilityOfElementLocated(FiveCharLocator));
        Assert.assertTrue(diagramReport5.isDisplayed(), "Diagram report is not visible.");
        return new SummeryReportPage(browserActions.getDriver());

    }
    public SummeryReportPage ClickInCSVFile(){
        // cleanCSVFiles();
        browserActions.click(CSVButton);
        return new SummeryReportPage(browserActions.getDriver());
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
    public SummeryReportPage CleanAllCsvFile() throws IOException {
        FileOperation operation=new FileOperation();
        operation.cleanClonedFiles("csv");
        return new SummeryReportPage(browserActions.getDriver());
    }
}
