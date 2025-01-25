package Pages.AdminstrationPages.AutomationRulesPages;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import HelperClasses.AutomationRule;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AutomationRulePage {
    private final By CreateNewRuleLocator= By.xpath("//*[@id=\"content\"]/div[5]/div/p[1]/a");
    private final By RULE_ROWS = By.xpath("//table[@id='rules']//tr[starts-with(@id, 'rule_') or @data-name]");
    private final By ExpandXMLLocator=By.xpath("//*[@id=\"content\"]/div[5]/div/div/a");
    private final By SearchLocator=By.xpath("//*[@id=\"content\"]/div[5]/div/p[1]/input");
    BrowserActions browserActions;
    Assertion assertion;
    public AutomationRulePage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public CreateRulePage ClickOnCreateRuleButton(){
        browserActions.click(CreateNewRuleLocator);
        return  new CreateRulePage(browserActions.getDriver());
    }
    public List<AutomationRule>GetAllRules(){
        List<WebElement>AllRows=browserActions.getDriver().findElements(RULE_ROWS);
        List<AutomationRule>Rules=new ArrayList<>();
        for (WebElement row:AllRows){
            String id=row.getAttribute("id");
            String name = row.findElement(By.cssSelector("td:first-child a")).getText();
            boolean enabled = row.findElement(By.cssSelector("input[type='checkbox']")).isSelected();
             // Locate the clone button within the row
            WebElement cloneButton = row.findElement(By.cssSelector("td a.fa-copy[title='Clone rule']"));
            WebElement toggle = row.findElement(By.cssSelector("input[type='checkbox']"));
            WebElement Delete=row.findElement(By.cssSelector("a.delete[title='Delete'][href*='/Admin/DeleteRule/']"));
            Rules.add(new AutomationRule(id, name, enabled, cloneButton,toggle,Delete));
        }
        return Rules;
    }

    public AutomationRulePage DeleteRuleByName(String RuleName){
        boolean ruleExists;
        do {
            // Get FRESH list of rules every iteration
            List<AutomationRule> currentRules = GetAllRules();

            Optional<AutomationRule> targetRule = currentRules.stream()
                    .filter(rule -> rule.getName().equalsIgnoreCase(RuleName))
                    .findFirst();

            if (!targetRule.isPresent()) {
                break; // Exit when no more matching rules exist
            }

            // Store the ID before deletion for verification
            String targetId = targetRule.get().getId();

            // Get fresh delete button reference using the current row
            WebElement currentRow = browserActions.getDriver().findElement(By.id(targetId));
            WebElement deleteButton = currentRow.findElement(
                    By.cssSelector("a.delete[title='Delete'][href*='/Admin/DeleteRule/']")
            );

            // Scroll and click with JavaScript
            ((JavascriptExecutor) browserActions.getDriver())
                    .executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", deleteButton);

            ((JavascriptExecutor) browserActions.getDriver())
                    .executeScript("arguments[0].click();", deleteButton);

            // Handle confirmation dialog
            browserActions.acceptAlert();

            // Wait for rule to be removed from DOM using the stored ID
            new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id(targetId)));

            ruleExists = true;
        } while (ruleExists);

        return new AutomationRulePage(browserActions.getDriver());
//        List<AutomationRule> rules = GetAllRules();
//        List<AutomationRule> targetRules = rules.stream()
//                .filter(rule -> rule.getName().equalsIgnoreCase(RuleName))
//                .collect(Collectors.toList());
//
//        if (targetRules.isEmpty()) {
//            throw new NoSuchElementException("No rules found with name: " + RuleName);
//        }
//        targetRules.forEach(rule -> {
//
//            rule.getDeleteButton().click();
//            browserActions.acceptAlert();
//
//            // Wait for DOM to update
//            new WebDriverWait(browserActions.getDriver(), Duration.ofSeconds(5))
//                    .until(ExpectedConditions.invisibilityOf(rule.getDeleteButton()));
//
//        });
//
//
//        return new AutomationRulePage(browserActions.getDriver());
    }

    public AutomationRulePage SearchForRuleByName(String NameOfRule){
        browserActions.type(SearchLocator,NameOfRule);
        return new AutomationRulePage(browserActions.getDriver());

    }

    public AutomationRulePage  DisableAllRuleWhichIsEnabled(){
        List<AutomationRule> rules = GetAllRules();
        rules.stream()
                .filter(AutomationRule::isEnabled)  // Only process enabled rules
                .forEach(rule -> {

                    // Click the toggle switch
                    rule.getToggleSwitch().click();

                });

        return  new AutomationRulePage(browserActions.getDriver());

    }

    public int GetNumberOfEnableRule(){
        List<AutomationRule> rules = GetAllRules();
        return (int) rules.stream()
                .filter(AutomationRule::isEnabled)
                .count();

    }

    public AutomationRulePage  cloneRuleByName(String ruleName){
        // Get all rules with their clone buttons
        List<AutomationRule> rules = GetAllRules();

        // Find the first rule with matching name (case-insensitive)
        AutomationRule targetRule = rules.stream()
                .filter(rule -> rule.getName().equalsIgnoreCase(ruleName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Rule with name '" + ruleName + "' not found"));
        targetRule.getCloneButton().click();
        return  new AutomationRulePage(browserActions.getDriver());

    }
    public boolean isRulePresent(String ruleName) {
        return GetAllRules().stream()
                .anyMatch(rule -> rule.getName().equalsIgnoreCase(ruleName));
    }
    public int theNumberOfRulesWithTheSameName(String ruleName) {
        return (int) GetAllRules().stream()
                .filter(rule -> rule.getName().equalsIgnoreCase(ruleName))
                .count();
    }
    public int theNumberOfAllRules() {
        return (int) GetAllRules().stream().count();
    }
    public AutomationRulePage ClickInExpandXml(){
        browserActions.click(ExpandXMLLocator);
        return new AutomationRulePage(browserActions.getDriver());
    }
    public AutomationRulePage HandleAlertForDownloadXMLFile(){
        browserActions.acceptAlert();
        return new AutomationRulePage(browserActions.getDriver());
    }

}
