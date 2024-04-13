import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidMobileWebTest {
    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setPlatformVersion("13.0");
        options.setDeviceName("1361521842000MR");
        options.setCapability("browserName","Chrome");

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );

    }

    @Test
    public void testWebChrome() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(AppiumBy.cssSelector("#username")).sendKeys("tomsmith");
        driver.findElement(AppiumBy.cssSelector("#password")).sendKeys("SuperSecretPassword!");
        driver.findElement(AppiumBy.cssSelector("button.radius")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlContains("secure"));
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());
    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
