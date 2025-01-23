package Pages.AssetsPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Utilities.Utilities;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class AssetsPage {

    private final By AssertButton = By.xpath("//*[@id=\"divBigHeader\"]/ul[1]/li[4]/a");
    private final By ImportButton = By.xpath("//input[@value='Import (CSV)...']");
    private final By UpdateFileButton = By.name("csv");
    private final By Try_To_Update_Existing_Assets_CheckBox = By.name("cbUpdate");
    private final By LeaveEmptyField_Assets_CheckBox = By.xpath("//*[@id=\"ignoreEmpty\"]"); //update this

    private final By UploadButton = By.xpath("//input[@value='Upload']");
    private final By assetRowsLocator = By.xpath("//*[@id=\"content\"]/div[5]/div[4]/b");
    //By.id("tblTickets");
    private final By ErrorMessageLacoter = By.xpath("//*[@id=\"divUpload\"]/div");
    private final By SelectAssetButton = By.xpath("//*[starts-with(@id, 'asset_')]/td[13]");
    private final By DeleteChooseLocator = By.xpath("//*[@id=\"bulk-actions-popup\"]/ul/li[1]/a");
    private final By DisableChooseLocator = By.xpath("//*[@id=\"bulk-actions-popup\"]/ul/li[2]/a");
    private final By CloneChooseLocator = By.xpath("//*[@id=\"bulk-actions-popup\"]/ul/li[3]/a");
    private final By TagChooseLocator = By.xpath("//*[@id=\"bulk-actions-popup\"]/ul/li[4]/a");
    private final By ShowDisableAssetsButtonLocator = By.xpath("//*[@id=\"content\"]/div[5]/div[1]/a");
    private final By AssetsRowsLocator = By.xpath("//*[starts-with(@id, 'asset_') and @class='highlight']");
    private final By ExportButtonLocator = By.xpath("//*[@id=\"content\"]/div[5]/div[1]/input[1]");
    BrowserActions browserActions;
    Assertion assertion;
    static List<String> RowsSelector;

    public AssetsPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    public AssetsPage ClickAssertButton() {
        browserActions.click(AssertButton);
        return new AssetsPage(browserActions.getDriver());
    }

    public AssetsPage ClickImportButton() {
        browserActions.click(ImportButton);
        return new AssetsPage(browserActions.getDriver());
    }

    public AssetsPage ClickTryToUpdateExistingAssetsCheckBox() {
        browserActions.click(Try_To_Update_Existing_Assets_CheckBox);
        return new AssetsPage(browserActions.getDriver());
    }

    public AssetsPage ClickLeaveEmptyFieldAssetsCheckBox() {
        browserActions.click(LeaveEmptyField_Assets_CheckBox);
        return new AssetsPage(browserActions.getDriver());
    }

    public AssetsPage ChooseFile(String filePath) {
        Utilities.uploadFile(filePath, browserActions.getDriver(), UpdateFileButton);
        return new AssetsPage(browserActions.getDriver());
    }

    public AssetsPage ClickUploadButton() {
        browserActions.click(UploadButton);
        return new AssetsPage(browserActions.getDriver());
    }

    public int GetNumberOfAssetsInGrid() {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement assetRowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(assetRowsLocator));

        String totalText = browserActions.getDriver().findElement(assetRowsLocator).getText();
        return Integer.parseInt(totalText);


    }

    public String GetErrorMessage() {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageLacoter));

        // Get and return the error message text
        return browserActions.getDriver().findElement(ErrorMessageLacoter).getText();
    }

    public enum operationInAssets {
        Delete,
        Disable,
        Clone, Tag


    }

    public AssetsPage SelectSomeAssetsAndDoAnyOperationOnTheme(operationInAssets op, int NumberOfRowsAssets) {
        List<WebElement> buttons = browserActions.getDriver().findElements(SelectAssetButton);

        for (int i = 0; i < NumberOfRowsAssets; i++) {
            buttons.get(i).click();
        }
        RowsSelector = browserActions.getDriver().findElements(AssetsRowsLocator)
                .stream()
                .map(row -> row.getAttribute("id"))
                .collect(Collectors.toList());
        ;
        switch (op) {
            case Delete:
                browserActions.click(DeleteChooseLocator);

                break;
            case Disable:
                browserActions.click(DisableChooseLocator);
                break;
            case Clone:
                browserActions.click(CloneChooseLocator);
                break;
            case Tag:
                browserActions.click(TagChooseLocator);

                break;
        }

        //browserActions.click(DisableChooseLocator);
        return new AssetsPage(browserActions.getDriver());
    }

    public AssetsPage acceptAlert() {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return new AssetsPage(browserActions.getDriver());
    }

    public ShowDisableAssetsPage ClickOnShowDisableAssetsButton() {
        browserActions.click(ShowDisableAssetsButtonLocator);
        return new ShowDisableAssetsPage(browserActions.getDriver(), RowsSelector);
    }

    public AssetsPage ClickInExportButton() {
        browserActions.click(ExportButtonLocator);
        return new AssetsPage(browserActions.getDriver());
    }

    protected String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";
     public boolean waitForFileDownload(String fileExtension, int timeoutSeconds) {
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);
        while (System.currentTimeMillis() < endTime) {
            File[] files = new File(downloadDir).listFiles();
            if (files != null) {
                if(getLastDownloadedFile()){
                    return true;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }
    public Boolean getLastDownloadedFile() {
        cleanCSVFiles();
        File dir = new File(downloadDir);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastFile = files[0];
        if(lastFile.getName().endsWith(".csv")){
            return true;
        }

        return false;
    }
    private void cleanCSVFiles() {
        File dir = new File(downloadDir);
        if (!dir.exists()) {
            return; // No directory to clean
        }

        File[] csvFiles = dir.listFiles((dir1, name) -> name.endsWith(".csv"));
        if (csvFiles == null) return;

        for (File file : csvFiles) {
            if (file.delete()) {
                System.out.println("Deleted: " + file.getName());
            } else {
                System.out.println("Failed to delete: " + file.getName());
            }
        }

    }

}