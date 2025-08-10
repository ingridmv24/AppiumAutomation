package ingridmuneracompany;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongPress extends BaseTest {
    @Test
    public void LongPressGesture() throws InterruptedException {
        driver.findElement(new AppiumBy.ByAccessibilityId("Views")).click();
        driver.findElement(new AppiumBy.ByAccessibilityId("Expandable Lists")).click();
        driver.findElement(new AppiumBy.ByAccessibilityId("1. Custom Adapter")).click();
        WebElement ele = driver.findElement(new By.ByXPath("//android.widget.TextView[@text='People Names']"));

        longPressAction(ele);

        String menuText = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(menuText,"Sample menu");
        Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
    }
}
