package org.testcompany;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testcompany.pageObjects.android.CartPage;
import org.testcompany.pageObjects.android.FormPage;
import org.testcompany.pageObjects.android.ProductCataloge;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Set;

public class eCommerce_tc_4_hybrid extends BaseTest {
    @Test
    public void FillForm() throws InterruptedException
    {
        formPage.setNameField("Ingrid Munera");
        formPage.setGender("female");
        //Scroll until Colombia text and select Colombia country
        formPage.setCountrySelection("Colombia");
        //Click on Let's Shop (submit button)
        ProductCataloge productCataloge = formPage.submitForm();
        //add two product to the cart
        productCataloge.addItemTocartByIndex(0);
        productCataloge.addItemTocartByIndex(0);

        //click on Cart icon
        CartPage cartPage = productCataloge.goToCartPage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
/*        wait.until(ExpectedConditions.attributeContains(
                (By.id("com.androidsample.generalstore:id/toolbar_title")),
                "text",
                "Cart"
        ));*/

/*        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("com.androidsample.generalstore:id/toolbar_title"),
                "Cart"
        ));*/

        //extract prices from the Cart page
        double totalSum = cartPage.getProductSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();

        Assert.assertEquals(totalSum, displayFormattedSum);

        //long click on terms of conditions text
        WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(ele);

        String conditionsTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(conditionsTitle, "Terms Of Conditions");

        //close pop up
        driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']")).click();

        //click on send me emails check box
        driver.findElement(By.xpath("//android.widget.CheckBox[contains(@text,'future')]")).click();

        //click on visit website to complete purchase
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(8000);

        //Handle hybrid app - Google page
        Set<String> contexts = driver.getContextHandles();
        for(String contextName : contexts){
            System.out.println(contextName);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement((By.name("q"))).sendKeys("automation testing");
        driver.findElement((By.name("q"))).sendKeys(Keys.ENTER);

        driver.pressKey(new KeyEvent(AndroidKey.BACK)); //return to native device
        driver.context("NATIVE_APP");
    }
}
