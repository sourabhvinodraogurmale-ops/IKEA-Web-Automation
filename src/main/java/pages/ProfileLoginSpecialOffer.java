package pages;


import com.aventstack.extentreports.Status;
import org.openqa.selenium.interactions.Actions;
import report.ExtentTestManager;
import util.WebConstants;
import util.WebOperations;

import static webdriver.WebDriverSetup.driver;

public class ProfileLoginSpecialOffer {

    public static boolean profilebuttonclicking() {
        try {
            WebOperations.clickElement("profile");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the Profile Login Button");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Profile Login" + e.getMessage());
            return false;
        }
    }
    public static boolean profilesignup() {
        try {
       //     WebOperations.clickElement("profile");
            WebOperations.clickElement("profilesignup");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the Signup  Button");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Profile Login" + e.getMessage());
            return false;
        }

    }

    public static boolean profileInInputField(String signInNameEmail) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Filling details for Profile");
            WebOperations.clickAndType("nameinputfieldprofile", signInNameEmail);
            Actions actions = new Actions(driver);
          //  actions.sendKeys(Keys.TAB).perform();

            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return false;
        }}
    public static boolean Continuebutton() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the continue button.");
            WebOperations.clickElement("Continue");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click continue button: " + e.getMessage());
            return false;
        }
    }
    public static boolean Profilepass(String Password) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Entering the password.");
            WebOperations.clickAndType("profilepassword", Password);
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click continue button: " + e.getMessage());
            return false;
        }
    }
    public static boolean Profilelogin() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the login button.");
            WebOperations.clickElement("Log in");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click continue button: " + e.getMessage());
            return false;
        }
    }
    public static boolean Profilmagiclink() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the login button.");
            WebOperations.clickElement("Magic Link");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Magic Link button: " + e.getMessage());
            return false;
        }
    }
  public static boolean ProfilLoginsuccess() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the login button.");
            WebOperations.clickElement("Loginsuccess");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Magic Link button: " + e.getMessage());
            return false;
        }
    }
    public static boolean Profileemailmagientery(String emailid) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the login button.");
            WebOperations.clickAndType( "profileemail",emailid);
            WebOperations.clickElement("enter");
            WebOperations.clickElement( "Magiclinkmail");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Magic Link button: " + e.getMessage());
            return false;
        }}
        public static boolean Profileemailmagiclink() {
            try {
                ExtentTestManager.getTest().log(Status.INFO, "Clicking the login button.");
                Thread.sleep(2000);
                WebOperations.clickElement( "Magiclinkmail");
                WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
                return true;
            } catch (Exception e) {
                ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Magic Link button: " + e.getMessage());
                return false;
            }

    }
        public static boolean Profilemodify( ) {
            try {
                ExtentTestManager.getTest().log(Status.INFO, "Clicking the login button.");
                WebOperations.clickElement("profilemodify");
                WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
                return true;
            } catch (Exception e) {
                ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Magic Link button: " + e.getMessage());
                return false;
            }
    }

    public static boolean Profilemodifyclick( ) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the profile button.");
            WebOperations.clickElement("profilemodifyclick");
            ;
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Magic Link button: " + e.getMessage());
            return false;
        }
    }
    public static boolean Profilemodifyclickname( ) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the profile button.");
            WebOperations.clickElement("Profilemodifyclickname");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Magic Link button: " + e.getMessage());
            return false;
        }
    }
    public static boolean profilenamemodify(String update ) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the profile button.");
            WebOperations.clickAndType("updatename",update);
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Magic Link button: " + e.getMessage());
            return false;
        }
    }
    public static boolean profilesave() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the profile save button.");
            WebOperations.clickElement("profilemodifysave");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Save Link button: " + e.getMessage());
            return false;
        }
    }
    public static boolean profileLogout() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the profile Logout button.");
            WebOperations.clickElement("profileLogout");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Logout button: " + e.getMessage());
            return false;
        }
    }
    public static boolean Offerclaim() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the profile Logout button.");
            WebOperations.clickElement("IKEAofferclick");
            WebOperations.clickElement("ClaimOffer");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Logout button: " + e.getMessage());
            return false;
        }
    }
}