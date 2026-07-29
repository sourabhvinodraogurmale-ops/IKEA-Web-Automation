package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import report.ExtentTestManager;
import util.WebOperations;

import static webdriver.WebDriverSetup.driver;

public class NewsLetterForm {

    public static boolean addUserNameInInputField(String userName) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Filling details for newsletter subscription form");
            WebOperations.clickAndType("nameinputfield", userName);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return false;
        }
    }

    }