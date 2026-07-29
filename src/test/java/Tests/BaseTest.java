package Tests;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.Homepage;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.WebOperations;
import webdriver.WebDriverSetup;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "browserSize"})
    public void init(@Optional("chrome") String browser, @Optional("desktop") String browserSize) {
        browser = System.getProperty("browser", browser);
        driver = browserSize.equalsIgnoreCase("mobile")
                ? WebDriverSetup.getMobileBrowserDriver(browser)
                : WebDriverSetup.getWebBrowserDriver(browser);
        Homepage.navigateToWebsite(MeetingPlaceConfig.getMeetingPlaceUrl());

    }

    @AfterMethod
    public void afterMethodStep(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("test failed.");
            ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage());
            ExtentTestManager.getTest().log(Status.FAIL, result.getName() + " Test Failed");

            if (driver != null) {
                WebOperations.addScreenshotToExtentReport();
            } else {
                ExtentTestManager.getTest().log(Status.FAIL, "Driver is crashed, screenshot can't be taken.");
            }

        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("Test Skipped.");
            ExtentTestManager.getTest().log(Status.SKIP, result.getThrowable().getMessage());
            ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " Test Skipped");
        } else {
            System.out.println("Test Passed.");
            WebOperations.addScreenshotToExtentReport();
            ExtentTestManager.getTest().log(Status.PASS, result.getName() + " Test Passed");
        }
        ExtentTestManager.endTest();
        if (driver != null) {
            driver.quit();
        }
    }

}

