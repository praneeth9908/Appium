import io.appium.java_client.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;

import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class FirstAndroidTest {

    AndroidDriver driver;
//    TouchAction action;
//
//
//    @BeforeTest
//    public void setUp() throws MalformedURLException, InterruptedException {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "1361521842000MR");
//        capabilities.setCapability("appPackage", "io.appium.android.apis");
//        capabilities.setCapability("appActivity", ".ApiDemos");
//        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
//
//        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723"), capabilities);
//    }
//
//    @Test
//    public void test_scroll_action() throws InterruptedException {
//        //Tap
//        AndroidElement views = driver.findElement(MobileBy.AccessibilityId("Views"));
//        action = new TouchAction(driver);
//        action.tap(ElementOption.element(views)).perform();
//
//    }
//    @Test
//    public void click_App_Button() throws InterruptedException {
//        driver.findElement(By.id("digit_1")).click();
//        driver.findElement(AppiumBy.accessibilityId("Plus")).click();
//        driver.findElement(AppiumBy.id("digit_3")).click();
//        driver.findElement(AppiumBy.accessibilityId("=")).click();
//        String result = driver.findElement(AppiumBy.id("formula")).getText();
//        Assert.assertEquals(result, "4");
//        Thread.sleep(5000);
//    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
