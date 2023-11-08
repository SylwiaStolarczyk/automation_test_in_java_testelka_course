import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminPanelTests extends BaseTests{
    @BeforeEach
    public void adminLogin(){
        driver.get(baseUrl + "/wp-admin/");
        driver.findElement(By.id("user_login")).sendKeys("admin");
        driver.findElement(By.id("user_pass")).sendKeys("admin");
        driver.findElement(By.id("wp-submit")).click();
    };
    @Test
    public void  admin_successful_login_should_display_my_account_content() {
        driver.get(baseUrl + "/my-account/");

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
    public void product_virtual_should_not_shipping(){

        driver.get("http://localhost:8080/wp-admin/post-new.php?post_type=product");
        driver.findElement(By.id("_virtual")).click();
        WebElement shippingOptions = driver.findElement(By.className("shipping_options"));
        Assertions.assertFalse(shippingOptions.isDisplayed(), "Shipping tab is still displayed but should be hidden. The product is a virtual product");
    }
    @Test
    public void select_all_products_should_select_each_of_them(){

        driver.get("http://localhost:8080/wp-admin/edit.php?post_type=product");
        driver.findElement(By.id("cb-select-all-1")).click();

        List<WebElement> productCheckboxes = driver.findElements(By.name("post[]"));
        //productCheckboxes.get(0).isSelected();
        long numberOfSelectedCheckboxes = productCheckboxes.stream().filter(checkbox -> checkbox.isSelected()).count();
        Assertions.assertEquals(7, numberOfSelectedCheckboxes, "Not all the product checkboxes were selected");

    }
}
