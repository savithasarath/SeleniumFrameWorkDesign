package test.java.TestComponents;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import main.resources.ExtentReporting;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentReports extent = ExtentReporting.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> beSafe=new ThreadLocal();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        beSafe.set(test); // to make sure each test is threadsafe when ran in parellel
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        beSafe.get().log(Status.PASS, " Test completed successfully");

    }


    @Override
    public void onTestFailure(ITestResult result) {
        beSafe.get().fail(result.getThrowable());
        String filePath = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        beSafe.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        driver.quit();
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        // not implemented
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
