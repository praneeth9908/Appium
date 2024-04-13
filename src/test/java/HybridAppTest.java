import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

public class HybridAppTest {
    private AndroidDriver driver;
    private static final String APP_ANDROID = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://localhost:4723";

    private static final By URL_INPUT = AppiumBy.accessibilityId("urlInput");
    private static final By WEBVIEW_BTN = AppiumBy.accessibilityId("Webview Demo");

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setPlatformVersion("13.0");
        options.setDeviceName("1361521842000MR");
        options.setAutoGrantPermissions(true);
        options.setApp(APP_ANDROID);
        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL(APPIUM),
                options
        );

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    private String getWebContext(AndroidDriver driver) {
        ArrayList<String> contexts = new ArrayList<>(driver.getContextHandles());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                return context;
            }
        }
        return null;
    }
    @Test
    public void testHybridApp() throws InterruptedException {
        driver.findElement(WEBVIEW_BTN).click();

        Thread.sleep(1000);
        driver.context(getWebContext(driver));
        Thread.sleep(1000);

        driver.get("https://facebook.com");

    }
//    @Test
    public void testHybridAppMultipleContext() throws InterruptedException {
        driver.findElement(WEBVIEW_BTN).click();

        WebElement url = driver.findElement(URL_INPUT);
        WebElement btn = driver.findElement(AppiumBy.accessibilityId("navigateBtn"));

        url.sendKeys("https://cloudgrey.com");
        btn.click();

        assert driver.switchTo().alert().getText().contains("Sorry");
        driver.switchTo().alert().accept();

        Thread.sleep(1000);
        driver.context(getWebContext(driver));
        Thread.sleep(1000);

        assert driver.findElement(By.cssSelector("body > center > h1")).getText().equals("Please navigate to a webpage");

        Thread.sleep(1000);
        driver.context("NATIVE_APP");
        Thread.sleep(1000);

        url.clear();
        url.sendKeys("https://appiumpro.com");
        btn.click();

//        driver.get("https://cloudgrey.io");
        Thread.sleep(1000);
        driver.context(getWebContext(driver));
        Thread.sleep(1000);

        assert new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("Appium Pro"));

    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
