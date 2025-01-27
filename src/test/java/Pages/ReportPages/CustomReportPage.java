package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CustomReportPage {
    private final By CategorySelectorLocator= By.xpath("//*[@id=\"searchForm\"]/div/div/span[1]/button");
    private final By CheckAllOptionLocator=By.cssSelector(".ui-multiselect-all");
    private final By PeriodSelectorLocator=By.id("periodType");
    private final  By BuildButtonLocator=By.xpath("//*[@id=\"btnBuild\"]");
    private final By TableLocator=By.cssSelector("table.lightbg");
    private final By RowLocator=By.cssSelector("tbody tr");
    private final By CSVButton=By.xpath("//*[@id=\"btnExcel\"]");
    BrowserActions browserActions;
    Assertion assertion;
    public CustomReportPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public  CustomReportPage SelectCheckAllInCategory(){
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
        return new CustomReportPage(browserActions.getDriver());
    }
    public CustomReportPage selectPeriodDropdownValue( String valueOrText) {
        WebElement dropdownElement = browserActions.getDriver().findElement(PeriodSelectorLocator);
        Select dropdown = new Select(dropdownElement);
        if (valueOrText.matches("\\d+|-\\d+")) {
            dropdown.selectByValue(valueOrText);
        } else {
            dropdown.selectByVisibleText(valueOrText);
        }

        return new CustomReportPage(browserActions.getDriver());


    }
    public CustomReportPage ClickInBuildButton(){
        browserActions.click(BuildButtonLocator);
        return new CustomReportPage(browserActions.getDriver());
    }
    public CustomReportPage ClickInCSVFile(){
       // cleanCSVFiles();
        browserActions.click(CSVButton);
        return new CustomReportPage(browserActions.getDriver());
    }

    public void ValidateThatReportIsGenerated(){
        // Wait for the table to be visible
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(TableLocator));
        Assert.assertTrue(table.isDisplayed());
        List<WebElement> rows = table.findElements(RowLocator);
        Assert.assertTrue(rows.size()>0);
    }
    protected String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";
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
