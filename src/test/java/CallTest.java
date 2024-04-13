import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class CallTest {
    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("11.0");
        options.setDeviceName("emulator-5554");
        options.setAppPackage("com.android.mms");
        options.setAppActivity(".ui.ConversationList");
        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );
    }

    @Test
    public void testPhoneAndSMS() throws InterruptedException {

        String PHONE_NUMBER = "123456789";
        driver.makeGsmCall(PHONE_NUMBER, GsmCallActions.CALL);
        Thread.sleep(2000); // pause just for effect
        driver.makeGsmCall(PHONE_NUMBER, GsmCallActions.ACCEPT);
        Thread.sleep(2000); // pause just for effect
        driver.makeGsmCall(PHONE_NUMBER, GsmCallActions.CANCEL);
        driver.navigate().back();

    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
