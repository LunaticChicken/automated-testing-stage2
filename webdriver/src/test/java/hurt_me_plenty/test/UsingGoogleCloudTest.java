package hurt_me_plenty.test;

import hurt_me_plenty.page.GoogleCalculatorPage;
import hurt_me_plenty.page.GoogleCloudHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class UsingGoogleCloudTest {

    private WebDriver driver;
    GoogleCalculatorPage googleCalculatorPage;

    @BeforeSuite(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test (priority = -2)
    public void calculatorIsOpened() {
        googleCalculatorPage = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForTerms("Google Cloud Platform Pricing Calculator")
                .clickCalculatorLink();
    }

    @Test (priority = -1)
    public void searchParametersEntered() {
        switchToCalculatorFrame();

        googleCalculatorPage = new GoogleCalculatorPage(driver)
                .writeNumberOfInstances("4")
                .chooseMachineType("\n              n1-standard-8 (vCPUs: 8, RAM: 30GB)\n            ")
                .addGPUs()
                .chooseNumberOfGPUs("\n                4\n              ")
                .chooseTypeOfGPU("\n                NVIDIA Tesla V100\n              ")
                .chooseLocalSSD("\n            2x375 GB\n          ")
                .chooseDatacenterLocation("\n            Frankfurt (europe-west3)\n          ")
                .chooseCommittedUsage("1 Year")
                .addToEstimate();
    }

    @Test
    public void enteredVMClassEqualsToEstimate() {
        String text = driver.findElement(By.xpath("//*[@id=\"compute\"]/md-list/md-list-item[2]/div")).getText();
        Assert.assertEquals(text, "VM class: regular");
    }

    @Test
    public void enteredInstanceTypeEqualsToEstimate() {
        String text = driver.findElement(By.xpath("//*[@id=\"compute\"]/md-list/md-list-item[3]/div")).getText();
        Assert.assertEquals(text, "Instance type: n1-standard-8");
    }

    @Test
    public void enteredRegionEqualsToEstimate() {
        String text = driver.findElement(By.xpath("//*[@id=\"compute\"]/md-list/md-list-item[4]/div")).getText();
        Assert.assertEquals(text, "Region: Frankfurt");
    }

    @Test
    public void enteredSSDSpaceEqualsToEstimate() {
        String text = driver.findElement(By.xpath("//*[@id=\"compute\"]/md-list/md-list-item[5]/div")).getText();
        Assert.assertEquals(text, "Total available local SSD space 2x375 GiB");
    }

    @Test
    public void enteredCommittedUsageEqualsToEstimate() {
        String text = driver.findElement(By.xpath("//*[@id=\"compute\"]/md-list/md-list-item[6]/div")).getText();
        Assert.assertEquals(text, "Commitment term: 1 Year");
    }

    @Test
    public void expectedCostEqualsToEstimate() {
        String text = driver.findElement(By.xpath("//*[@id=\"resultBlock\"]/md-card/md-card-content/div/div/div/h2/b")).getText();
        Assert.assertEquals(text, "Total Estimated Cost: USD 1,082.77 per 1 month");
    }

    private void switchToCalculatorFrame() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cloud-site\"]/devsite-iframe/iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"cloud-site\"]/devsite-iframe/iframe")))
                .switchTo().frame(driver.findElement(By.id("myFrame")));
    }

    @AfterSuite(alwaysRun = true)
    public void browserTearDown() throws InterruptedException {
        Thread.sleep(15000);
        driver.quit();
    }
}
