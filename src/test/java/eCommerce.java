import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class eCommerce extends BaseTest {
    // 4. Validate the total Amount displayed in the checkout page matches with sum of product amounts selected for Shopping
    @Test
    public void Fill_Form() {
        // 1. Fill the form details and verify Toast error message displayed appropriately for wrong inputs
        String country = "China";
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ country +"\"));"));
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("     ");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage,"Please enter your name");

        // 2. Shop the items in the app by scrolling to specific Product and add to cart
        String[] itemsList = {"Air Jordan 1 Mid SE","PG 3","Jordan 6 Rings"};
        List<Float> priceList = new LinkedList<>();

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Nutest");
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for (int i=0; i<productCount; i++) {
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (Arrays.asList(itemsList).contains(productName)) {
                driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ productName +"\"));"));
                float price = Integer.parseInt(driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText().replace("$", ""));
                priceList.add(price);
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }
        int itemCount = Integer.parseInt(driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText());
        Assert.assertEquals(itemCount, itemsList.length);

        // 3. Validate if the items selected in the page 2 are matching with item displayed in check out page
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText(),"Cart");
        for (WebElement ele : driver.findElements(By.id("com.androidsample.generalstore:id/productName"))) {
            Assert.assertTrue(Arrays.stream(itemsList).anyMatch(item -> item.equalsIgnoreCase(ele.getText())));
        }
        float realTotalAmount = Integer.parseInt(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().replace("$", ""));
        float sum = 0;
        for (Float price : priceList) {
            sum += price;
        }
        Assert.assertEquals(sum, realTotalAmount);
    }
}