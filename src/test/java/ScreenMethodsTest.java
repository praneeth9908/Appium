import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class ScreenMethodsTest {
    AndroidDriver driver;
    private static final String APP_ANDROID = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setPlatformVersion("13.0");
        options.setDeviceName("1361521842000MR");
        options.setApp(APP_ANDROID);
        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );

    }

    @Test
    public void testScreenMethods() throws IOException {
        ScreenOrientation orientation = driver.getOrientation();
        System.out.println(orientation);
        if(orientation != ScreenOrientation.LANDSCAPE){
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
        Dimension dimension = driver.manage().window().getSize();
        System.out.println(dimension);
        File screenShot = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("/home/pran/Desktop/ss.png"));
        driver.rotate(ScreenOrientation.PORTRAIT);
        System.out.println(driver.getOrientation());
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
