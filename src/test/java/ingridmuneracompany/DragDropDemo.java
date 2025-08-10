package ingridmuneracompany;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragDropDemo extends BaseTest{
    @Test
    public void DragDropTest() throws InterruptedException {
        driver.findElement(new AppiumBy.ByAccessibilityId("Views")).click();
        driver.findElement(new AppiumBy.ByAccessibilityId("Drag and Drop")).click();
        WebElement source = driver.findElement(By.id(("io.appium.android.apis:id/drag_dot_1")));

        //Drag and Drop action
        dragAndDropAction(source,650,584);

        Thread.sleep(3000);
        String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result, "Dropped!");
    }
}

