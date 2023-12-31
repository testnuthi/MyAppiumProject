import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class eCommerce extends BaseTest {
    // 4. Validate the total Amount displayed in the checkout page matches with sum of product amounts selected for Shopping
    @Test
    public void Fill_Form() throws InterruptedException {
        // 1. Fill the form details and verify Toast error message displayed appropriately for wrong inputs
        String country = "Angola";
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        Thread.sleep(500);
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ country +"\"));"));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
        Thread.sleep(500);
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Nutest");
//        driver.hideKeyboard();
        Thread.sleep(500);
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        // 2. Shop the items in the app by scrolling to specific Product and add to cart
        String[] itemsList = {"Air Jordan 1 Mid SE","Jordan 6 Rings","PG 3"};
        List<Float> priceList = new LinkedList<>();
        Thread.sleep(500);
        for (int i=0; i<itemsList.length; i++) {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ itemsList[i] +"\"));"));
            driver.findElement(
                    By.xpath("//android.widget.TextView[@text='"+itemsList[i]+"']/following-sibling::android.widget.LinearLayout//android.widget.TextView[@text='ADD TO CART']")).click();
            Thread.sleep(300);
            float price = Float.parseFloat((driver.findElement(
                    By.xpath("//android.widget.TextView[@text='"+itemsList[i]+"']/following-sibling::android.widget.LinearLayout//android.widget.TextView")).getText().substring(1)));
           priceList.add(price);
        }
        Thread.sleep(500);
        int itemCount = Integer.parseInt(driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText());
        Assert.assertEquals(itemCount, itemsList.length);

        // 3. Validate if the items selected in the page 2 are matching with item displayed in check out page
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(300);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"),"text","Cart"));
        Thread.sleep(300);
        for (WebElement ele : driver.findElements(By.id("com.androidsample.generalstore:id/productName"))) {
            Thread.sleep(300);
            Assert.assertTrue(Arrays.stream(itemsList).anyMatch(item -> item.equalsIgnoreCase(ele.getText())));
        }
        float realTotalAmount = Float.parseFloat((driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(1)));
        float sum = 0;
        for (Float price : priceList) {
            sum += price;
        }
        Assert.assertEquals(sum, realTotalAmount);

        // validate mobile gesture of app (tap, long, press)
        Thread.sleep(1000);
        longPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText(),"Terms Of Conditions");
        Thread.sleep(1000);
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(6000);

        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts) {
            System.out.println("contextName:  "+contextName);
        }
        Thread.sleep(1000);
//        driver.context("WEBVIEW_com.androidsample.generalstore");
        Thread.sleep(1000);
        driver.findElement(By.name("q")).sendKeys("nutest 123");
        Thread.sleep(1000);
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(1000);
        driver.context("NATIVE_APP");
        Thread.sleep(3000);
    }
}