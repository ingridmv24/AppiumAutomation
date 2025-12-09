package org.testcompany;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BrowserBaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void ConfigureAppium() throws URISyntaxException, MalformedURLException {
        //Code to start the servet
        //AndroidDriver , IOSDriver
        //Appium code -> Appium Sever -> Mobile //Se crea codigo en Appium y se envia a servidor de Appium que lo interpreta en acciones en un dispositivo mobile

        //Start appium servet automatically
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\ingri\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        //Se crea objeto de la clase AndroidDriver que espera dos argumentos.
        UiAutomator2Options options = new UiAutomator2Options(); //con esta clase se especifica que tipo de dispositivo android se va utilizar
        options.setDeviceName("IngridPhone");
        options.setChromedriverExecutable("C:\\chromedriver\\chromedriver-win64\\chromedriver.exe");

        options.setCapability("browserName","Chrome");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds((10))));
    }

    //Formater of dollar caracter
    public Double getFormattedAmmount(String amountString){
        String removeDollar = amountString.substring(1); //devolver el string empezando desde el idex 1
        Double price = Double.parseDouble(removeDollar); //convert string to double
        return price;

        //forma 2
        //Double price =  Double.parseDouble(amountString.substring(1));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}
