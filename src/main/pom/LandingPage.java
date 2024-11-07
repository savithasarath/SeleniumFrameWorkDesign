package main.pom;

import main.utilities.SupportingMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage extends SupportingMethods {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='userEmail']")
    WebElement userEmail;
    @FindBy(xpath = "//input[@id='userPassword']")
    WebElement userPWD;
    @FindBy(xpath = "//input[@id='login']")
    WebElement submitButton;
    @FindBy(css = "div[class*='flyInOut']")
    WebElement loginErrorText;

    public void goToPage() {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public void performLogin(String username, String pwd) {
        userEmail.sendKeys(username);
        userPWD.sendKeys(pwd);
        submitButton.click();

    }

    public String errorMessage() {
        waitForVisibility(loginErrorText);
        return loginErrorText.getText();
    }
}
