package ingridmuneracompany;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwipeDemo extends BaseTest{
    @Test
    public void SwipeDemoTest() throws InterruptedException {
        driver.findElement(new AppiumBy.ByAccessibilityId("Views")).click();
        driver.findElement(new AppiumBy.ByAccessibilityId("Gallery")).click();
        driver.findElement(new By.ByXPath("//android.widget.TextView[@text='1. Photos']")).click();
        WebElement firstImage = driver.findElement(new By.ByXPath("//android.widget.ImageView[1]"));
        Assert.assertEquals(driver.findElement(new By.ByXPath("//android.widget.ImageView[1]")).getAttribute("focusable"),"true");

        //Swipe action
        swipeAction(firstImage,"left");
        //check focusable image is false
        Assert.assertEquals(driver.findElement(new By.ByXPath("//android.widget.ImageView[1]")).getAttribute("focusable"),"false");
    }
}

