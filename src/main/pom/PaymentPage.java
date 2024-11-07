package main.pom;

import main.utilities.SupportingMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class PaymentPage extends SupportingMethods {

    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);// sends driver to parent
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()=' Payment Method ']")
    WebElement paymentMethodText;
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryfield;
    @FindBy(xpath = "//*[@class='ta-results list-group ng-star-inserted']")
    WebElement countrySuggestion;
    @FindBy(css = ".action__submit")
    WebElement submitButton;


    public void waitForPaymentPageLoad() {
        waitForVisibility(paymentMethodText);
    }

    public void fillPaymentDetails() {
        countryfield.sendKeys("United States");
        waitForVisibility(countrySuggestion);
        countryfield.sendKeys(Keys.DOWN, Keys.ENTER);
    }


    public void clickOnSubmitButton() {
        actionMethod(submitButton);
        submitButton.click();
    }


}
