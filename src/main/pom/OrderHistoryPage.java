package main.pom;

import main.utilities.SupportingMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryPage extends SupportingMethods {
    WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//h1[text()='Your Orders']")
    public WebElement orderHistoryPageText;
    @FindBy(xpath = "//tr[@class='ng-star-inserted']/th")
    List<WebElement> getOrderNumbers;

    public List<String>  lookUpOrderNumberInHistory() throws InterruptedException {
        waitForVisibility(orderHistoryPageText);
        Thread.sleep(3000);
        List<WebElement> orderNumbers = getOrderNumbers;
        List<String> orders = new ArrayList<>();
        for (WebElement orderfromHistory : orderNumbers) {
            orders.add(orderfromHistory.getText());
        }
       // System.out.println("Hiiiiiiii" + orders.stream().collect(Collectors.toList()) + "hoi" + orderNumber);
        return orders;
    }





}
