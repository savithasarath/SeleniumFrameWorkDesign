package test.java.cucumbertests;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumbertests/e2e.feature",
        glue = "src/test/java/stepdefinitions/StepDefinitionImplementation.java" ,
monochrome = true,plugin = {"html:target/reports.html"})
public class TestNGTestRunner {
}
