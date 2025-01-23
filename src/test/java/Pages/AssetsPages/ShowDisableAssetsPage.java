package Pages.AssetsPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowDisableAssetsPage {
    private final By SelectAssetButton=By.xpath("//*[starts-with(@id, 'asset_')]/td[13]");
    private final By RowsLocator=By.xpath("//*[starts-with(@id, 'asset_')]");



    private final By PAGINATION_LOCATOR = By.xpath("//*[@id='content']/div[5]/div[@class='pager']");
    private final By PAGE_LINKS = By.xpath(".//a[contains(@href, 'page=')]");
    private final By CURRENT_PAGE = By.xpath("//*[@id=\"content\"]/div[5]/div[3]/b");
    private final By ROWS_LOCATOR = By.xpath("//*[starts-with(@id, 'asset_')]");
    private final By AllButtonLocator=By.xpath(".//a[contains(@href, 'page=-1')]");
    BrowserActions browserActions;
    Assertion assertion;
    List<String> _Rows;
    public ShowDisableAssetsPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);

    }
    public ShowDisableAssetsPage(WebDriver driver,List<String> Rows) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
        _Rows=Rows;
    }

    public  Boolean CheckThatAllAssetsSelectedIsMoveToDisablePage(){
        browserActions.click(AllButtonLocator);
       // List<WebElement> RowsInDisablePage = browserActions.getDriver().findElements(RowsLocator);
        List<String> disabledAssetIds = browserActions.getDriver().findElements(RowsLocator)
                .stream()
                .map(row -> row.getAttribute("id")) // Replace with unique identifier logic
                .collect(Collectors.toList());

    return disabledAssetIds.containsAll(_Rows);
    }
    public int GetNumberOfDisabledAsset(){
        List<WebElement> buttons = browserActions.getDriver().findElements(SelectAssetButton);
        return buttons.size();

    }

    public List<WebElement> getAllRowsAcrossPages() {
        List<WebElement> allRows = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement pagination = browserActions.findElement(PAGINATION_LOCATOR);

        // Get all page numbers (including current page)
        List<Integer> pageNumbers = getPageNumbers(pagination);

        for (int pageNumber : pageNumbers) {
            // Click the page (except first page which is already loaded)
            if (pageNumber != 1) {
                WebElement pageLink = pagination.findElement(
                        By.xpath(".//a[contains(@href, 'page=" + pageNumber + "')]"));
                pageLink.click();

                // Wait for page load
                wait.until(ExpectedConditions.stalenessOf(allRows.isEmpty() ?
                        browserActions.findElement(ROWS_LOCATOR) : allRows.get(0)));
            }

            // Collect current page rows
            List<WebElement> currentRows = browserActions.getDriver().findElements(ROWS_LOCATOR);
            allRows.addAll(currentRows);
        }

        return allRows;
    }
    private List<Integer> getPageNumbers(WebElement pagination) {
        List<Integer> pages = new ArrayList<>();

        // Add current page (wrapped in <b> tag)
        pages.add(Integer.parseInt(pagination.findElement(CURRENT_PAGE).getText()));

        // Add other pages from links (filter out "All" page)
        List<WebElement> pageLinks = pagination.findElements(PAGE_LINKS);
        for (WebElement link : pageLinks) {
            String href = link.getAttribute("href");
            if (href.contains("page=-1")) continue; // Skip "All" link

            int pageNum = extractPageNumberFromHref(href);
            if (!pages.contains(pageNum)) {
                pages.add(pageNum);
            }
        }

        return pages;
    }
    private int extractPageNumberFromHref(String href) {
        // Extract page number from URL pattern: "/Assets?page=2&ShowDisabled=True"
        String[] params = href.split("&")[0].split("=");
        return Integer.parseInt(params[params.length - 1]);
    }
}
