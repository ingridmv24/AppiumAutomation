package ingridmuneracompany;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class ScrollDemo extends BaseTest{
    @Test
    public void ScrollDemoTest() throws InterruptedException {
        driver.findElement(new AppiumBy.ByAccessibilityId("Views")).click();

        //Where to scroll is known prior
        //this locator androidUIAutomator -> means you're directly using google engine to find that element
        // androidUIAutomator -> rely in google engine script
        // to scroll you have to create an object for the class (new UiScrollable) UiScrollable and that class expects a selector (new UiSelector) as an argument
        //inside the UiScrollable class there is a method .scrollIntoView when the text you want to scroll is passed
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

        //No prior idea
        scrollToEndAction();
    }
}

