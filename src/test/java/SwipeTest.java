import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class SwipeTest {
    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setDeviceName("1361521842000MR");
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity(".ApiDemos");
        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );
    }

    @Test
    public void testSwipe() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

//        WebElement img = driver.findElements(AppiumBy.className("android.widget.ImageView")).get(1);
//        System.out.println(img.getLocation().toString());
        Point point = new Point(850,350);
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
        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(600),
                PointerInput.Origin.viewport(),
                point.moveBy(-200,0)
        ));
        sequence.addAction(finger.createPointerUp(
                PointerInput.MouseButton.LEFT.asArg()
        ));
        driver.perform(Collections.singletonList(sequence));
    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
