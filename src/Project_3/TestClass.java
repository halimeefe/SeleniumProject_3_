package Project_3;

import Utility.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Utility.BaseDriver.driver;


public class TestClass extends BaseDriver{

//    Test Case 1:
//            ➢ https://shopdemo.e-junkie.com/ sitesine gidiniz
//            ➢ E-book add to cart butonuna tıklatınız.
//            ➢ Add promo code butonuna tıklatıp veri giriniz.
//            ➢ Apply butonuna tıklayınız.
//            ➢ Invalid promo code yazısının görüldüğünü doğrulayınız

    @Test
    public void Test1() {

        driver.get("https://shopdemo.e-junkie.com/");

        WebElement ebook = driver.findElement(By.linkText("Ebook"));
        ebook.click();

        WebElement addToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        addToCart.click();

        WebElement iframe = driver.findElement(By.cssSelector("[class='EJIframeV3 EJOverlayV3']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);
        WebElement addPromoCode = driver.findElement(By.xpath("//*[text()='Add Promo Code']"));
        addPromoCode.click();

        WebElement code = driver.findElement(By.cssSelector("[class='Promo-Code-Value']"));
        code.sendKeys("promotion");

        WebElement apply = driver.findElement(By.cssSelector("[class='Promo-Apply']"));
        apply.click();

        WebElement userText = driver.findElement(By.xpath("//*[text()='Invalid promo code']"));
        System.out.println(userText.getText());

        Assert.assertEquals("Invalid promo code", userText.getText());

    }

    @Test
    public void Test2() throws InterruptedException {

        //driver.get("https://shopdemo.e-junkie.com/");
        //WebElement ebook = driver.findElement(By.linkText("Ebook"));
        //ebook.click();


        //WebElement addToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        //addToCart.click();

        WebElement iframe = driver.findElement(By.cssSelector("[class='EJIframeV3 EJOverlayV3']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);
        WebElement debitCard = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        debitCard.click();

        WebElement payBtn = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payBtn.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SnackBar']/span")));
        WebElement invalid = driver.findElement(By.xpath("//*[@id='SnackBar']/span"));
        System.out.println(invalid.getText());

        Assert.assertTrue(invalid.getText().contains("Invalid Email"));

    }

    @Test
    public void Test3() throws InterruptedException {

        driver.get("https://shopdemo.e-junkie.com/");
        WebElement ebook = driver.findElement(By.linkText("Ebook"));
        ebook.click();


        WebElement addToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        addToCart.click();

        WebElement iframe = driver.findElement(By.cssSelector("[class='EJIframeV3 EJOverlayV3']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);
        WebElement debitCard = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        debitCard.click();

        WebElement iframe2 = driver.findElement(By.xpath("//div[@class='__PrivateStripeElement']//iframe"));
        wait.until(ExpectedConditions.visibilityOf(iframe2));
        driver.switchTo().frame(iframe2);
        WebElement cardNumber = driver.findElement(By.xpath("(//input[@class='InputElement is-empty Input Input--empty'])[1]"));
        cardNumber.sendKeys("1111 1111 1111 1111");
        driver.switchTo().parentFrame();  //dışarı çıkıp bir üstteki frame'e geçiş yaptık.

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SnackBar']/span")));
        WebElement assertion = driver.findElement(By.xpath("//*[@id='SnackBar']/span"));
        Assert.assertEquals("Your card number is invalid.", assertion.getText());

        driver.close();
        Thread.sleep(10000);
    }

    @Test
    public void Test4() throws InterruptedException {

        driver.get("https://shopdemo.e-junkie.com/");
        WebElement ebook = driver.findElement(By.linkText("Ebook"));
        ebook.click();


        WebElement addToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        addToCart.click();

        WebElement iframe = driver.findElement(By.cssSelector("[class='EJIframeV3 EJOverlayV3']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);
        WebElement debitCard = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        debitCard.click();

        WebElement email = driver.findElement(By.cssSelector("[placeholder=Email]"));
        email.sendKeys("cihatdugerr@gmail.com");
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB).sendKeys("cihatdugerr@gmail.com").build().perform();
        action.sendKeys(Keys.TAB).sendKeys("Cihat").build().perform();
        action.sendKeys(Keys.TAB).sendKeys("533 556 87 90").build().perform();
        action.sendKeys(Keys.TAB).sendKeys("Techno Study").build().perform();

        WebElement iframe3 = driver.findElement(By.xpath("//div[@class='__PrivateStripeElement']//iframe"));
        wait.until(ExpectedConditions.visibilityOf(iframe3));
        driver.switchTo().frame(iframe3);
        WebElement cardNumber = driver.findElement(By.xpath("(//input[@class='InputElement is-empty Input Input--empty'])[1]"));
        cardNumber.sendKeys("4242 4242 4242 4242 1223 000");
        driver.switchTo().parentFrame();

        WebElement payBtn = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='green_text_margin']")));
        WebElement greenText = driver.findElement(By.cssSelector("[class='green_text_margin']"));
        Assert.assertEquals("your order is confirmed. Thank you!", greenText.getText());


        driver.close();
        Thread.sleep(10000);
    }
    @Test
    public void Test5 () throws InterruptedException {

        driver.get("https://shopdemo.e-junkie.com/");
        WebElement contactUs= driver.findElement(By.cssSelector("a[class='contact']"));
        contactUs.click();


        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys("Cihat").build().perform();
        actions.sendKeys(Keys.TAB).sendKeys("cihattduger@gmail.com").build().perform();
        actions.sendKeys(Keys.TAB).sendKeys("Java").build().perform();
        actions.sendKeys(Keys.TAB).sendKeys("Merhaba Dünya").build().perform();
        actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        //System.out.println(driver.switchTo().alert().getText());
        Assert.assertEquals("Recaptcha didn't match", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

       driver.quit();






    }



}
