package Pages.LoginPage;

import Assertions.Assertion;
import BrowserActions.BrowserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {

    public enum LanguageOption {
        DEFAULT(""),
        ARABIC("ar-SA"),
        ENGLISH("en-US");

        private final String value;

        LanguageOption(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    private final By UserNameLocator= By.xpath("//*[@id=\"Username\"]");
    private final By EmailLocator= By.xpath("//*[@id=\"Email\"]");
    private final By PasswordLocator= By.xpath("//*[@id=\"Password\"]");
    private final By ConfirmationPasswordLocator= By.xpath("//*[@id=\"PasswordConfirm\"]");
    private final By FirstNameLocator= By.xpath("//*[@id=\"FirstName\"]");
    private final By LastNameLocator= By.xpath("//*[@id=\"LastName\"]");
    private final By PhoneNumberLocator= By.xpath("//*[@id=\"Phone\"]");
    private final By LocationLocator= By.xpath("//*[@id=\"Location\"]");
    private final By LanguageLocator= By.xpath("//*[@id=\"llLang\"]");
    private final By RegisterButtonLocator= By.xpath("//*[@id=\"user-form\"]/table/tbody/tr[11]/td[2]/input");

    BrowserActions browserActions;
    Assertion assertion;
    public RegisterPage(WebDriver driver) {
        browserActions = new BrowserActions(driver);
        assertion = new Assertion(driver);
    }
    public RegisterPage EnterUserName(String UserName){
        browserActions.type(UserNameLocator,UserName);
        return new RegisterPage(browserActions.getDriver());
    }
    public RegisterPage EnterEmail(String Email){
        browserActions.type(EmailLocator,Email);
        return new RegisterPage(browserActions.getDriver());
    }
    public RegisterPage EnterPassword(String Password){
        browserActions.type(PasswordLocator,Password);
        return new RegisterPage(browserActions.getDriver());
    }
    public RegisterPage EnterConfirmationPassword(String ConfirmationPassword){
        browserActions.type(ConfirmationPasswordLocator,ConfirmationPassword);
        return new RegisterPage(browserActions.getDriver());
    }

    public RegisterPage EnterFirstName(String FirstName){
        browserActions.type(FirstNameLocator,FirstName);
        return new RegisterPage(browserActions.getDriver());
    }
    public RegisterPage EnterLastName(String LastName){
        browserActions.type(LastNameLocator,LastName);
        return new RegisterPage(browserActions.getDriver());
    }
    public RegisterPage EnterPhoneNumber(String PhoneNumber){
        browserActions.type(PhoneNumberLocator,PhoneNumber);
        return new RegisterPage(browserActions.getDriver());
    }
    public RegisterPage selectLanguage(LanguageOption language) {
        WebElement dropdownElement = browserActions.findElement(LanguageLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(language.getValue());
        return new RegisterPage(browserActions.getDriver());

    }

    public  void ClickInRegisterButton(){
        browserActions.click(RegisterButtonLocator);
    }

    public String GetMessageError(){
        return  browserActions.findElement(By.xpath("//*[@id='Email-error']")).getText();

    }
    public String GetMessageErrorForConfirmationPassword(){
        return  browserActions.findElement(By.xpath("//*[@id=\"PasswordConfirm-error\"]")).getText();

    }
    public String GetMessageErrorForPassword(){
        return  browserActions.findElement(By.xpath("//*[@id=\"Password-error\"]")).getText();

    }
public String GetMessageForSuccessRegister(){
    //*[@id="user-form"]/table/tbody/tr[2]/td/span
    return  browserActions.findElement(By.xpath("//*[@id=\"user-form\"]/table/tbody/tr[2]/td/span")).getText();

}

}
