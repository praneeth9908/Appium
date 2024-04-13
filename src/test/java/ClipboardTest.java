import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ClipboardTest {
    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setDeviceName("1361521842000MR");
        options.setAppPackage("io.selendroid.testapp");
        options.setAppActivity(".HomeScreenActivity");
        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );
    }
    @Test
    public void testClipboard() throws InterruptedException {
        String text = "Praneeth";
        driver.setClipboardText(text);
        Thread.sleep(2000);

        WebElement textInput = driver.findElement(AppiumBy.accessibilityId("my_text_fieldCD"));
        textInput.sendKeys(driver.getClipboardText());
        Thread.sleep(2000);

        Assert.assertEquals(text, textInput.getText());
    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
