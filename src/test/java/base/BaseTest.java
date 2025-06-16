package base;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class BaseTest {
	protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
    	String browser = System.getProperty("browser", "edge");
        if (browser.equalsIgnoreCase("edge")) {
        	WebDriverManager.edgedriver().setup();
            WebDriverManager.chromedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String baseUrl = System.getProperty("baseUrl", "https://www.saucedemo.com");
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
