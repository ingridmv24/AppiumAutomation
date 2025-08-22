package ingridmuneracompany;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class MiscellaneousAppiumActions extends BaseTest {
    @Test
    public void Miscellaneous() throws URISyntaxException, MalformedURLException {
        //App package & App activity
        //start the test execution from specific page
        Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));

//        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        //Rotate the device to landscape mode
        DeviceRotation landscape = new DeviceRotation(0,0,90); //object of the class DeviceRotation
        driver.rotate(landscape);

        driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle,"WiFi settings");

        //Copy paste
        //copy to clipboard - paste it clipboard
        driver.setClipboardText("Ingrid Wifi"); //from somewhere we're copy this text
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());

        //press enter
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        //click on OK button
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        //press back button in the phone
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //press home button in the phone
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

        //last lines before copy paste code
        //driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Ingrid Wifi"); //writting text

        //set Wifi name
    }
}
