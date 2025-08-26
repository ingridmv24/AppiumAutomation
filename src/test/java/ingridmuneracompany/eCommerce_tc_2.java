package ingridmuneracompany;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class eCommerce_tc_2 extends BaseTest {
    @Test
    public void FillForm() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ingrid Munera");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();

        //Scroll until Colombia text
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Colombia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Colombia']")).click();

        //select Colombia country
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //scroll until Jordan 6 Rings product
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));

        //click on add ot cart Jordan 6 Rings product
       int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).size();    //le pido que me devuelva todos los productos que tengan ese resource-id

        for(int i = 0; i < productCount; i++ ){
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

            if (productName.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }

        //click on Cart icon
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //wait until the cart page is loaded - explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));

        //product added in the cart
        String lastPageProduct =  driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();

        Assert.assertEquals(lastPageProduct, "Jordan 6 Rings");
    }
}
