package hurt_me_plenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCalculatorPage extends AbstractPage {

    @FindBy(id = "input_58")
    private WebElement numberOfInstancesInput;

    @FindBy(id = "select_value_label_55")
    private WebElement machineTypeField;

    @FindBy(xpath = "//*[@id=\"mainForm\"]/div[1]/div/md-card/md-card-content/div/div[1]/form/div[8]/div[1]/md-input-container/md-checkbox")
    private WebElement addGPUsButton;

    @FindBy(xpath = "//*[@id=\"select_value_label_169\"]/span[1]/div")
    private WebElement localSSDField;

    @FindBy(xpath = "//*[@id=\"select_value_label_332\"]/span[1]/div")
    private WebElement numberOfGPUsField;

    @FindBy(xpath = "//*[@id=\"select_value_label_333\"]/span[1]/div")
    private WebElement typeOfGPUField;

    @FindBy(xpath = "//*[@id=\"select_value_label_56\"]/span[1]/div")
    private WebElement datacenterLocationField;

    @FindBy(xpath = "//*[@id=\"select_value_label_57\"]/span[1]/div")
    private WebElement committedUsageField;

    @FindBy(xpath = "//button[text()='\n      Add to Estimate\n    ']")
    private WebElement addToEstimateButton;


    public GoogleCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public GoogleCalculatorPage writeNumberOfInstances(String numberOfInstances) {
        waitFor(numberOfInstancesInput);
        numberOfInstancesInput.sendKeys(numberOfInstances);
        return this;
    }

    public GoogleCalculatorPage chooseMachineType(String machineType) {
        waitForFieldAndChooseVariant(machineTypeField, machineType);
        return this;
    }

    public GoogleCalculatorPage chooseLocalSSD(String localSSD) {
        waitForFieldAndChooseVariant(localSSDField, localSSD);
        return this;
    }

    public GoogleCalculatorPage addGPUs() {
        waitFor(addGPUsButton);
        javascriptClick(addGPUsButton);
        return this;
    }

    public GoogleCalculatorPage chooseNumberOfGPUs(String numberOfGPUs) {
        waitForFieldAndChooseVariant(numberOfGPUsField, numberOfGPUs);
        return this;
    }

    public GoogleCalculatorPage chooseTypeOfGPU(String typeOfGPU) {
        waitForFieldAndChooseVariant(typeOfGPUField, typeOfGPU);
        return this;
    }

    public GoogleCalculatorPage chooseDatacenterLocation(String datacenterLocation) {
        waitForFieldAndChooseVariant(datacenterLocationField, datacenterLocation);
        return this;
    }

    public GoogleCalculatorPage chooseCommittedUsage(String committedUsage) {
        waitForFieldAndChooseVariant(committedUsageField, committedUsage);
        return this;
    }

    public GoogleCalculatorPage addToEstimate() {
        waitFor(addToEstimateButton);
        javascriptClick(addToEstimateButton);
        return this;
    }

    private void javascriptClick(WebElement webElement) {
        executor.executeScript("arguments[0].click();", webElement);
    }

    private void javascriptClick(By by) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(by));
        executor.executeScript("arguments[0].click();", driver.findElement(by));
    }

    private void waitForFieldAndChooseVariant(WebElement field, String variant) {
        waitFor(field);
        javascriptClick(field);
        javascriptClick(By.xpath("//div[text()='"+variant+"']"));
    }
}
