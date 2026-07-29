package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import report.ExtentTestManager;
import util.WebConstants;
import util.WebOperations;

import java.util.List;
import java.util.Random;

import static webdriver.WebDriverSetup.driver;


public class MainMenu {

    public static boolean clickMeetingplaceLogo() {
        try {
            WebOperations.clickElement("meetingplacelogomenu");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Meeting place logo");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Meeting place logo: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickMeetingplaceLogoHomepage() {
        try {
            WebElement element = WebOperations.getWebElement("meetingplacelogohomepage");
            org.openqa.selenium.Point location = element.getLocation();
            org.openqa.selenium.Dimension size = element.getSize();
            int x = location.getX() + size.getWidth() / 2;
            int y = location.getY() + size.getHeight() / 2;
            Actions action = new Actions(driver);
            action.moveByOffset(x, y).click().perform();
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Meeting place logo");
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            return true;
        } catch (Exception e) {
            // Log any error and return false
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Meeting place logo: " + e.getMessage());
            return false;
        }
    }



    public static boolean clickShopsLink() {
        try {
            WebOperations.clickElement("shopslink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Shops Link");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Shops Link: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickPromotionLink() {
        try {
            WebOperations.clickElement("offerslink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Offers/Promotions Link");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Offers/Promotion Link: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickPackageDealsLink() {
        try {
            WebOperations.clickElement("packagedealslink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Package deals  Link");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click package deals Link: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickMenuShopsButton() {
        try {
            WebOperations.clickElement("menushopsbutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Menu Tenant Button");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click Menu Tenant Button: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickMenuFAndBButton() {
        try {
            WebOperations.clickElement("menufandbbutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Menu Food & Beverage  Button");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click Menu Food & Beverage Button: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickMenuOccasionsButton() {
        try {
            WebOperations.clickElement("menuoccasionsbutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Menu Occasion Button");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click Menu Occasion Button: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickFoodAndBeverageLink() {
        try {
            WebOperations.clickElement("foodandbeveragelink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Food and Beverage Link");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Food and Beverage Link: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickOffersLink() {
        try {
            WebOperations.clickElement("offerslink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Offers Link");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Offers Link: " + e.getMessage());
            return false;
        }
    }



    public static boolean clickEventsLink() {
        try {
            WebOperations.clickElement("eventslink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Events Link");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Events Link: " + e.getMessage());
            return false;
        }
    }


    public static boolean clickServicesLink() {
        try {
//            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            WebOperations.clickElement("serviceslink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Services Link");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Services Link: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickFloorPlanLink() {
        try {
            WebOperations.clickElement("floorplanlink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Floor Plan Link");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Floor Plan Link: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickFloorPlanInput() {
        try {
            WebOperations.clickElement("floorplaninput");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Floor Plan Input");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Floor Plan Input: " + e.getMessage());
            return false;
        }
    }


    public static boolean clickNewsletterSubscriptionLink() {
        try {
            WebOperations.clickElement("newslettersubscriptionlink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the Newsletter Subscription link");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click the Newsletter Subscription link: " + e.getMessage());
            return false;
        }
    }


    public static String clickFirstSubServiceAndGetServiceName() {
        try {
            List<WebElement> subServices = WebOperations.getWebElements("subServices");
            int randomIndex = WebOperations.getRandomIndex(subServices.size());
            String serviceName = subServices.get(randomIndex).getText();
            subServices.get(randomIndex).click();
            ExtentTestManager.getTest().log(Status.INFO, "Retrieving first Subservice and clicking on it");
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            return serviceName;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to retrieve and click the first Subservice: " + e.getMessage());
            return null;
        }
    }

    public static boolean clickSubscribeButton() {
        try {
            WebOperations.clickElement("enabledsubscribebutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Subscribe Button");
            WebOperations.wait(10000);  // Wait for 10 seconds
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Subscribe Button: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickCloseButton() {
        try {
            WebOperations.clickElement("closebutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Close Button");

            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Close Button: " + e.getMessage());
            return false;
        }
    }


    public static String fillNewsSubscriptionFormAndAgreeToTerms() {
        try {
            Random rand = new Random();
            //int randNumber = rand.nextInt(1000);
            String randomName = "testUser";
            String randomEmail = "testUser"  + "@mailinator.com";
            ExtentTestManager.getTest().log(Status.INFO, "Filling details for newsletter subscription form");
            WebOperations.clickAndType("nameinputfield", randomName);
            WebOperations.clickAndType("emailinputfield", randomEmail);
            ExtentTestManager.getTest().log(Status.INFO, "Clicking checkbox");
            WebOperations.clickElement("checkbox");
            return randomName;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to fill the form: " + e.getMessage());
            return null;
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

    public static boolean verifyIconSizeOnHover() {
        WebElement button = WebOperations.getWebElement("floorplanservice");
        WebElement svgIcon = WebOperations.getWebElement("floorplanserviceicon");

        // Get the icon size before hover
        Dimension sizeBeforeHover = svgIcon.getSize();
        int widthBeforeHover = sizeBeforeHover.getWidth();
        int heightBeforeHover = sizeBeforeHover.getHeight();

        // Hover over the button
        Actions action = new Actions(driver);
        action.moveToElement(button).perform();

        // Get the icon size after hover
        Dimension sizeAfterHover = svgIcon.getSize();
        int widthAfterHover = sizeAfterHover.getWidth();
        int heightAfterHover = sizeAfterHover.getHeight();

        if (widthBeforeHover != widthAfterHover || heightBeforeHover != heightAfterHover) {
            return false;
        }
        return true;
    }
    public static boolean clickContactusLink() {
        try {
            WebOperations.clickElement("Contactlink");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking contact Link");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click contact Link: " + e.getMessage());
            return false;
        }}
}
