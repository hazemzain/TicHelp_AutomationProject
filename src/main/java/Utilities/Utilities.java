package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;


public class Utilities {
    public static String  generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public static int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("");
        }

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static void uploadFile(String filePath, WebDriver driver, By fileInputLocator) {
        try {
            // Locate the input[type='file'] element
            WebElement fileInput = driver.findElement(fileInputLocator);

            // Provide the file path to the input element
            fileInput.sendKeys(filePath);

            System.out.println("File uploaded successfully: " + filePath);
        } catch (Exception e) {
            System.err.println("Failed to upload file: " + e.getMessage());
        }
    }
}
