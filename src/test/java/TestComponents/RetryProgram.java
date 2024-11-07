package test.java.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryProgram implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            count++;
            return true;
            // logic is to retry the number of times as maxTry has assigned. Until then this returns true and when the condition fails; the method return false and never retry
        }
        return false;
    }
}
