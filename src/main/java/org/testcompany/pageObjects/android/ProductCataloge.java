package org.testcompany.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testcompany.utils.AndroidActions;
import java.util.List;

//GrandParent(AppiumUtils) -> AndroidActions -> ProductCatalogue
public class ProductCataloge extends AndroidActions {
    AndroidDriver driver;

    public ProductCataloge(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

   @AndroidFindBy (xpath="//android.widget.TextView[@text='ADD TO CART']")
   private List<WebElement> addToCart;

    @AndroidFindBy (id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    public void addItemTocartByIndex(int index)
    {
        addToCart.get(index).click();
    }

    public CartPage goToCartPage() throws InterruptedException {
        cartButton.click();
        Thread.sleep(2000);
        return new CartPage(driver);
    }
}
