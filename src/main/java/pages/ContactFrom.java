package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import report.ExtentTestManager;
import util.WebOperations;
import static webdriver.WebDriverSetup.driver;

public class ContactFrom {


    public static boolean contactUserNameInInputField(String userName) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Filling details for Contact form");

            // MainMenu.clickContactusLink();
            WebOperations.clickAndType("contactnameinputfield", userName);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return false;
        }
    }

    public static boolean contactEmailInInputField(String Contuseremail) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Filling details for Contact form");
            WebOperations.clickAndType("contactemailinputfield", Contuseremail);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            WebOperations.clickAndType("contactconfirmemailinputfield", Contuseremail);
            actions.sendKeys(Keys.TAB).perform();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return false;
        }

    }
    public static boolean contactMessageInInputField(String message) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Filling details for Contact form");
            WebOperations.clickAndType("contactusMessageinputfield", message);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();

            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return false;
        }
    }
    public static boolean contactPhoneInInputField(String Contemporaneous) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Filling details for Contact form");
            WebOperations.clickAndType("contacphoneinputfield", Contemporaneous);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return false;
        }
    }

    public static boolean skippingAllTheFieldsEmpty() {
        try {

            ExtentTestManager.getTest().log(Status.INFO, "Attempting to leave all fields empty");
            WebOperations.clickElement("nameinputfield");
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            actions.sendKeys(Keys.TAB).perform();
            actions.sendKeys(Keys.TAB).perform();
            ExtentTestManager.getTest().log(Status.INFO, "Clicking checkbox");
            WebOperations.clickElement("checkbox");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to fill the form: " + e.getMessage());
            return false;
        }
    }

    public static boolean disablcontactussubmitbebutton(String Contactsubmit) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Filling details for Contact form");
            WebOperations.clickAndType("disablcontactussubmitbebutton", Contactsubmit);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return false;
        }
}
    public static boolean Verifycontactus() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the check box and submit button");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking checkbox");
            WebOperations.clickElement("checkbox");
            WebOperations.clickElement("contactusSubmit");

            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return false;
        }
    }
}