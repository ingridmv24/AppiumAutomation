package org.testcompany.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions {
    AndroidDriver driver;
    public AndroidActions(AndroidDriver driver){

        this.driver = driver;
    }
    public void longPressAction(WebElement ele){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
                        "duration",2000));
    }

    //No prior idea. when you don't know if the element exists
    //hace como un scroll y luego valida si puede seguir haciendo scroll y devuelve tru
    //y cuando no puede seguir haciendo scroll hacia abajo devuelve false, y va ser false cuando llegue al final de la pagina web
    public void scrollToEndAction(){
        boolean canScrollMore;
        do {
            {
                canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "left", 100,
                        "top", 100,
                        "width", 200,
                        "height", 200,
                        "direction", "down",
                        "percent", 3.0
                ));
            }
        }while(canScrollMore);
    }

    //Scroll to specific piece
    public void scrollToText(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
    }

    //Swipe
    public void swipeAction(WebElement ele, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)ele).getId(), //cast with remote web element
                "direction",direction,
                "percent", 0.25
        ));
    }

    //Drag and Drop action
    public void dragAndDropAction(WebElement source, int coorditaneX, int coorditaneY){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", coorditaneX,
                "endY", coorditaneY
        ));
    }
}
