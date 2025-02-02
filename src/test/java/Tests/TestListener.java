package Tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private FileWriter writer;

    public TestListener() {
        try {
            // Generate log file with the current date
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            writer = new FileWriter("TestLog_" + date + ".log", true); // Append mode
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logToFile(String message) {
        try {
            writer.write(message + "\n");
            writer.flush(); // Ensure data is written immediately
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        logToFile("TEST STARTED: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logToFile("TEST PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logToFile("TEST FAILED: " + result.getName() + " | Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logToFile("TEST SKIPPED: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logToFile("======== TEST SUITE STARTED ========");
    }

    @Override
    public void onFinish(ITestContext context) {
        logToFile("======== TEST SUITE FINISHED ========");
        try {
            writer.close(); // Close the file writer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
