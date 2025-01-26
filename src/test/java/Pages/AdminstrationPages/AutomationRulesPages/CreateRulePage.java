package Pages.AdminstrationPages.AutomationRulesPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateRulePage {
    public enum WhenThisRuleHappen {
        TICKET_CREATED("0", "Ticket is created"),
        STATUS_CHANGED("7", "Ticket status has changed"),
        SATISFACTION_RATED("15", "Ticket satisfaction is rated by submitter"),
        PRIORITY_CHANGED("8", "Ticket priority has changed"),
        REOPENED("10", "Ticket is re-opened"),
        MOVED_CATEGORY("5", "Ticket is moved to a new category"),
        DELETED("14", "Ticket is deleted"),
        CLOSED_DUPLICATE("16", "Ticket is closed as duplicate"),
        CLOSED("3", "Ticket is closed"),
        ASSIGNED_TECH("6", "Ticket is assigned to a tech"),
        NOT_UPDATED("2", "Ticket has not been updated for 30 minutes or more"),
        DUE_DATE_CHANGED("12", "Ticket due date has changed"),
        OVERDUE("1", "Ticket becomes overdue"),
        TAG_ADDED("13", "Tag is added to a ticket"),
        NEW_REPLY("4", "A new reply is added to a ticket"),
        SUBMITTER_REPLY("9", "A new reply from ticket-submitter"),
        CUSTOM_FIELD_EDITED("11", "A custom field has been edited in a ticket"),
        TRIGGERED_BY_RULE("-1", "Triggered by another rule");

        private final String value;
        private final String displayText;

        WhenThisRuleHappen(String value, String displayText) {
            this.value = value;
            this.displayText = displayText;
        }

        public String getValue() { return value; }
        public String getDisplayText() { return displayText; }
    }
    public enum ToDoAction {
        ADD_RELATED_ASSET("AddAssetAction", "Add related asset to the ticket..."),
        ADD_REPLY("AddReplyAction", "Add reply to the ticket..."),
        ADD_TIME_SPENT("AddTimeSpentAction", "Add X minutes to 'Time Spent'..."),
        ADD_REMOVE_TAG("AddTagAction", "Add/remove a tag..."),
        ADD_REMOVE_SUBSCRIBER("AddSubscriberAction", "Add/remove subscriber..."),
        ASSIGN_LEAST_BUSY("AssignToLeastBusyAction", "Assign to least busy technician..."),
        ASSIGN_TO("AssignAction", "Assign to..."),
        ASSIGN_ROUND_ROBIN("AssignRoundRobinAction", "Assign using Round Robin..."),
        ABORT_EMAILS("AbortEmailAction", "Cancel further email notifications for this event..."),
        CLONE_TICKET("CloneTicketAction", "Clone the ticket..."),
        CLOSE_TICKET("CloseTicketAction", "Close the ticket..."),
        CREATE_SUBTICKET("AddSubticketAction", "Create subticket..."),
        DELAY("DelayAction", "Delay..."),
        DELETE_TICKET("DeleteTicketAction", "Delete ticket..."),
        MARK_TECH_ONLY("MarkLatestCommentAsTech", "Mark latest reply 'for techs only'..."),
        MARK_UPD_FOR_TECH("MarkUpdForTech", "Mark the ticket as 'upd FOR tech'..."),
        MARK_ANSWERED("MarkAnswered", "Mark the ticket as answered..."),
        MARK_UNANSWERED("MarkUnanswered", "Mark the ticket as unanswered..."),
        MARK_SPAM("MarkSpamAction", "Mark ticket as spam..."),
        MERGE_PREVIOUS("MergePreviousAction", "Merge with previous ticket from this user..."),
        REMOVE_ALL_SUBSCRIBERS("RemoveSubsAction", "Remove all subscribers..."),
        REMOVE_ALL_TAGS("RemoveTagsAction", "Remove all tags..."),
        REOPEN_TICKET("ReopenTicketAction", "Reopen the ticket..."),
        NOTIFY_ADMINS("NotifyAdminsAction", "Send email to admins..."),
        NOTIFY_TECHS("NotifyTechsAction", "Send email to all techs in the ticket category..."),
        NOTIFY_SUBSCRIBERS("NotifySubscribersAction", "Send email to all ticket subscribers..."),
        NOTIFY_ASSIGNEE("NotifyAssignee", "Send email to assigned tech..."),
        NOTIFY_LAST_REPLIER("NotifyLastReplier", "Send email to last replier..."),
        SEND_EMAIL_TO_USER("SendEmailToUser", "Send email to particular user(s)..."),
        NOTIFY_SUBMITTER("NotifySubmitter", "Send email to ticket submitter..."),
        NOTIFY_MANAGERS("NotifyManager", "Send email to ticket submitter's manager(s)..."),
        NOTIFY_DEPARTMENT("NotifyDepartmentAction", "Send email to users in a department..."),
        HTTP_REQUEST("HttpRequestAction", "Send HTTP request..."),
        SET_CUSTOM_FIELD("CustomFieldAction", "Set custom field value..."),
        SET_DUE_DATE("SetDueDateAction", "Set due date..."),
        SET_START_DATE("SetStartDateAction", "Set start date..."),
        SET_CATEGORY("SetCategoryAction", "Set ticket category..."),
        SET_PRIORITY("SetPriorityAction", "Set ticket priority..."),
        SET_STATUS("SetStatusAction", "Set ticket status..."),
        SET_SUBJECT("SetSubjectAction", "Set ticket subject..."),
        SET_SUBMITTER("SetSubmitterAction", "Set ticket submitter..."),
        STOP_PROCESSING_RULES("AbortFurtherRules", "Stop processing more rules..."),
        TRIGGER_ANOTHER_RULE("TriggerAnotherRule", "Trigger another automation rule..."),
        TRIGGER_PARENT_RULE("TriggerAnotherRuleOnParent", "Trigger another rule on PARENT ticket..."),
        UNSHARE_TICKET("UnshareAction", "Unshare a shared ticket...");

        private final String value;
        private final String displayText;

        ToDoAction(String value, String displayText) {
            this.value = value;
            this.displayText = displayText;
        }

        public String getValue() {
            return value;
        }

        public String getDisplayText() {
            return displayText;
        }

        public static ToDoAction fromValue(String value) {
            for (ToDoAction action : values()) {
                if (action.value.equals(value)) {
                    return action;
                }
            }
            throw new IllegalArgumentException("Invalid AutomationAction value: " + value);
        }
    }
    public enum AssigneeToType {
        NO_ONE("0", "No one (remove current assignee)"),
        CURRENT_USER("-2", "Current user who's triggered the rule (if has permissions)"),
        ADMIN("1", "admin"),
        CLIENT("2", "client"),
        SAMAR_SAEED("556", "SAMAR SAEED");

        private final String value;
        private final String displayText;

        AssigneeToType(String value, String displayText) {
            this.value = value;
            this.displayText = displayText;
        }

        public String getValue() {
            return value;
        }

        public String getDisplayText() {
            return displayText;
        }

        public static AssigneeToType fromValue(String value) {
            for (AssigneeToType type : values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid AssigneeToType value: " + value);
        }
    }

    public enum ConditionType {
        CURRENT_DATE_BETWEEN("CurrentDateBetweenCondition", "Current date is between..."),
        DAY_OFF("DayOffCondition", "Current day is public holiday or weekend..."),
        DAY_OF_WEEK("DayOfWeekCondition", "Current day of week is..."),
        CURRENT_TIME_BETWEEN("CurrentTimeBetweenCondition", "Current time is between..."),
        CUSTOM_FIELD("CustomFieldCondition", "Custom field equals..."),
        UPDATED_CUSTOM_FIELD("UpdatedCustomFieldCondition", "Custom field was updated and the field is..."),
        LATEST_COMMENT_NON_SUB("LatestCommentFromNonSubCondition", "Latest reply came from a non-subscriber..."),
        LATEST_COMMENT_CONTAINS("LatestCommentContainsCondition", "Latest reply contains..."),
        LATEST_RESPONDER_EMAIL_CONTAINS("LatestResponderEmailContainsCondition", "Latest responder's email contains..."),
        ADDED_TAG("AddedTagCondition", "Tag has been added to ticket and the tag is..."),
        TECH_IS_VIEWING("TechIsViewingCondition", "Technician is looking at the ticket ..."),
        BODY_CONTAINS("BodyContainsCondition", "Ticket body contains..."),
        CATEGORY("CategoryCondition", "Ticket category or section is..."),
        COMPANY("CompanyCondition", "Ticket comes from company..."),
        DEPARTMENT("DepartmentCondition", "Ticket comes from department..."),
        LOCATION("LocationCondition", "Ticket comes from location..."),
        TO_EMAIL_CONTAINS("ToEmailContainsCondition", "Ticket comes via email and the TO field contains..."),
        ORIGIN("OriginCondition", "Ticket comes via..."),
        DUE_DATE("DueDateCondition", "Ticket due date is set"),
        TAG("TagCondition", "Ticket has tag..."),
        NO_REPLIES("NoRepliesCondition", "Ticket has zero replies..."),
        LOGGED_BY("LoggedByCondition", "Ticket is 'on-behalf', logged by technician X..."),
        TICKET_UNANSWERED("TicketUnansweredCondition", "Ticket is 'unanswered'"),
        UPDATED_BY_CUSTOMER("TicketUpdByCustomerCondition", "Ticket is 'updated by customer'"),
        UPDATED_BY_TECH("TicketUpdByTechCondition", "Ticket is 'updated by tech'"),
        UPDATED_FOR_TECH("TicketUpdForTechCondition", "Ticket is 'updated for tech'"),
        ASSIGNED_TO_DISABLED("AssignedToDisabledCondition", "Ticket is assigned to a deactivated tech..."),
        ASSIGNED_TO("AssignedToCondition", "Ticket is assigned to..."),
        OVERDUE("OverdueCondition", "Ticket is overdue by..."),
        TICKET_UNASSIGNED("TicketUnassignedCondition", "Ticket is unassigned"),
        PRIORITY("PriorityCondition", "Ticket priority is..."),
        STATUS("StatusCondition", "Ticket status is..."),
        SUBJECT_CONTAINS("SubjectContainsCondition", "Ticket subject contains or equals..."),
        EMAIL_CONTAINS("EmailContainsCondition", "Ticket submitter email contains..."),
        USER_LOOKUP_CONTAINS("UserLookupContainsCondition", "Ticket submitter's 'user-lookup' data contains..."),
        CREATION_DATE("CreationDateCondition", "Ticket was created more than X time ago..."),
        NOT_UPDATED("NotUpdatedCondition", "Ticket was not updated for..."),
        BEFORE_OVERDUE("BeforeOverdueCondition", "Ticket will be overdue in X hours or less..."),
        ASSIGNEE_OUT_OF_OFFICE("AssigneeOutOfOfficeCondition", "Ticket's assigned tech is 'out of office'");
        private static int conditionRowCounter = 2;
        private final String value;
        private final String displayText;

        ConditionType(String value, String displayText) {
            this.value = value;
            this.displayText = displayText;
        }

        public String getValue() {
            return value;
        }

        public String getDisplayText() {
            return displayText;
        }

        public static ConditionType fromValue(String value) {
            for (ConditionType condition : values()) {
                if (condition.value.equals(value)) {
                    return condition;
                }
            }
            throw new IllegalArgumentException("Invalid ConditionType value: " + value);
        }
    }
    private final By NewRuleNameLocator= By.xpath("//*[@id=\"name\"]");
    private final By WhenThisHappenLocator=By.xpath("//*[@id=\"TriggerType\"]");
    private final By SaveButtonLocator=By.xpath("//*[@id=\"rule-form\"]/div[1]/div[4]/input[5]");
    private final By AddDoThisLocator=By.xpath("//*[@id=\"btnAddAction\"]");
    private final By ToDoSelectorLocator=By.xpath("//*[@id=\"ddlActions\"]");
    private final By AssignToTypeLocator=By.xpath("//*[@id=\"actions\"]/tbody/tr[2]/td[1]/select");
    private final By MessegeErrorLocator=By.xpath("//*[@class=\"error\"]");
    private final By AddConditionButtonLocator=By.xpath("//*[@id=\"btnAddCondition\"]");
    private static int conditionRowCounter=2;
    BrowserActions browserActions;
    Assertion assertion;
    public CreateRulePage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public CreateRulePage EnterNewRuleName(String RuleName){
        browserActions.type(NewRuleNameLocator,RuleName);
        return new CreateRulePage(browserActions.getDriver());

    }
    public CreateRulePage ClickInAddNewConditionButton(){
        browserActions.click(AddConditionButtonLocator);
        return  new CreateRulePage(browserActions.getDriver());
    }
    public CreateRulePage selectConditionValueForTag(String valueToSelect) {
        // Locate the dropdown using the provided XPath
        //By conditionValueDropdownLocator = By.xpath("//*[@id=\"conditions\"]/tbody/tr[2]/td[1]/select[2]");
        By conditionValueDropdownLocator = By.xpath(String.format("//*[@id='conditions']/tbody/tr[%d]/td[1]/select[2]", conditionRowCounter));

        WebElement dropdown = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10))
                .withMessage("Condition value dropdown failed to appear within " + 10 + " seconds")
                .until(driver -> {
                    WebElement el = driver.findElement(conditionValueDropdownLocator);
                    return el.isDisplayed() && el.isEnabled() ? el : null;
                });

        new Select(dropdown).selectByVisibleText(valueToSelect);
        conditionRowCounter++;

        return  new CreateRulePage(browserActions.getDriver());
    }
    public List<String>GetMessageValidationError(){
        List<WebElement>Messages=browserActions.getDriver().findElements(MessegeErrorLocator);
        List<String> errorTexts = new ArrayList<>();

        for (WebElement message : Messages) {
            if (message.isDisplayed() && !message.getText().isEmpty()) {
                errorTexts.add(message.getText());
            }
        }

        return errorTexts;

    }
    public CreateRulePage selectConditionType(ConditionType conditionType) {
        WebElement dropdown = browserActions.findElement(By.id("ddlConditions"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(conditionType.getDisplayText());
        return new CreateRulePage(browserActions.getDriver());
    }
    public CreateRulePage selectWhenThisHappen(WhenThisRuleHappen triggerType) {
        WebElement dropdown = browserActions.findElement(WhenThisHappenLocator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(triggerType.getDisplayText());
        return new CreateRulePage(browserActions.getDriver());

    }
    public CreateRulePage selectToDoType(ToDoAction triggerType) {
        WebElement dropdown = browserActions.findElement(ToDoSelectorLocator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(triggerType.getDisplayText());
        return new CreateRulePage(browserActions.getDriver());

    }
    public CreateRulePage AssignToType(AssigneeToType triggerType) {
        WebDriverWait wait = new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10));

        // Wait for dropdown to be visible and interactable
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(AssignToTypeLocator));

        // Additional wait for element to be clickable (optional but recommended)
        wait.until(ExpectedConditions.elementToBeClickable(AssignToTypeLocator));
        //WebElement dropdown = browserActions.findElement(AssignToTypeLocator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(triggerType.getDisplayText());
        return new CreateRulePage(browserActions.getDriver());

    }
    public CreateRulePage ClickOnAddButtonToDoThis(){
        browserActions.click(AddDoThisLocator);
        return new CreateRulePage(browserActions.getDriver());
    }
    public AutomationRulePage ClickOnSaveButton(){
        browserActions.click(SaveButtonLocator);
        return new AutomationRulePage(browserActions.getDriver());
    }
}
