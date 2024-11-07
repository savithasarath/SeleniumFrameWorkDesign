package test.java.TestComponents;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.pom.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream
                (System.getProperty("user.dir") + "//src//main//resources//GlobalConfig.properties");
        prop.load(fis);

        //Code to take browserOfTest from mvn command when available. Also an example of java ternary operator
        String browserType = System.getProperty("browser") != null
                ? System.getProperty("browser") : prop.getProperty("browser");

        System.out.println(browserType);

        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage goToApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goToPage();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }

    public List<HashMap<String, String>> readJsonData() throws IOException {
        //Need to add commons.io to convert the json to String
        String jsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src/main//resources//TestData.json"));
        //Need to add Jackson bind repository so that string can be converted to hashmap for later use
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonData,
                new TypeReference<List<HashMap<String, String>>>() {
                });

        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destFilePath = System.getProperty("user.dir") + "//reports" + testCaseName + LocalTime.now() + ".png";
        File destFile = new File(destFilePath);
        FileUtils.copyFile(source, destFile);
        return destFilePath;

    }


}
