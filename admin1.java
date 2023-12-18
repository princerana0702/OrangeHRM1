package Admin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.FileAssert.fail;

public class admin1 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private JavascriptExecutor js;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }



    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        driver.findElement(By.linkText("Admin")).click();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div[2]/div/button")).click();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div/form/div/div/div/div/div[2]/div/div/div")).click();
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div/form/div/div/div[2]/div/div[2]/div/div/input")).click();

        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div/form/div/div/div[2]/div/div[2]/div/div/input")).sendKeys("Test 90 Krishna M");
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div/form/div/div/div[3]/div/div[2]/div/div/div")).click();
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div/form/div/div/div[4]/div/div[2]/input")).click();

        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div/form/div/div/div[4]/div/div[2]/input")).sendKeys("rana07021");
        driver.findElement(By.xpath("//input[@type='password']")).click();

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Pass@2023");
        driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")).click();


        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
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

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }}