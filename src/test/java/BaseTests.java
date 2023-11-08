import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTests {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String baseUrl = "http://localhost:8080";
    @BeforeEach
    public void setup() {
       driver = new ChromeDriver();

        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        // Duration implicitWait = driver.manage().timeouts().getImplicitWaitsTimeout();
        //Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @AfterEach
    public  void  quitDriver() { driver.quit();}





}