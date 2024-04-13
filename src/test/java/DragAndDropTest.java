import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
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

public class DragAndDropTest {
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
    public void testScroll() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement drag = driver.findElement(AppiumBy.id("drag_dot_1"));
        WebElement drop = driver.findElement(AppiumBy.id("drag_dot_3"));

        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                new Point(215, 590)
        ));
        System.out.println(drag.getLocation().toString());
        sequence.addAction(finger.createPointerDown(
            PointerInput.MouseButton.LEFT.asArg()
        ));
        sequence.addAction(new Pause(finger, Duration.ofMillis(1000)));
        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(600),
                PointerInput.Origin.viewport(),
                new Point(215, 1020)
        ));
        System.out.println(drop.getLocation().toString());
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
