package HelperClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmailSettingHelperClass {
    String tempEmail;
    String mainWindow;
    String tempMailWindow;
    WebDriver _driver;
public EmailSettingHelperClass(WebDriver driver){
        _driver=driver;

}
    public String getTempEmail() {
        // Store the main window handle
        mainWindow = _driver.getWindowHandle();

        // Open a new tab and navigate to Temp Mail
        _driver.switchTo().newWindow(WindowType.TAB);
        _driver.get("https://temp-mail.org/");

        // Wait for the email field to load
        WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(10));
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mail")));
        tempEmail = emailElement.getAttribute("value");

        System.out.println("Generated Temp Email: " + tempEmail);


        // Store the Temp Mail tab handle
        Set<String> handles = _driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                tempMailWindow = handle;
                break;
            }
        }

        // Switch back to the main tab
        _driver.switchTo().window(mainWindow);
        return tempEmail;
    }

    public String getTempEmailUsingGmail() {
    String Email="gbgzain@gmail.com";
        // Store the main window handle
        mainWindow = _driver.getWindowHandle();

        // Open a new tab and navigate to Temp Mail
        _driver.switchTo().newWindow(WindowType.TAB);
        _driver.get("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&osid=1&passive=1209600&service=mail&ifkv=AVdkyDmrfq3IJDM82zxBe0Drc6y4z8TXdS53db7EDvai40oJAyOEBbulpnEONJlq40ftmF3xFm6Nag&ddm=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

        // Wait for the email field to load
        WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(30));
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"identifierId\"]")));
         emailElement.sendKeys("gbgzain@gmail.com");
        //*[@id="identifierNext"]/div/button/div[3]
        WebElement NextElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"identifierNext\"]/div/button")));
        NextElement.click();
        //*[@id="password"]/div[1]/div/div[1]/input
        // Wait for the email field to load
        WebElement PasswordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")));
        PasswordElement.sendKeys("mmm@1999");
        WebElement Next2Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"passwordNext\"]/div/button")));
        Next2Element.click();



        // Store the Temp Mail tab handle
        Set<String> handles = _driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                tempMailWindow = handle;
                break;
            }
        }

        // Switch back to the main tab
        _driver.switchTo().window(mainWindow);
        return Email;
    }

    ////td[@class='c2']//span/span

    public Map<String, String> getFirstEmailDetailsUsingGmail() throws InterruptedException {
        // Switch to the Gmail tab
        _driver.switchTo().window(tempMailWindow);

        WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(50));
        Map<String, String> emailDetails = new HashMap<>();

        try {
            Thread.sleep(Duration.ofSeconds(30));
            // Wait for the inbox to load and get all email rows
            List<WebElement> emailRows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//tbody/tr[@role='row']"))); // Locate all email rows

            // Check if the inbox contains any emails
            if (emailRows.isEmpty()) {
                System.out.println("No emails found in the inbox.");
                return null;
            }

            WebDriverWait wait2 = new WebDriverWait(_driver, Duration.ofSeconds(30));
            WebElement firstEmailRow = wait2.until(ExpectedConditions.elementToBeClickable(emailRows.get(0)));
            firstEmailRow.click();



            // Wait for the email content to load and extract details
            WebElement senderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[@class='c2']//span/span"))); // XPath for sender
            String sender = senderElement.getAttribute("name");
            emailDetails.put("Sender", sender);

            WebElement subjectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='ha']/h2"))); // XPath for subject
            String subject = subjectElement.getText();
            emailDetails.put("Subject", subject);

            WebElement emailBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@style, 'font-family:Helvetica')]"))); // XPath for email body
            String emailBody = emailBodyElement.getText();
            emailDetails.put("Body", emailBody);


            System.out.println("Email Details: " + emailDetails);

            WebElement linkElement = _driver.findElement(By.xpath("//div[contains(@style, 'font-family:Helvetica')]//a"));
            String loginLink = linkElement.getAttribute("href");
            emailDetails.put("LinkInBody", loginLink);

            System.out.println("Login Link: " + loginLink);
        } catch (Exception ex) {
            System.err.println("Failed to fetch email: " + ex.getMessage());
            return null;
        }
        _driver.navigate().back();
        // Switch back to the main tab
        _driver.switchTo().window(mainWindow);
        return emailDetails;
    }



    public Map<String, String> getReceivedEmail() throws InterruptedException {
        // Switch to the Temp Mail tab
        _driver.switchTo().window(tempMailWindow);
        WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(50));


        try {
            // Wait for an email to arrive
            WebElement emailList = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"tm-body\"]/main/div[1]/div/div[2]/div[2]/div/div[1]/div/div[4]/ul/li[2]")));



        }catch (Exception ex){
            return null;

        }

        // Click the first email in the inbox
        Thread.sleep(500);
        //emailList.click();

        // Extract email details
        Map<String, String> emailDetails = new HashMap<>();

        // Wait for 'From' field and get text
        WebElement fromElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"tm-body\"]/main/div[1]/div/div[2]/div[2]/div/div[1]/div/div[4]/ul/li[2]/div[1]/a/span[3]")));
        String from = fromElement.getText();
        emailDetails.put("From", from);
//*[@id="tm-body"]/main/div[1]/div/div[2]/div[2]/div/div[1]/div/div[4]/ul/li[2]/div[1]/a/span[2]
        WebElement NameFromElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"tm-body\"]/main/div[1]/div/div[2]/div[2]/div/div[1]/div/div[4]/ul/li[2]/div[1]/a/span[2]")));
        String FromName = NameFromElement.getText();
        emailDetails.put("Name", FromName);
        // Wait for 'Subject' field and get text
        WebElement subjectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"tm-body\"]/main/div[1]/div/div[2]/div[2]/div/div[1]/div/div[4]/ul/li[2]/div[2]/span/a")));
        String subject = subjectElement.getText();
        emailDetails.put("Subject", subject);
        WebElement senderLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"tm-body\"]/main/div[1]/div/div[2]/div[2]/div/div[1]/div/div[4]/ul/li[2]/div[1]/a/span[2]")));
        String sender = senderLocator.getText();
        emailDetails.put("Sender", subject);
//*[@id="tm-body"]/main/div[1]/div/div[2]/div[2]/div/div[1]/div/div[4]/ul/li[2]/div[1]/a/span[2]
//        // Wait for email body and get text
//        WebElement emailBodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//div[contains(@class, 'email-text')]")));
//        String emailBody = emailBodyElement.getText();
//        emailDetails.put("Body", emailBody);

        // Print the extracted email details
        System.out.println("Received Email Details: " + emailDetails);

        // Switch back to the main tab
        _driver.switchTo().window(mainWindow);

        return emailDetails;
    }


}
