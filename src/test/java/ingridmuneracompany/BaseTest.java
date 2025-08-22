package ingridmuneracompany;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void ConfigureAppium() throws URISyntaxException, MalformedURLException {
        //Code to start the servet
        //AndroidDriver , IOSDriver
        //Appium code -> Appium Sever -> Mobile //Se crea codigo en Appium y se envia a servidor de Appium que lo interpreta en acciones en un dispositivo mobile

        //Start appium servet automatically
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\ingrid.munera\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        //Se crea objeto de la clase AndroidDriver que espera dos argumentos.
        UiAutomator2Options options = new UiAutomator2Options(); //con esta clase se especifica que tipo de dispositivo android se va utilizar
        options.setDeviceName("IngridPhone");
        //options.setApp("C:\\Users\\ingrid.munera\\AndroidStudioProjects\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
        options.setApp("C:\\Users\\ingrid.munera\\AndroidStudioProjects\\Appium\\src\\test\\java\\resources\\General-Store.apk");
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds((10))));
    }

    //Long click injecting Javascript in the mobile app
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

    @AfterClass
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}
