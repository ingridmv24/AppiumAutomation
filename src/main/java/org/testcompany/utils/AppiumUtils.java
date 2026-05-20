package org.testcompany.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumUtils {
    public AppiumDriverLocalService service;

    public Double getFormattedAmount(String amount)
    {
        String removeDollar = amount.substring(1); //devolver el string empezando desde el index 1
        Double price = Double.parseDouble(removeDollar); //convert string to double
        return price;
    }

    //Parse the json file to use test data
    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
    //System.getProperty("user.dir")+"//src//main//java//org//testcompany//testData//eCommerce.json"
        // conver json file content to json string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });

        return data;
    }

    //Start appium servet automatically
    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port)
    {
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\ingri\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
    }

    public void waitForElementToAppear(WebElement ele, AppiumDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException
    {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
        //1. capture and place in folder //2. extent report pick file and attach to report
    }
}
