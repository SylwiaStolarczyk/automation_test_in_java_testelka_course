import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Really {
        WebDriver driver;
        @BeforeEach
        public void setup(){
            driver = new ChromeDriver();
        }
        @AfterEach
        public void quitDriver(){
            driver.quit();
        }
        @Test
        public void NewEvent(){
            driver.get("http://localhost:3000/new");
            WebElement TytulSpotkania = driver.findElement(By.id("title"));
            TytulSpotkania.sendKeys("Spotkanie z Testelką");
            driver.findElement(By.className("btn-primary")).click();
            WebElement stepTracking = driver.findElement(By.className("tracking-tight"));
            Assertions.assertEquals("Etap 2 z 3", stepTracking.getText(),
                    "Step tracking doesn't show that we are at the second step.");

        }
        @Test
    public void proper_title_in_placeholders(){
            driver.get("http://localhost:3000/new");
            WebElement searchField = driver.findElement(By.id("title"));
            Assertions.assertEquals("Miesięczne spotkanie", searchField.getDomAttribute("placeholder"), "Placeholder for search field is not correct");


        }
        @Test
    public void proper_location_in_placeholders(){
        driver.get("http://localhost:3000/new");
        WebElement searchField = driver.findElement(By.id("location"));
        Assertions.assertEquals("Sklep z kawą Joe", searchField.getDomAttribute("placeholder"), "Placeholder for search field is not correct");


    }
    @Test
    public void proper_description_in_placeholders(){
        driver.get("http://localhost:3000/new");
        WebElement searchField = driver.findElement(By.id("description"));
        Assertions.assertEquals("Cześć wszystkim, wybierzcie terminy, które Wam pasują!", searchField.getDomAttribute("placeholder"), "Placeholder for search field is not correct");


    }
    @Test
    public void new_event_should_have_correct_placeholders() {
        driver.get("http://localhost:3000/new/");
        WebElement titleField = driver.findElement(By.id("title"));
        WebElement locationField = driver.findElement(By.id("location"));
        WebElement descriptionField = driver.findElement(By.id("description"));

        Assertions.assertAll(
                () -> Assertions.assertEquals("Miesięczne spotkanie",
                        titleField.getDomAttribute("placeholder"),
                        "Title field placeholder is not correct."),
                () -> Assertions.assertEquals("Sklep z kawą Joe",
                        locationField.getDomAttribute("placeholder"),
                        "Location field placeholder is not correct."),
                () -> Assertions.assertEquals("Cześć wszystkim, wybierzcie terminy, które Wam pasują!",
                        descriptionField.getDomAttribute("placeholder"),
                        "Description field placeholder is not correct.")
        );
    }
}

