package org.testcompany;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testcompany.TestUtils.BaseTest;
import org.testcompany.pageObjects.android.CartPage;
import org.testcompany.pageObjects.android.ProductCataloge;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class eCommerce_tc_4_hybrid extends BaseTest {
    @BeforeMethod
    public void preSetup()
    {
        formPage.setActivity();
    }

    @AfterMethod
    public void afterMethod() {
        try {
            driver.context("NATIVE_APP");
        } catch (Exception e) {
            System.out.println("Already in NATIVE_APP");
        }
    }

    @Test(dataProvider = "getData")
    public void FillForm(HashMap<String,String> input) throws InterruptedException
    {
        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        //Scroll until Colombia text and select Colombia country
        formPage.setCountrySelection(input.get("country"));
        //Click on Let's Shop (submit button)
        ProductCataloge productCataloge = formPage.submitForm();
        //add two product to the cart
        productCataloge.addItemTocartByIndex(0);
        productCataloge.addItemTocartByIndex(0);

        //click on Cart icon
        CartPage cartPage = productCataloge.goToCartPage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        //extract prices from the Cart page
        double totalSum = cartPage.getProductSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum, displayFormattedSum);

        //long click on terms of conditions text
        cartPage.acceptTermsConditions();

        //click on visit website to complete purchase
        cartPage.submitOrder();
        Thread.sleep(8000);

        //Handle hybrid app - Google page
        // Espera explícita hasta que el WEBVIEW esté disponible
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait2.until(d -> driver.getContextHandles().stream().anyMatch(c -> c.contains("WEBVIEW")));

        // Imprime contextos
        Set<String> contexts = driver.getContextHandles();
        for(String contextName : contexts){
            System.out.println(contextName);
        }

        // Cambia al WEBVIEW
        driver.context("WEBVIEW_com.androidsample.generalstore");

        // Espera que la página de Google cargue completamente
        wait2.until(d -> {
            try {
                return driver.findElement(By.name("q")).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });

        driver.findElement(By.name("q")).sendKeys("automation testing");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        // Regresa a la app nativa
        Thread.sleep(3000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>>	data = getJsonData(System.getProperty("user.dir")+"/src/main/java/org/testcompany/testData/eCommerce.json");
        //return new Object[][] {{"ingrid munera","female", "Colombia" }, {"andres b","male", "Colombia" }};
        return new Object[][] { {data.get(0)},{data.get(1)}};
    }
}
