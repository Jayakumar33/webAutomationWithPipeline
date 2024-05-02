package example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class Firefox {
    //WebDriver driver;
    WebDriver driver = null;
    public String username = System.getenv("LT_USERNAME");
    public String accesskey = System.getenv("LT_ACCESS_KEY");
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    DesiredCapabilities caps = new DesiredCapabilities();
    @BeforeClass
    public void BeforeClass() {
        System.out.println("Test started ");
    }

    @AfterClass
    public void AfterClass() {
        System.out.println("Test ended");
    }

    @BeforeMethod
    public void setup() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jayak\\Desktop\\chrome\\chromedriver-win64\\chromedriver.exe");
//        driver = new ChromeDriver();

        FirefoxOptions browserOptions = new FirefoxOptions();

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("browserName", "Firefox");
        ltOptions.put("platformName","Windows 10");
        ltOptions.put("browserVersion","125");
        ltOptions.put("username", username);
        ltOptions.put("resolution", "1366x768");
        ltOptions.put("accessKey", accesskey);
        ltOptions.put("geoLocation", "AL");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("network", true);
        ltOptions.put("build", "Tunnel-test");
//        ltOptions.put("tunnel", true);
        ltOptions.put("project", "Test-NG");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        caps.setCapability("LT:Options", ltOptions);

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), caps);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }

    @Test
    public void test1() {
// Activating browser driver
        driver.get("https://tutorialsninja.com/demo/");
//        driver.get("http://localhost/dashboard/");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


// Register user
        driver.findElement(By.cssSelector("a[title='My Account']")).click();//a[@title='My Account']
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")).click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


// Assert the page is correct
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("https://tutorialsninja.com/demo/index.php?route=account/login"));


        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("jaimech61@gmail.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("1234567890");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

// Check url is correct ?
        wait.until(ExpectedConditions.urlToBe("https://tutorialsninja.com/demo/index.php?route=account/account"));


// Searching for macbook
        String laptop = "macbook";
        WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchBar.sendKeys(laptop);
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
        wait.until(ExpectedConditions.urlToBe("https://tutorialsninja.com/demo/index.php?route=product/search&search=macbook"));

// Adding macbook to check out page
        driver.findElement(By.cssSelector("body > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(8) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > h4:nth-child(1) > a:nth-child(1)")).click();
        wait.until(ExpectedConditions.urlToBe("https://tutorialsninja.com/demo/index.php?route=product/product&product_id=43&search=macbook"));
        driver.findElement(By.xpath("//button[@id='button-cart']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")).click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//a[@title='Shopping Cart']//i[@class='fa fa-shopping-cart']")).click();

// Checkout cart
        wait.until(ExpectedConditions.urlToBe("https://tutorialsninja.com/demo/index.php?route=checkout/cart"));
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement message = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
        String alertMessage = message.getText();
        System.out.println(alertMessage);
        String expectedAlertMessage = "Products marked with *** are not available in the desired quantity or not in stock!\n" +
                "×";
        System.out.println(alertMessage);

        Assert.assertEquals(expectedAlertMessage, alertMessage, "Total amount is wrong");


// Logout
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.cssSelector("li[class='dropdown open'] li:nth-child(5) a:nth-child(1)")).click();

// Assert is user logout
        wait.until(ExpectedConditions.urlToBe("https://tutorialsninja.com/demo/index.php?route=account/logout"));
        WebElement logoutMessageElement = driver.findElement(By.xpath("//h1[normalize-space()='Account Logout']"));
        String logoutMessage = logoutMessageElement.getText();
        System.out.println(logoutMessage);
        String expectedLogoutMessage = "Account Logout";
        System.out.println(expectedLogoutMessage);
        Assert.assertEquals(logoutMessage, expectedLogoutMessage, "Logout was not successful");

        System.out.println("Successfully test executed");

    }

}