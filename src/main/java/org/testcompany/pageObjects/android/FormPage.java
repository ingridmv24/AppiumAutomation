package org.testcompany.pageObjects.android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testcompany.utils.AndroidActions;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public  FormPage(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ingrid Munera");

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']" )
    private WebElement femaleOption;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']" )
    private WebElement maleOption;

    @AndroidFindBy(id="android:id/text1")
    private WebElement countrySelection;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;


    public void setNameField(String name)
    {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender)
    {
        if(gender.contains("female"))
            femaleOption.click();
        else
            maleOption.click();
    }


    //Scroll until Colombia text and select Colombia country
    public void setCountrySelection(String countryName)
    {
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    //Click on Let's Shop button
    public ProductCataloge submitForm()
    {
        shopButton.click();
        return new ProductCataloge(driver);
    }
}
