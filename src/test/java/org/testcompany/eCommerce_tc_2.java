package org.testcompany;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eCommerce_tc_2 extends BaseTest {
    @BeforeMethod
    public void preSetup(){
        //set screen to home page before running any test
        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent","com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
    }

    @Test
    public void FillForm_ErrorValidation() throws InterruptedException {
        //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ingrid Munera");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();

        //Scroll until Colombia text
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Colombia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Colombia']")).click();

        //Click on Let's shop button without entering the name
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test
    public void FillForm_PositiveFlow() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ingrid Munera");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();

        //Scroll until Colombia text
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Colombia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Colombia']")).click();

        //Click on Let's shop button entering the correct name
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1); //return number of xpatch in the screen, if it doesn't find any will retirn 0 which is less than 1 the assertrue is true and the test case will pass
    }
}


