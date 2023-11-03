import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;


public class Tests {
    WebDriver driver;
    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    //    driver = new FirefoxDriver();
    //    driver = new EdgeDriver();
    //    driver = new InternetExplorerDriver();
    //    driver = new SafariDriver();
    }
    @AfterEach public void quitDriver(){
            driver.quit();
        }
        @Test
        public void exampleTest(){

            String url = "http://localhost:8080/";
            driver.get(url);
//            Assertions.assertAll(
//                    ()->    Assertions.assertEquals(url, driver.getCurrentUrl(),
//                            "URL address of the main page is not what expected"),
//                    ()-> Assertions.assertEquals("Test App - Just another WordPress site",
//                    driver.getTitle(), "Site title is not correct")
//            );


            //Assertions.assertEquals("Test App - Just another WordPress site",
            //        driver.getTitle(), "Site title is not correct");
            WebElement searchElement =  driver.findElement(By.id("wc-block-search__input-1" ));
            searchElement.click();
            List<WebElement> addToCartButtons = driver.findElements(By.className("add_to_cart_button"));
    }
    }

