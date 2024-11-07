package test.java.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.TestComponents.BaseTest;

public class ErrorTests extends BaseTest {
    @Test(groups={"smoke"})
    public void invalidLoginTests(){
        landingPage.performLogin("hey@gmail.com","Test1234!");
        Assert.assertEquals(landingPage.errorMessage(),"Incorrect email or password.");
    }

}
