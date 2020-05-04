package i_can_win_and_bring_it_on.page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    public abstract AbstractPage openPage();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
}
