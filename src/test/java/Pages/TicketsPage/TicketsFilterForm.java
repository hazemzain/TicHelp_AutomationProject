package Pages.TicketsPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TicketsFilterForm {
    BrowserActions browserActions;
    Assertion assertion;

    public TicketsFilterForm(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }

    //Locators
    By OpenFilter = By.id("toggleFilter");
    By Date = By.name("datePeriod");
    By ChosenPeriod = By.xpath("//option[@value='1']");
    By DueIn = By.name("dueFilter");
    By updated = By.name("updatedPeriod");
    By Status = By.xpath("//*[@id=\"filterForm\"]/table/tbody/tr[6]/td[2]/button/span[1]");

    //dynamic locator for the status field
    public void SelectStatus(String statusText) {
        browserActions.click(Status);
        String StatusValue = getStatusValue(statusText);
        By dynamicStatusLocator = By.id("ui-multiselect-statusId-option-" + StatusValue);
        browserActions.click(dynamicStatusLocator);
        //close status
        browserActions.click(By.xpath("//*[@id=\"filterForm\"]/table/tbody/tr[6]/td[2]/button"));
    }

    By priority = By.xpath("//button[span[text()='Priority']]");

   // By ChosenPriority = By.xpath("//input[@value='" + getPriorityValue(priority) + "']");

    //dynamic loctor for priority
    public void SelectPriority(String priorityText) {
        browserActions.click(priority); // Open the priority dropdown
        String priorityValue = getPriorityValue(priorityText); // Map the priority text to value

        By dynamicPriorityLocator = By.xpath("//label[@for='ui-multiselect-priorityId-option-" + priorityValue + "']");
        browserActions.click(dynamicPriorityLocator); // Select the priority
    }

    By FromUser_Email = By.id("fromUserId_txt");
    By Company = By.id("fromCompanyId_txt");
    By Department = By.id("fromDepartmentId");
    By Tech = By.xpath("//button/span[text()='Tech']");
    By UpdBy = By.xpath("//select[@name='badge']");
    By UpdByOptions = By.xpath("//option[text()='upd by tech']");

    //Buttons Locators
    By OnlyticketsSubscribedTo = By.xpath("//label[text()=\"Only tickets I'm subscribed to\"]");
    By SubmitButton = By.xpath("/html/body/div[1]/div[5]/table/tbody/tr/td[1]/div[1]/div[2]/div[2]/form/table/tbody/tr[14]/td/button");
    By SaveButton = By.id("btnSaveFilter");
    By SavedFilterName = By.id("filterName");
    By SaveFilterButton = By.xpath("//*[@id=\"saveFilterDiv\"]/form/button");

    By RemoveFilter = By.id("btnResetFilter");
    By ChosenTech = By.id("//label[span[text()='client']]");

    //Ticket Page body
    By CreatedTicket = By.xpath("//*[@id=\"tblTickets\"]/tbody");
    By TicketCheckBox = By.xpath("//*[@id=\"ticketRow1117\"]/td[8]/label/input");

    By AllTicketCheck = By.id("cbDelAll");
    By DeleteBulk = By.xpath("//a[text()='Delete']");
    By DeleteTicket = By.xpath("//input[@title=\"Hint: try 'Shift+Click' to select ranges!\"]");

    //Tags section
    By NewTag = By.xpath("//a[text()='automatedtag']");
    By TaggedTickets = By.xpath("//*[@id=\"tblTickets\"]/thead");

    //categories section
    By CategoryAnderson = By.xpath("//*[@id=\"divCategories\"]/ul/ul/li[1]/a/span[1]");

    //Actions
    By FilterClosed = By.xpath("//div[@style=\"display: none;\"]");

    public void OpenFilter() {
            // Check if the filter is closed.
            WebElement x = browserActions.findElement(FilterClosed);
            if (!x.isDisplayed() ) {
                browserActions.click(OpenFilter);
            }
    }

//
//    public void OpenFilter() {
//        By filterPanelLocator = By.className("filterBox");
//
//        WebElement filterPanel = browserActions.findElement(filterPanelLocator);
//
//        // Check if the filter panel is hidden by inspecting the "display" style
//        JavascriptExecutor js = (JavascriptExecutor) browserActions.getDriver();
//        String displayStyle = (String) js.executeScript("return window.getComputedStyle(arguments[0]).display;", filterPanel);
//
//        if (filterPanel != null && "none".equals(displayStyle)) {
//            browserActions.click(OpenFilter);
//        }
//    }
    private String getPeriodValue(String date){
        switch (date){
            case "Today":
                return "1"; // Value for "Today"
            case "Last week":
                return "7"; // Value for "Last week"
            case "30 days":
                return "30"; // Value for "30 days"
            case "Custom...":
                return "0"; // Value for "Custom..."
            default:
                return ""; // Default case when there's no match
        }
    }

    public void SelectPeriod(String periodText){
        browserActions.click(Date);
        String DateValue = getPeriodValue(periodText);
        By dynamicPeriodLocator = By.xpath("//select[@name='datePeriod']/option[@value='" + DateValue + "']");
        browserActions.click(dynamicPeriodLocator);
        //close
        browserActions.click(Date);

    }
    public void EnterDueIn(){
        browserActions.type(DueIn, "3");
    }
    public void SelectUpdated(){
        browserActions.click(updated);
        browserActions.click(ChosenPeriod);
    }
    //    public void SelectStatus(){
    //        browserActions.click(Status);
    //        browserActions.click(ChosenStatus);
    //
    //    }


    private String getStatusValue(String status) {
        switch (status) {
            case "In progress":
                return "0";
            case "pending QC":
                return "2";
            case "New":
                return "1";
            default:
                return "1"; // Default to "new"
        }
    }
    public void EnterUser(String User){
        browserActions.type(FromUser_Email, User);
    }
    public void EnterCompany(){
        browserActions.type(Company, "Johns Group");
    }
    public void EnterDepartment(){
        browserActions.type(Department, "dep");
    }
    public void EnterTechnician(){
        browserActions.click(Tech);
        browserActions.click(ChosenTech);
    }
    public void SelectUpdatedBy(){
        browserActions.click(UpdBy);
        browserActions.click(UpdByOptions);
    }
    public void CheckSubscribedTo(){
        browserActions.click(OnlyticketsSubscribedTo);
    }
    //
    //    public void SelectPriority(){
    //        browserActions.click(priority);
    //        browserActions.click(ChosenPriority);
    //    }

    // Helper method to map priority text to filter values
    private String getPriorityValue(String priority) {
        switch (priority) {
            case "Critical":
                return "3";
            case "High":
                return "2";
            case "Normal":
                return "1";
            default:
                return "0"; // Default to "low"
        }
    }

    public void ClickSubmit(){
        browserActions.click(SubmitButton);
    }
    public void ClickSave(){
        browserActions.click(SaveButton);
    }
    public void ResetFilter(){
        browserActions.click(RemoveFilter);
    }

    public void IsTicketDisplayed(){
        assertion.assertElementIsDisplayed(CreatedTicket);

    }
    //actions by checkboxes

    public void CheckTicket(){  browserActions.click(TicketCheckBox);}
    public void DeleteCreatedTicket(){
        CheckTicket();
        browserActions.click(DeleteTicket);
        browserActions.acceptAlert();

    }
    /*Bulk Actions*/
    public void DeleteAllTickets(){
        browserActions.click(AllTicketCheck);
        browserActions.click(DeleteBulk);
        browserActions.acceptAlert();
    }
    public void ClickTag(){
        browserActions.click(NewTag);
    }

    public void IsTicketsTableDisplayed(){
        assertion.assertElementIsDisplayed(TaggedTickets);
    }

    public void FilterByCategory(){
        browserActions.click(CategoryAnderson);
    }

    public void IsFilterResultsShown(String FilterResult){assertion.assertElementIsDisplayed(By.xpath(
            "//a[text()='" + FilterResult + "']"

    ));}
    public void EnterFilterName(String FilterName){
        browserActions.type(SavedFilterName, FilterName);
    }
    public void SaveFilter(){browserActions.click(SaveFilterButton);}
    public void ClickTheSavedFilter(String filterName){
        browserActions.click(By.xpath("//li[@class='category']/a[text()='" + filterName + "']"));
    }

}
/*,
    {

      "status": "Closed",
      "priority": "Low"
    },
    {
      "status": "New",
      "priority": "Normal"
    },

    {
      "status": "New",
      "priority": "Critical"
    }*/