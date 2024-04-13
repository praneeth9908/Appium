import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class FlipkartTest {
    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setDeviceName("1361521842000MR");
        options.setAppPackage("com.flipkart.android");
        options.setAppActivity(".SplashActivity");
        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );

    }

    private void tap(int x, int y){
        Point point = new Point(x, y);
        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                point
        ));
        sequence.addAction(finger.createPointerDown(
                PointerInput.MouseButton.LEFT.asArg()
        ));
        sequence.addAction(new Pause(finger, Duration.ofMillis(100)));
        sequence.addAction(finger.createPointerUp(
                PointerInput.MouseButton.LEFT.asArg()
        ));

        driver.perform(Collections.singletonList(sequence));
    }
    @Test
    public void testFlipkart() {
        tap(450, 300);
        WebElement search = driver.findElement(AppiumBy.className("android.widget.EditText"));
        search.sendKeys("Gents Shirts");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
