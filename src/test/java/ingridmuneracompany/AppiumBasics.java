package ingridmuneracompany;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class AppiumBasics extends BaseTest {
    @Test
    public void WifiSettingsName() throws URISyntaxException, MalformedURLException {
        //Actual automation
        //Locators: Xpath,id,accessibilityId, classname, androidUIAutomator

        //ConfigureAppium(); en lugar de llamar a este metodo, con la anotacion @BeforeClass lo hace automaticamente
        //sintaxis xpath: //tagName[@attribute='value']
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle,"WiFi settings");

        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Ingrid Wifi");
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        //set Wifi name
    }
}
