import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AppiumFirst extends BaseTest{

    @Test
    public void startAppiumTest() {
        // actual automation
        driver.findElement(AppiumBy.accessibilityId("Preference"));
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        driver.findElement(By.id("android:id/checkbox")).click();

//      driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text,'WiFi settings')]")).click();

        String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        System.out.println("alertTitle........: "+alertTitle);
        driver.findElement(By.id("android:id/edit")).sendKeys("NTG wifi");
        driver.findElement(By.id("android:id/button1")).click();

    }

    @Test
    public void longPress() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        Thread.sleep(1000);
        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[text()='People Names']"));
        Thread.sleep(1000);
//        ((JavascriptExecutor)driver).executeScript("mobile: longPressGesture",
//                ImmutableMap.of("elementId", (RemoteWebElement)ele).get("id"), "duration", 2000);
//
//        Thread.sleep(2000);

//        try {
//            await().
//                    atMost(30, TimeUnit.SECONDS).
//                    with().pollDelay(1, TimeUnit.SECONDS).pollInSameThread().
//                    pollInterval(2, TimeUnit.SECONDS).
//                    untilAsserted(() -> assertThat(getCurrentBalance()).as("" +
//                                    "Wallet balance was not updated to proper value")
//                            .isEqualTo(CommonUtils.round(expectedBalance, 2)));
//        } catch (org.awaitility.core.ConditionTimeoutException e) {
//            fail(e.getMessage());
//        }
    }
}