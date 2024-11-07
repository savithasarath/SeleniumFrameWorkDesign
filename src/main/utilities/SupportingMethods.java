package main.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SupportingMethods {

    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public SupportingMethods(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //@FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
    @FindBy(xpath ="//button[@routerlink='/dashboard/cart']/label")
    WebElement goToCartIcon;

    @FindBy(xpath = "//i[@class='fa fa-handshake-o']")
    WebElement ordersIcon;

    @FindBy(xpath = "//ngx-spinner/div")
    WebElement spinnerTransition;

    By cartIcon = By.xpath("//i[@class='fa fa-shopping-cart']");

    public void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf
                (element));
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf
                (element));
    }

    public void elementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable
                (element));
    }

    public void elementToBeClickableBy(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
    }

    public void actionMethod(WebElement element) {
        Actions a = new Actions(driver);
        a.moveToElement(element).
                build().
                perform();
    }

    public void clickOnCartIcon() throws InterruptedException {
        waitForInvisibility(spinnerTransition);
        elementToBeClickable(goToCartIcon);
        Thread.sleep(3000);
        goToCartIcon.click();
    }

    public void clickOnOrderIcon() {
        elementToBeClickable(ordersIcon);
        ordersIcon.click();
    }

}
