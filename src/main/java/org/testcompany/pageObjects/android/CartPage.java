package org.testcompany.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testcompany.utils.AndroidActions;
import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;
    public CartPage(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;
    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;
    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement terms;
    @AndroidFindBy(id="android:id/button1")
    private WebElement acceptButton;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;
    @AndroidFindBy(xpath="//android.widget.CheckBox[contains(@text,'future')]")
    private WebElement checkbox;

    public List<WebElement> getProductList()
    {
        return productList;
    }

    //extract prices from the Cart page
    public double getProductSum()
    {
        int count = productList.size();
        double totalSum = 0;

        for(int i = 0; i < count; i++ )
        {
            String amountString = productList.get(i).getText();
            Double price = getFormattedAmount(amountString);
            //sum prices
            totalSum =  totalSum + price; //1st iteracion = 160.97 + 2nd iteracion = 120.0 = 280.97
        }
        return totalSum;
    }

    //Formater of dollar caracter
    public Double getTotalAmountDisplayed()
    {
        return getFormattedAmount(totalAmount.getText());
    }

    public void acceptTermsConditions()
    {
        longPressAction(terms);
        acceptButton.click();
    }

    public Double getFormattedAmount(String amount)
    {
        String removeDollar = amount.substring(1); //devolver el string empezando desde el index 1
        Double price = Double.parseDouble(removeDollar); //convert string to double
        return price;
    }

    public void submitOrder()
    {
        checkbox.click();
        proceed.click();
    }


}
