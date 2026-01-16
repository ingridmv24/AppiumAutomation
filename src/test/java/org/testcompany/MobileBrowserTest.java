package org.testcompany;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BrowserBaseTest {

    @Test
    public void browserTest(){
        //Selenium
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.className("navbar-toggler-icon")).click();
        driver.findElement(By.cssSelector("a[routerlink='/products']")).click();

        //scroll down in web mode
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)","");
        String text = driver.findElement(By.cssSelector("a[href$='products/3']")).getText();
        Assert.assertEquals(text,"Devops");
    }
}
