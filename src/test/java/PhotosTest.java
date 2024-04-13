import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PhotosTest {
    AndroidDriver driver;

//    private static By backupBtn = By.id("auto_backup_switch");
//    private static By touchOutSideBtn = By.id("touch_outside");
//    private static By keepOffBtn = By.xpath("//*[@text='KEEP OFF']");
//    private static By photo = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'Photo taken')]");

    File classPath, imageDir, img;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setDeviceName("1361521842000MR");
        options.setAppPackage("com.google.android.apps.photos");
        options.setAppActivity(".home.HomeActivity");
        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );
    }
    @Test
    public void testPhotos() throws IOException {
        classPath = new File(System.getProperty("user.dir"));
        imageDir = new File(classPath, "/resources/images");
        img = new File(imageDir.getCanonicalFile(), "pran.jpeg");

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.presenceOfElementLocated(backupBtn)).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(touchOutSideBtn)).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(keepOffBtn)).click();

        String Android_Photo_Path = "/storage/emulated/0/Pictures";
        driver.pushFile(Android_Photo_Path + "/" + img.getName(), img);
//        wait.until(ExpectedConditions.numberOfElementsToBe(photo,1));
    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
