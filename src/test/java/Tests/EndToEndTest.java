package test.java.Tests;

import main.pom.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.TestComponents.BaseTest;
import test.java.TestComponents.RetryProgram;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class EndToEndTest extends BaseTest {
     String orderNumber;
   @Test(groups={"smoke"},retryAnalyzer = RetryProgram.class)
 //  public void submitOrderTest(HashMap<String, String> loginDetails) throws InterruptedException, IOException {
   public void submitOrderTest() throws InterruptedException, IOException {
       // landingPage.performLogin(loginDetails.get("email"),loginDetails.get("password")); // to run using dataProvider
       landingPage.performLogin("Hohoihey@gmail.com","Test1234!");
        ProductListingPage productListingpage = new ProductListingPage(driver);
        String productNameGoingToAddToCart = productListingpage.findProductAndAddtoCart();
        productListingpage.clickOnCartIcon();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(productNameGoingToAddToCart, cartPage.clickOnGoToCheckout()
                , "Product added is displayed in Cart");
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.waitForPaymentPageLoad();
        paymentPage.fillPaymentDetails();
        paymentPage.clickOnSubmitButton();
        OrderConfirmation orderConfirmation = new OrderConfirmation(driver);
        orderConfirmation.orderConfirmationMesageText.isDisplayed();
         orderNumber = orderConfirmation.getOrderNumber();
    }

    @Test(dependsOnMethods = "submitOrderTest",groups={"returningUser","smoke"})
     public void locateTheOrderInOrdersHistory() throws InterruptedException {
         landingPage.performLogin("Hohoihey@gmail.com","Test1234!");
         landingPage.clickOnOrderIcon();
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        Assert.assertTrue(orderHistoryPage.lookUpOrderNumberInHistory()
                .stream().anyMatch(orderNumberDisplayed->orderNumberDisplayed.equalsIgnoreCase(orderNumber)));
    }

    @DataProvider
    public Object[][] getDataFromJson() throws IOException {
       List<HashMap<String,String>> loginData=readJsonData();
       return new Object[][]{{loginData.get(0)},{loginData.get(1)}};

    }
}
