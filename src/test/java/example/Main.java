package example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Main {
    //WebDriver driver;
    WebDriver driver = null;
    public String username = "jayak";
    public String accesskey = "b4JOibh8UrnB8Ce2eL2VgHtc9P91eu4xCSc2IdXA7nibD30lX1";
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

        ChromeOptions browserOptions = new ChromeOptions();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("browserName", "Chrome");
        ltOptions.put("browserVersion", "105.0");
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("username", "jayak");
        ltOptions.put("resolution", "1366x768");
        ltOptions.put("accessKey", "b4JOibh8UrnB8Ce2eL2VgHtc9P91eu4xCSc2IdXA7nibD30lX1");
        ltOptions.put("geoLocation", "AL");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("network", true);
        ltOptions.put("build", "Training");
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
        System.out.println(driver.getTitle());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void test2() {
//Activating browser driver
        driver.get("https://tutorialsninja.com/demo/");
        System.out.println(driver.getTitle());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}