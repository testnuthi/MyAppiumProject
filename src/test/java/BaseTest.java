import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    AndroidDriver driver;
    AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        // Appium code > Appium Server > Mobile

        // start appium server using AppiumServiceBuilder
//        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\VMO\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//                .withIPAddress("172.16.4.180").usingPort(4723).build();
//        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel6API31");
//        options.setApp("D:\\Automation Project\\MyAppiumProject\\src\\main\\resources\\ApiDemos-debug.apk");
        options.setApp("D:\\Automation Project\\MyAppiumProject\\src\\main\\resources\\General-Store.apk");
        driver = new AndroidDriver(new URL("http://172.16.4.180:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterClass
     public void tearDown() {
         // stop server
         driver.quit();
         // stop appium server using AppiumServiceBuilder
         service.stop();
     }
}