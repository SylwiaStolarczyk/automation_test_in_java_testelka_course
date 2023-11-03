import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExampleTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() { driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        // Duration implicitWait = driver.manage().timeouts().getImplicitWaitsTimeout();
        //Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
    }
    @AfterEach
    public  void  quitDriver() { driver.quit();}

    @Test
    public void add_product_to_cart_should_header_show_product_price(){
        driver.get("http://localhost:8080/product/a-popular-history-of-astronomy" +
                "-during-the-nineteenth-century-by-agnes-m-clerke/");
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();
        driver.findElement(By.className("wc-block-mini-cart_button")).click();
        //WebElement totalPrice = driver.findElement(By.className("wc-block-components-product-price_value"));
        WebElement totalPrice = wait.until(driver->driver.findElement(By.className("wc-block-components-totals__value")));
        Assertions.assertEquals("12,00 €", totalPrice.getText(), "The price displayed in minicart is not correct");

    }

    @Test
    //@DisplayName("Adding one product to cart should show product price in header")
    public void add_product_to_cart_should_header_show_product() {
                driver.get("http://localhost:8080/product/a-popular-history-of-astronomy" +
                "-during-the-nineteenth-century-by-agnes-m-clerke/");
            WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));

            addToCartButton.click();
        WebElement miniCart = driver.findElement(By.className("wc-block-mini-cart__amount"));
        Assertions.assertEquals("12,00 €", miniCart.getText(),
                "The price displayed in header is not correct");

    }
    @Test
    public void  admin_successful_login_should_display_my_account_content() {
        driver.get("http://localhost:8080/my-account/");

        WebElement signInWithUsername = driver.findElement(By.name("username"));
        signInWithUsername.clear();
        signInWithUsername.sendKeys("admin");

        WebElement signInWithPassword = driver.findElement(By.id("password"));
        signInWithPassword.sendKeys("admin");

        WebElement logInButton = driver.findElement(By.name("login"));
        logInButton.click();


        Assertions.assertDoesNotThrow(() -> driver.findElement(By.className("woocommerce-MyAccount-content")),"The my account is missing. User probably is not logged in.");

    }
    @Test
    public void update_quantity_in_cart_should_update_total_price(){
        driver.get("http://localhost:8080/product/a-popular-history-of-astronomy" +
                "-during-the-nineteenth-century-by-agnes-m-clerke/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");
        WebElement quantityField = driver.findElement(By.className("qty"));
        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(By.name("update_cart")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));//odpytywanie ileś razy w tracie 5 sekund
        //metoda untill przestaje odpytywać, aż funkcja zwróci prawdę, czyli zwróci cokolwiek (wynik niepusty i nie false)
        wait.until(driver -> driver.findElements(By.className("blockUI")).size()==0); //ten untill działa tak samo jak ten wyższy
        WebElement total = driver.findElement(By.className("order-total"));
        Assertions.assertEquals("Total 24,00 €", total.getText(), "Total price not correct");
    }
    @Test
    public void cart_not_change_should_update_button_disable(){
        driver.get("http://localhost:8080/product/the-elements-of-qualitative-chemical-analysis-vol-1-parts-1-and-2-by-stieglitz/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");
        WebElement updateButton = driver.findElement(By.name("update_cart"));
        Assertions.assertFalse(updateButton.isEnabled(),"Update button is enabled while it shouldn't. There were no changes in cart.");
    }
    @Test
    public void product_virtual_should_not_shipping(){
        driver.get("http://localhost:8080/wp-admin/");
        driver.findElement(By.id("user_login")).sendKeys("admin");
        driver.findElement(By.id("user_pass")).sendKeys("admin");
        driver.findElement(By.id("wp-submit")).click();
        driver.get("http://localhost:8080/wp-admin/post-new.php?post_type=product");
        driver.findElement(By.id("_virtual")).click();
        WebElement shippingOptions = driver.findElement(By.className("shipping_options"));
        Assertions.assertFalse(shippingOptions.isDisplayed(), "Shipping tab is still displayed but should be hidden. The product is a virtual product");
    }
    @Test
    public void select_all_posts_should_select_each_of_them(){
        driver.get("http://localhost:8080/wp-admin/");
        driver.findElement(By.id("user_login")).sendKeys("admin");
        driver.findElement(By.id("user_pass")).sendKeys("admin");
        driver.findElement(By.id("wp-submit")).click();
        driver.get("http://localhost:8080/wp-admin/edit.php?post_type=product");
        driver.findElement(By.id("cb-select-all-1")).click();

        List<WebElement> productCheckboxes = driver.findElements(By.name("post[]"));
        //productCheckboxes.get(0).isSelected();
        long numberOfSelectedCheckboxes = productCheckboxes.stream().filter(checkbox -> checkbox.isSelected()).count();
        Assertions.assertEquals(7, numberOfSelectedCheckboxes, "Not all the product checkboxes were selected");

    }
    @Test
    public void search_field_should_have_placeholder_text(){
        driver.get("http://localhost:8080/");
        WebElement searchField = driver.findElement(By.id("wc-block-search__input-1"));
        Assertions.assertEquals("Search products…", searchField.getDomAttribute("placeholder"), "Placeholder for search field is not correct");

    }

    @Test
    public void new_product_quantity_typed_in_should_product_quantity_changed(){
        driver.get("http://localhost:8080/product/a-popular-history-of-astronomy" +
                "-during-the-nineteenth-century-by-agnes-m-clerke/");
        WebElement productQuantity = driver.findElement(By.className("qty"));
        productQuantity.clear();
        productQuantity.sendKeys("3");

        Assertions.assertEquals("3", productQuantity.getDomProperty("value"), "Product quantity not changed");
    }
}