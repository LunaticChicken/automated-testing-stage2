package i_can_win_and_bring_it_on.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://pastebin.com/";

    @FindBy(id = "paste_code")
    private WebElement codeInput;

    @FindBy(xpath = "//span[text()='None']")
    private WebElement syntaxHighlightingField;

    @FindBy(xpath = "//span[text()='Never']")
    private WebElement pasteExpirationField;

    @FindBy(name = "paste_name")
    private WebElement pasteTitleInput;

    @FindBy(name = "submit")
    private WebElement createPasteButton;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PastebinHomePage writeCode(String code) {
        waitFor(codeInput);
        codeInput.sendKeys(code);
        return this;
    }

    public PastebinHomePage chooseSyntaxHighlighting(String highlighting) {
        chooseLiElementInField(syntaxHighlightingField, highlighting);
        return this;
    }

    public PastebinHomePage choosePasteExpiration(String expirationTimeChoice) {
        chooseLiElementInField(pasteExpirationField, expirationTimeChoice);
        return this;
    }

    public PastebinHomePage writePasteTitle(String title) {
        waitFor(pasteTitleInput);
        pasteTitleInput.sendKeys(title);
        return this;
    }

    public void createPaste() {
        waitFor(createPasteButton);
        createPasteButton.click();
    }

    private void chooseLiElementInField(WebElement field, String choice) {
        waitFor(field);
        field.click();
        By byChoiceXpath = By.xpath("//li[text()='" + choice + "']");
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(byChoiceXpath));
        driver.findElement(byChoiceXpath).click();
    }

    private void waitFor(WebElement webElement) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(webElement));
    }
}
