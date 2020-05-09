package hurt_me_plenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudSearchPage extends AbstractPage {

    @FindBy(xpath = "//b[text()='Google Cloud Platform Pricing Calculator']")
    private WebElement calculatorLink;

    public GoogleCloudSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public GoogleCalculatorPage clickCalculatorLink() {
        waitFor(calculatorLink);
        calculatorLink.click();
        return new GoogleCalculatorPage(driver);
    }
}
