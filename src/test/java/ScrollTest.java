import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class ScrollTest {
    AndroidDriver driver;
    Actions actions;

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

    private void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);
        int width = dimension.getWidth()/2;
        PointerInput finger = new PointerInput(
                PointerInput.Kind.TOUCH,
                "finger"
        );
        Sequence sequence = new Sequence(
                finger, 1
        );
        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                width,
                scrollStart
        ));
        sequence.addAction(finger.createPointerDown(
                PointerInput.MouseButton.MIDDLE.asArg()
        ));
        sequence.addAction(new Pause(finger, Duration.ofMillis(600)));
        sequence.addAction(finger.createPointerMove(
                Duration.ofMillis(600),
                PointerInput.Origin.viewport(),
                width,
                scrollEnd
        ));
        sequence.addAction(finger.createPointerUp(
                PointerInput.MouseButton.MIDDLE.asArg()
        ));
        driver.perform(Collections.singletonList(sequence));
    }
    @Test
    public void testScroll() throws InterruptedException {
        //Clicking On Views
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        actions = new Actions(driver);
        actions.click(views).perform();
        Thread.sleep(2000);

        //Scroll Down
        scrollDown();
        driver.findElement(AppiumBy.accessibilityId("Lists")).click();
        Thread.sleep(2000);


    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
