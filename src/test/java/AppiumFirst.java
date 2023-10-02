import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumFirst extends BaseTest{

    @Test
    public void startAppiumTest() {
        // actual automation
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

    // not yet run successfully
    @Test
    public void longPress() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text()='People Names']"));
        System.out.println("getText people.....: "+ element.getText());
//        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
//                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),"duration",2000
//        ));
        // Java
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
        Thread.sleep(2000);
        String menuText = driver.findElement(By.id("android:id/title")).getText();
        System.out.println("menuText......:"+menuText);
        Assert.assertEquals(menuText,"Sample menu");
    }

    @Test
    public void scrollGesture() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        // way 1
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

        // way2
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 500,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while (canScrollMore);
        Thread.sleep(1000);
    }

    @Test
    public void Swipe() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(firstImage.getAttribute("focusable"),"true");
        //swipe
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)firstImage).getId(),
                "direction", "left",
                "percent", 0.95
        ));
        Assert.assertEquals(firstImage.getAttribute("focusable"),"false");
    }

    @Test
    public void DragAndDrop() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement element = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        //Drag
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", 624,
                "endY", 626
        ));
    }
}