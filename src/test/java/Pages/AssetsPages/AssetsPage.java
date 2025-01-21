package Pages.AssetsPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import Utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AssetsPage {

    private  final  By AssertButton=By.xpath("//*[@id=\"divBigHeader\"]/ul[1]/li[4]/a");
    private final  By ImportButton=By.xpath("//input[@value='Import (CSV)...']");
    private  final  By UpdateFileButton=By.name("csv");
    private  final By Try_To_Update_Existing_Assets_CheckBox=By.name("cbUpdate");
    private  final By LeaveEmptyField_Assets_CheckBox=By.xpath("//*[@id=\"ignoreEmpty\"]"); //update this

    private  final  By UploadButton=By.xpath("//input[@value='Upload']");
    private final  By assetRowsLocator = By.xpath("//*[@id=\"content\"]/div[5]/div[4]/b");
    //By.id("tblTickets");
    private final By ErrorMessageLacoter=By.xpath("//*[@id=\"divUpload\"]/div");
    BrowserActions browserActions;
    Assertion assertion;
    public AssetsPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public AssetsPage ClickAssertButton(){
        browserActions.click(AssertButton);
        return new AssetsPage(browserActions.getDriver());
    }
    public AssetsPage ClickImportButton(){
        browserActions.click(ImportButton);
        return new AssetsPage(browserActions.getDriver());
    }
    public AssetsPage ClickTryToUpdateExistingAssetsCheckBox(){
        browserActions.click(Try_To_Update_Existing_Assets_CheckBox);
        return new AssetsPage(browserActions.getDriver());
    }
    public AssetsPage ClickLeaveEmptyFieldAssetsCheckBox(){
        browserActions.click(LeaveEmptyField_Assets_CheckBox);
        return new AssetsPage(browserActions.getDriver());
    }
    public AssetsPage ChooseFile(String filePath){
        Utilities.uploadFile(filePath, browserActions.getDriver(),UpdateFileButton);
        return new AssetsPage(browserActions.getDriver());
    }

    public AssetsPage ClickUploadButton(){
        browserActions.click(UploadButton);
        return new AssetsPage(browserActions.getDriver());
    }
    public  int GetNumberOfAssetsInGrid(){
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement assetRowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(assetRowsLocator));

        String totalText = browserActions.getDriver().findElement(assetRowsLocator).getText();
        return  Integer.parseInt(totalText);


    }

    public String GetErrorMessage() {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageLacoter));

        // Get and return the error message text
        return browserActions.getDriver().findElement(ErrorMessageLacoter).getText();
    }

}
