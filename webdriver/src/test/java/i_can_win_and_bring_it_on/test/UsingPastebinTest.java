package i_can_win_and_bring_it_on.test;

import i_can_win_and_bring_it_on.page.PastebinHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class UsingPastebinTest {

    private WebDriver driver;
    ArrayList<String> tabs;
    String pasteTitle = "how to gain dominance among developers";
    String code = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";

    @BeforeSuite(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('');");
        tabs = new ArrayList<> (driver.getWindowHandles());
    }

//    @Test (priority = -2)
//    public void iCanWinTaskIsCreated() {
//        driver.switchTo().window(tabs.get(0));
//
//        new PastebinHomePage(driver)
//                .openPage()
//                .writeCode("Hello from WebDriver")
//                .choosePasteExpiration("10 Minutes")
//                .writePasteTitle("helloweb")
//                .createPaste();
//    }

    @Test (priority = -1)
    public void bringItOnTaskIsCreated() {
        driver.switchTo().window(tabs.get(1));

        new PastebinHomePage(driver)
                .openPage()
                .writeCode(code)
                .chooseSyntaxHighlighting("Bash")
                .choosePasteExpiration("10 Minutes")
                .writePasteTitle(pasteTitle)
                .createPaste();
    }

    @Test
    public void pageTitleMatchesPasteTitle() {
        Assert.assertTrue(driver.getTitle().matches(".*" + pasteTitle + ".*"));
    }

    @Test
    public void syntaxHighlighterIsBash() {
        boolean isSyntaxHighlighterBash = driver.findElements(By.linkText("Bash")).size() > 0;
        Assert.assertTrue(isSyntaxHighlighterBash);
    }

    @Test
    public void pasteCodeMatchesEnteredCode() {
        WebElement pasteCodeElement = driver.findElement(By.id("paste_code"));
        boolean isPasteCodeMatchesEnteredCode = pasteCodeElement.getText().equals(code);
        Assert.assertTrue(isPasteCodeMatchesEnteredCode);
    }

    @AfterSuite(alwaysRun = true)
    public void browserTearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
