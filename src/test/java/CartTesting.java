import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartTesting extends BaseTests{

    private final By addToCartButtonLocator = By.name("add-to-cart");
    private final String chemicalAnalysisSlug = "/product/the-elements-of-qualitative-chemical-analysis-vol-1-parts-1-and-2-by-stieglitz/";
    private final String historyOfAstronomySlug =  "/product/a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/";
        @Test
        public void update_quantity_in_cart_should_update_total_price_searching_of_element_in_element() {
            driver.get(baseUrl + "/product" + historyOfAstronomySlug);
            driver.findElement(addToCartButtonLocator).click();
            driver.get(baseUrl + "/cart/");
            WebElement quantityField = driver.findElement(By.className("qty"));
            quantityField.clear();
            quantityField.sendKeys("2");
            driver.findElement(By.name("update_cart")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));//odpytywanie ileś razy w tracie 5 sekund
            //metoda untill przestaje odpytywać, aż funkcja zwróci prawdę, czyli zwróci cokolwiek (wynik niepusty i nie false)
            wait.until(driver -> driver.findElements(By.className("blockUI")).size() == 0); //ten untill działa tak samo jak ten wyższy
            WebElement total = driver.findElement(By.className("order-total"))
                    .findElement(By.className("amount"));
            Assertions.assertEquals("24,00 €", total.getText(), "Total price not correct");
        }

        @Test
        public void add_product_to_cart_should_header_show_product_price() {
            driver.get(baseUrl + "/product" + historyOfAstronomySlug);
            driver.findElement(addToCartButtonLocator).click();
            driver.findElement(By.className("wc-block-mini-cart_button")).click();
            //WebElement totalPrice = driver.findElement(By.className("wc-block-components-product-price_value"));
            WebElement totalPrice = wait.until(driver -> driver.findElement(By.className("wc-block-components-totals__value")));
            Assertions.assertEquals("12,00 €", totalPrice.getText(), "The price displayed in minicart is not correct");

        }

        @Test
        //@DisplayName("Adding one product to cart should show product price in header")
        public void add_product_to_cart_should_header_show_product() {
            driver.get(baseUrl + "/product" + historyOfAstronomySlug);
            driver.findElement(addToCartButtonLocator).click();
            WebElement miniCart = driver.findElement(By.className("wc-block-mini-cart__amount"));
            Assertions.assertEquals("12,00 €", miniCart.getText(),
                    "The price displayed in header is not correct");

        }
        @Test
        public void update_quantity_in_cart_should_update_total_price(){
            driver.get(baseUrl + "/product" + historyOfAstronomySlug);
            driver.findElement(addToCartButtonLocator).click();
            driver.get(baseUrl + "/cart/");
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
            driver.get(baseUrl + "/product" + chemicalAnalysisSlug);
            driver.findElement(addToCartButtonLocator).click();
            driver.get("http://localhost:8080/cart/");
            WebElement updateButton = driver.findElement(By.name("update_cart"));
            Assertions.assertFalse(updateButton.isEnabled(),"Update button is enabled while it shouldn't. There were no changes in cart.");
        }
    }

