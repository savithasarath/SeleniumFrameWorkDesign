package test.java.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.pom.*;
import org.testng.Assert;
import test.java.TestComponents.BaseTest;

import java.io.IOException;

public class StepDefinitionImplementation extends BaseTest {
    ProductListingPage productListingpage;
    CartPage cartPage;
    String productName;
    PaymentPage paymentPage;
    OrderConfirmation orderConfirmation;
    String orderNumber;
    @Given ("I landed on Ecommerce page")
            public void I_landed_on_Ecommerce_page() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goToPage();

            }

    @Given("^Logged in with userName (.+) and Password (.+)$")
    public void Logged_in_with_userName_and_Password(String userName,String password)  {
        landingPage.performLogin(userName,password);

    }

    @When("^user select an item (.+) and click on add to cart$")
        public void user_select_an_item_click_on_add_to_cart(String productName) throws InterruptedException {
        this.productName=productName;
         productListingpage = new ProductListingPage(driver);
        String productNameGoingToAddToCart = productListingpage.findProductAndAddtoCart();
        productListingpage.clickOnCartIcon();

    }
    @Then("verify that product is displayed in cart")
    public void verfiy_that_product_is_displayed_in_cart(){
        cartPage = new CartPage(driver);
        Assert.assertEquals(productName, cartPage.clickOnGoToCheckout()
                , "Product added is displayed in Cart");
    }
    @And( "click on continue checkout" )
    public void click_on_continue_checkout(){

cartPage.clickOnGoToCheckout();
    }
    @And("provide payment details")
    public void provide_payment_details(){
        paymentPage = new PaymentPage(driver);
        paymentPage.waitForPaymentPageLoad();
        paymentPage.fillPaymentDetails();

    }
    @When( " user click on submit button")
    public void user_click_on_submit_button(){
        paymentPage.clickOnSubmitButton();
    }
    @Then ("{string} message is displayed")
    public void thank_you_message_is_displayed(String string){
        orderConfirmation = new OrderConfirmation(driver);
        orderConfirmation.orderConfirmationMesageText.isDisplayed();
        orderNumber = orderConfirmation.getOrderNumber();
    }

}
