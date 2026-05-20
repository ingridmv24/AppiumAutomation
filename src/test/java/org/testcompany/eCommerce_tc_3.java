package org.testcompany;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testcompany.TestUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class eCommerce_tc_3 extends BaseTest {
    @Test
    public void FillForm() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ingrid Munera");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();

        //Scroll until Colombia text and select Colombia country
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Colombia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Colombia']")).click();

        //Click on Let's Shop button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //add two product to the cart
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        //driver.findElement(By.xpath(("//android.widget.TextView[@text='ADD TO CART'])[0]"))).click();
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

        //click on Cart icon
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
/*        wait.until(ExpectedConditions.attributeContains(
                (By.id("com.androidsample.generalstore:id/toolbar_title")),
                "text",
                "Cart"
        ));*/

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("com.androidsample.generalstore:id/toolbar_title"),
                "Cart"
        ));


        //extract prices from the Cart page
        //retornat la cantidad de lista de precios
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));

        int count = productPrices.size();
        double totalSum = 0;

        for(int i = 0; i < count; i++ ){
            String amountString = productPrices.get(i).getText();
            Double price = getFormattedAmount(amountString);

            //sum prices
            totalSum =  totalSum + price; //1st iteracion = 160.97 + 2nd iteracion = 120.0 = 280.97
        }
        System.out.println("Total products sum is: " + totalSum);

        String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double displayFormattedSum = getFormattedAmount(displaySum);

        Assert.assertEquals(totalSum, displayFormattedSum);

        //long click on terms of conditions text
        WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        //longPressAction(ele);

        String conditionsTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(conditionsTitle, "Terms Of Conditions");

        //close pop up
        driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']")).click();

        //click on send me emails check box
        driver.findElement(By.xpath("//android.widget.CheckBox[contains(@text,'future')]")).click();

        //click on visit website to complete purchase
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(8000);
    }
}
