package ingridmuneracompany;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
public class eCommerce_tc_1 extends BaseTest {
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

        //Thread.sleep(3000);

        //Access to products page validation
        String firstProductName = driver.findElement(By.xpath("//android.widget.TextView[@text='Air Jordan 4 Retro']")).getText();
        Assert.assertEquals(firstProductName,"Air Jordan 4 Retro");

        //To extract toast message when the name is not filled
        //String toastMessage = driver.findElement(By.xpath("(//android.widget.android.widget.Toast)[1]")).getAttribute("name");
        //Assert.assertEquals(toastMessage,"Please enter your name");
    }
}
