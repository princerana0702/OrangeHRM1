package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPageTest {
    private WebDriver driver;
    private String baseUrl;
    private Logger logger;
    private SoftAssert softAssert;
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger = Logger.getLogger(LoginPageTest.class.getName());
        softAssert = new SoftAssert();
    }

    @Test
    public void testLogin() {
        try {
// Test login functionality
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.clear();
            usernameField.sendKeys("Admin");

            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.clear();
            passwordField.sendKeys("admin123");

            driver.findElement(By.cssSelector("button[type='submit']")).click();

// Perform assertions or additional actions as needed
            softAssert.assertTrue(isElementPresent(By.id("dashboard")), "Login unsuccessful");

// Additional test steps...

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Test failed: " + e.getMessage(), e);
            softAssert.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName());
        }
        softAssert.assertAll();
        driver.quit();
    }

    private void captureScreenshot(String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotDir = "screenshots/";
            FileUtils.copyFile(screenshot, new File(screenshotDir + testName + ".png"));
            logger.log(Level.INFO, "Screenshot captured: " + screenshotDir + testName + ".png");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to capture screenshot: " + e.getMessage(), e);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}


