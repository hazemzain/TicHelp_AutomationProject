package Pages.ReportPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class KanbanPage {
    BrowserActions browserActions;
    Assertion assertion;
    private final By FristTicketInNewLocator= By.xpath("//*[contains(@ondragstart, 'dragstart')][1]");
    private final By FristTicketInProgressLocator= By.xpath("//div[@data-status='2']/div/div[1]");

    private final By TargetPlaceLocator=By.cssSelector("div.column[data-status=\"2\"]");
    private final By NameForFristTicketInNew=By.xpath("//div[@data-status='1']/div/div[1]/a");
    private final By NameForFristTicketInInProgressLocator=By.xpath("//div[@data-status='2']/div/div[1]/a");
    private final By AllTicketInProgressLocator=By.xpath("//div[@data-status='2']/div/div");
    private final By DeletePlaceLocator=By.xpath("//*[@id=\"content\"]/div[5]/div/div[3]");
    public KanbanPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public KanbanPage DragAndDropFirstTicketFromNewToInProgress(){
        WebElement SourseElement= browserActions.findElement(FristTicketInNewLocator);
        WebElement TargetElement= browserActions.findElement(TargetPlaceLocator);

        Actions actions = new Actions(browserActions.getDriver());

        actions.dragAndDrop(SourseElement, TargetElement).perform();

        System.out.println("Drag and drop performed successfully!");
        return new KanbanPage(browserActions.getDriver());

    }
    public  String GetNameForFristTicketInProgress(){
        return browserActions.getText(NameForFristTicketInInProgressLocator).trim();
    }
    public  String GetNameForFristTicketInNew(){
        return browserActions.getText(NameForFristTicketInNew).trim();
    }
    public  int GetNumberOfTicketInProgress(){
        List<WebElement>AllTickets=browserActions.getDriver().findElements(AllTicketInProgressLocator);
        return AllTickets.size();
    }
    public KanbanPage DragAndDropFirstTicketFromInProgressToClosed(){
        WebElement SourseElement= browserActions.findElement(FristTicketInProgressLocator);
        WebElement TargetElement= browserActions.findElement(DeletePlaceLocator);

        Actions actions = new Actions(browserActions.getDriver());

        actions.dragAndDrop(SourseElement, TargetElement).perform();

        System.out.println("Drag and drop performed successfully!");
        return new KanbanPage(browserActions.getDriver());

    }
}
