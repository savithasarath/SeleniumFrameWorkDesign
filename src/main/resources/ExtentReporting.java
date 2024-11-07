package main.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporting {
    ;

    public static ExtentReports getReportObject(){
        String path=System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Test Results");
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extents=new ExtentReports();
        extents.attachReporter(reporter); // attach the above config set to the report
        extents.setSystemInfo("Savitha","Study External Reports");// Say like adding tester name and details
//        extents.createTest(path);
        return extents;
    }
}
