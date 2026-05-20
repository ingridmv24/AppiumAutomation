package org.testcompany.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testcompany.pageObjects.android.FormPage;
import org.testcompany.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest extends AppiumUtils {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass
    public void ConfigureAppium() throws IOException
    {
        //Code to start the servet
        //AndroidDriver , IOSDriver
        //Appium code -> Appium Sever -> Mobile: Code is generated in Appium and sent to the Appium server, which interprets it as actions on a mobile devic

        Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//testcompany//resources//data.properties");
        prop.load(fis);
        String ipAddress = prop.getProperty("idAddress");
        String port = prop.getProperty("port");

        //Start appium servet automatically
        service = startAppiumServer(ipAddress,Integer.parseInt(port));

        //An object of the AndroidDriver class is created that takes two arguments.
        UiAutomator2Options options = new UiAutomator2Options(); //con esta clase se especifica que tipo de dispositivo android se va utilizar
        options.setDeviceName(prop.getProperty("AndroidDeviceName"));
        options.setChromedriverExecutable("C://chromedriver//chromedriver-win64//chromedriver.exe");

        //options.setApp("C:\\Users\\ingrid.munera\\AndroidStudioProjects\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
        options.setApp(System.getProperty("user.dir")+"//src//test//resources//General-Store.apk");
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds((10))));
        formPage = new FormPage(driver);
    }



    @AfterClass
    public void tearDown()
    {
        driver.quit();
        service.stop();
    }
}

