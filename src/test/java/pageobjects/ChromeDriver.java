package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class ChromeDriver implements WebDriver {
    /**
     * @param url
     */
    @Override
    public void get(String url) {

    }

    /**
     * @return
     */
    @Override
    public String getCurrentUrl() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getTitle() {
        return null;
    }

    /**
     * @param by
     * @return
     */
    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    /**
     * @param by
     * @return
     */
    @Override
    public WebElement findElement(By by) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getPageSource() {
        return null;
    }

    /**
     *
     */
    @Override
    public void close() {

    }

    /**
     *
     */
    @Override
    public void quit() {

    }

    /**
     * @return
     */
    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getWindowHandle() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public TargetLocator switchTo() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Navigation navigate() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Options manage() {
        return null;
    }
}
