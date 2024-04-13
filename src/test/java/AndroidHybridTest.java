import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class AndroidHybridTest {
    AndroidDriver driver;
    private static By chromeBtn = By.id("buttonStartWebview");
    private static By gotoHomeBtn = By.id("goBack");

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setPlatformVersion("13.0");
        options.setDeviceName("1361521842000MR");
        options.setAutoGrantPermissions(true);
        options.setApp(System.getProperty("user.dir") + "/apps/selendroid-test-app.apk");
        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );

    }

    public void switchToWebView() {
        Set<String> contexts = driver.getContextHandles();
        for (String con : contexts) {
            if (con.contains("WEBVIEW")) {
                driver.context(con);
                break;
            }
        }
    }

    @Test
    public void testHybridTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.findElement(chromeBtn);
        wait.until(ExpectedConditions.presenceOfElementLocated(chromeBtn)).click();
        switchToWebView();
        WebElement nameInput = driver.findElement(By.xpath(""));
        nameInput.clear();
        String name = "Praneeth Kumar";
        nameInput.sendKeys(name);
        driver.context("NATIVE_APP");
        driver.findElement(gotoHomeBtn).click();
    }
//    public void switchToWebView() {
//        Set<String> contexts = driver.getContextHandles();
//        for (String context : contexts) {
//            if (context.contains("WEBVIEW")) {
//                driver.context(context);
//                break;
//            }
//        }
//    }

//    @Test
//    public void testHybridApp() {
//        // Your test logic
//        WebElement chromeBtn = driver.findElement(AppiumBy.accessibilityId("buttonStartWebviewCD"));
//        chromeBtn.click();
//
//        switchToWebView();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        try {
//            WebElement nameInput = wait.until(driver -> driver.findElement(AppiumBy.id("name_input")));
//            nameInput.clear();
//            String name = "Praneeth Kumar";
//            nameInput.sendKeys(name);
//        } catch (TimeoutException e) {
//            System.out.println("Timed out waiting for element to be present");
//            // Handle timeout exception
//        }
//
//        // Switch back to native context
//        driver.context("NATIVE_APP");
//
//        WebElement gotoHomeBtn = driver.findElement(AppiumBy.accessibilityId("buttonStartWebview"));
//        gotoHomeBtn.click();
//    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
