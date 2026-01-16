package org.testcompany.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AppiumUtils {

    AndroidDriver driver;
    public AppiumUtils()
    {
        this.driver = driver;
    }
    public Double getFormattedAmount(String amount)
    {
        String removeDollar = amount.substring(1); //devolver el string empezando desde el index 1
        Double price = Double.parseDouble(removeDollar); //convert string to double
        return price;
    }

    public void waitForElementToAppear(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }
}
