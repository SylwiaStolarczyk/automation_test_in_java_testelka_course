import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Materrmost {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterEach
    public void  quitDriver() { driver.quit();}
    @Test
    public void forbidden_acces_after_login_without_account(){
        driver.get("http://localhost:8065/login");
        //wait.until(driver -> driver.findElements(By.className("get-app__continue")).size()==1);
        WebElement viewInBrowser = driver.findElement(By.className("get-app__continue"));
        viewInBrowser.click();
        //wait.until(driver -> driver.findElements(By.className("alternate-link__link")).size()==1);
        WebElement clickIdontHaveAccount = driver.findElement(By.className("alternate-link__link"));
        clickIdontHaveAccount.click();
        WebElement contactAdmin = driver.findElement(By.className("AccessProblem__title"));
        Assertions.assertEquals("Contact your workspace admin", contactAdmin.getText(), "You cannot login without signing in first");
    }
    @Test
    public void login_happy_path(){
        driver.get("http://localhost:8065/login");
        WebElement viewInBrowser = driver.findElement(By.className("get-app__continue"));
        viewInBrowser.click();
        WebElement userLogin = driver.findElement(By.id("input_loginId"));
        userLogin.sendKeys("sit");
        WebElement userPassword = driver.findElement(By.id("input_password-input"));
        userPassword.sendKeys("8etuSrAMPEdYx3M");
        WebElement clickLogin = driver.findElement(By.id("saveSetting"));
        clickLogin.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.id("loadingSpinner"), 0));
        Assertions.assertEquals("localhost:8065/qa/channels/town-square", driver.getCurrentUrl(), "URL address of the main page is not what expected");

    }
    @Test
    public void successful_login_should_show_town_square_page() {
        driver.get("http://localhost:8065/login/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("get-app__continue"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input_loginId"))).sendKeys("sit");
        driver.findElement(By.id("input_password-input")).sendKeys("8etuSrAMPEdYx3M");
        driver.findElement(By.id("saveSetting")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.id("loadingSpinner"), 0));
        Assertions.assertEquals("http://localhost:8065/qa/channels/town-square",
                driver.getCurrentUrl(), "User is not on Town Square page.");
    }
    @Test
    public void save_button_is_disabled_when_no_changes_in_settings(){
        driver.get("http://localhost:8065/login");
        WebElement viewInBrowser = driver.findElement(By.className("get-app__continue"));
        viewInBrowser.click();
        WebElement userLogin = driver.findElement(By.id("input_loginId"));
        userLogin.sendKeys("sit");
        WebElement userPassword = driver.findElement(By.id("input_password-input"));
        userPassword.sendKeys("8etuSrAMPEdYx3M");
        driver.findElement(By.id("saveSetting")).click();
        driver.get("http://localhost:8065/admin_console/user_management/permissions/system_scheme");
        WebElement saveButton = driver.findElement(By.className("save-button"));
        Assertions.assertFalse(saveButton.isEnabled(), "Save button is enabled even though no changes were made");
    }

}