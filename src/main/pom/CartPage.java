package main.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='cartWrap ng-star-inserted']//h3")
    WebElement productName;
    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement proceedToCheckout;


    public String clickOnGoToCheckout(){
        String itemPresentinCart = productName.getText();
        proceedToCheckout.click();
        return itemPresentinCart;
    }

}
