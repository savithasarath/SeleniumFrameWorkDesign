package main.pom;

import main.utilities.SupportingMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProductListingPage extends SupportingMethods {
    WebDriver driver;

    public  ProductListingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='card-body']//button[@class='btn w-10 rounded']")
    WebElement addToCartButton;
    @FindBy(xpath = "//ngx-spinner/div")
    WebElement spinnerTransition;
    @FindBy(xpath="//div[@class='card-body']//button[@class='btn w-10 rounded']/../h5/b")
    WebElement productName;

    public String findProductAndAddtoCart() {
        waitForVisibility(addToCartButton);
       String productNameGoingToAddToCart = productName.getText();
        addToCartButton.click();
        System.out.println("This is me; " + productNameGoingToAddToCart + " got added");
        waitForInvisibility(spinnerTransition);
        return productNameGoingToAddToCart;
    }





}
