package Tests;

import DriverManager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestBase {
    protected WebDriver driver;
    DriverManager driverManager;
    protected String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";


    @BeforeClass
    public void setup() {
        driverManager = new DriverManager();
        driver = driverManager.getDriver();

    }

    @AfterClass
    public void terminateDriver() {
        System.out.println("Quitting WebDriver in TestBase...");
        if (driverManager != null) {
            driverManager.quitDriver();
        }
    }
}