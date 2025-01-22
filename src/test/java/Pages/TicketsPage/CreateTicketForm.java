package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateTicketForm {
    BrowserActions browserActions;
    Assertion assertion;

    public CreateTicketForm(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    //Locators
    By CategoryField = By.xpath("//*[@id=\"CategoryRow\"]/td[1]/div/a/i");
    By CatogeryTextLocator=By.xpath("//*[@id=\"CategoryRow\"]/td[1]/div/a/div");//*[@id="CategoryRow"]/td[1]/div/a/div
    By CategorySelection = By.xpath("//li[@class='dropdown-item']//span[@class='dropdown-text' and text()='Anderson']\n");
    By subject = By.id("Subject");
    By TextDetails = By.xpath("//*[@id='rteBody']");
    By Address = By.id("CustomFieldValue28");
    private final By NewAddress=By.xpath("//*[@id=\"CustomFieldValue64\"]");
   private final By PrioritydropdownLocator = By.xpath("//*[@id='PriorityID']");

    By advancedButton =By.id("lnkAdvanced");
    By tagField= By.id("Tags_tag");
    By SubmitButton = By.id("btnAdd");
    By TakeOverButton = By.id("CategoryID-error");
    By GuestEmail = By.id("YourEmail");




    public void validatemsg(){
        assertion.assertElementIsDisplayed(TakeOverButton);
    }
    public void ClickCatrgory(){
        browserActions.click(CategoryField);

    }
    public  String GetCategory(){

        return  browserActions.getText(CatogeryTextLocator);
//        WebElement dropdownElement = browserActions.getDriver().findElement(CategoryField);
//
//        Select dropdown = new Select(dropdownElement);
//
//        WebElement defaultOption = dropdown.getFirstSelectedOption();
//
//        // Get the text of the default option
//        String defaultOptionText = defaultOption.getText();
//        // Locate the "defualt" dropdown item
//        WebElement DefaultElement = browserActions.getDriver()
//                .findElement(By.xpath("//a[@role='menuitem']/span[@class='dropdown-text' and text()='" + defaultOptionText + "']"));
//
//        // Create Actions object for hover action
//        Actions actions = new Actions(browserActions.getDriver());
//
//        // Hover over the "sara" dropdown item to reveal the sub-dropdown
//        actions.moveToElement(DefaultElement).perform();
//
//        // Wait for the sub-dropdown option to be visible
//        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
//        WebElement subDropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//*[@id=\"CategoryRow\"]/td[1]/div/nav/div/div[4]/ul/li/a/span")));
//
//        // Get and print the text of the sub-dropdown option
//        String optionText = subDropdownOption.getText();
//        System.out.println("Sub-dropdown option: " + optionText);
//
//        // Return the text of the sub-dropdown option
//        return optionText;


    }
    public void SelectCategory(){
        browserActions.click(CategorySelection);
    }
    public void EnterSubject(String sub ){
        browserActions.type(subject, sub);
    }
    public void EnterDetails(String details){
        browserActions.click(TextDetails);
        browserActions.type(TextDetails, details);
    }
    public void EnterAddress(String address){
        browserActions.type(Address,  address);
    }
    public void ClickAdvancedButton(){browserActions.click(advancedButton);}
    public void ClickSubmitButton(){

        browserActions.click(SubmitButton);
    }
    public void EnterTag(){
        browserActions.type(tagField, "AutomatedTag");
    }
    public void EnterGuestMail(String GuestMail){
        browserActions.type(GuestEmail,GuestMail );
    }

    public CreateTicketForm EnterMail(String GuestMail){
        browserActions.type(GuestEmail,GuestMail );
        return new CreateTicketForm(browserActions.getDriver());
    }
    public CreateTicketForm ChoosePriority(String Priority){

        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));
        WebElement priorityDropdown = wait.until(ExpectedConditions.elementToBeClickable(PrioritydropdownLocator));

        Select select = new Select(priorityDropdown);

        select.selectByVisibleText(Priority);
        return  new CreateTicketForm(browserActions.getDriver());
    }
    public CreateTicketForm EnterNewSubject(String Subject){
        browserActions.type(subject,Subject );
        return new CreateTicketForm(browserActions.getDriver());
    }
    public CreateTicketForm EnterNewDetails(String Details){
        browserActions.type(TextDetails,Details );
        return new CreateTicketForm(browserActions.getDriver());
    }
    public CreateTicketForm EnterNewAddress(String address){
        browserActions.type(NewAddress,  address);
        return new CreateTicketForm(browserActions.getDriver());

    }
    public ThanksTicketPage ClickNewSubmitButton(){

        browserActions.click(SubmitButton);
        return new ThanksTicketPage(browserActions.getDriver());

    }

}
