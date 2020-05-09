package hurt_me_plenty.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    JavascriptExecutor executor;
    protected final int WAIT_TIMEOUT_SECONDS = 30;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor)driver;
    }

    public void waitFor(WebElement webElement) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(webElement));
    }
}
