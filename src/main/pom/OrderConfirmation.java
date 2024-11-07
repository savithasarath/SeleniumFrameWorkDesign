package main.pom;

import main.utilities.SupportingMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class OrderConfirmation extends SupportingMethods {
    WebDriver driver;

    public OrderConfirmation(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//h1[@class='hero-primary']")
    public WebElement orderConfirmationMesageText;
    @FindBy(xpath = "//label[@class='ng-star-inserted']")
    WebElement orderNumberText;

    @FindBy(xpath="//label[text()=' Orders History Page ']")
    WebElement linkToOrderHistory;

    public String getOrderNumber() {
       waitForVisibility(orderConfirmationMesageText);
        String orderNumber = orderNumberText.getText();
        orderNumber = orderNumber.substring(1, orderNumber.length() - 2).trim();
        System.out.println("Order Number Captured is: " + orderNumber);

        return orderNumber;
    }

    public void goToOrderHistory(){
        linkToOrderHistory.click();
    }





}
