package HelperClasses;

import org.openqa.selenium.WebElement;

public class AutomationRule {
    private String id;
    private String name;
    private boolean enabled;
    private WebElement cloneButton;
    private WebElement toggleSwitch;
    private WebElement DeleteSwitch;
    private  WebElement rowElement;



    public AutomationRule(String id, String name, boolean enabled, WebElement cloneButton, WebElement toggleSwitch,WebElement DeleteSwitch,WebElement rowElement) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.cloneButton = cloneButton;
        this.toggleSwitch = toggleSwitch;
        this.DeleteSwitch=DeleteSwitch;
        this.rowElement=rowElement;
    }

    // Add getter for the clone button
    public WebElement getCloneButton() {
        return cloneButton;
    }

    public WebElement getRowElement() {
        return rowElement;
    }
    public WebElement getDeleteButton() {
        return DeleteSwitch;
    }
    public WebElement getToggleSwitch() {
        return toggleSwitch;
    }
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isEnabled() { return enabled; }
}
