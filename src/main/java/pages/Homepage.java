package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import report.ExtentTestManager;
import util.WebConstants;
import util.WebOperations;

import java.time.Duration;

import static util.WebOperations.*;
import static webdriver.WebDriverSetup.driver;


public class Homepage {

    public static boolean navigateToWebsite(String url) {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean clickAcceptCookies() {
        try {
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            WebOperations.clickElement("acceptallcookies");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Accept all cookies");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Accept cookies: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickCookiesSetting() {
        try {
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            WebOperations.clickElement("cookiessetting");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking cookies settings");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click cookies settings: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickRejectCookies() {
        try {
             WebOperations.clickElement("rejectallcookies");
             ExtentTestManager.getTest().log(Status.INFO, "Clicking Reject all cookies");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click reject all cookies: " + e.getMessage());
            return false;
        }
    }


    public static boolean ClickPackageDealsCard() {
        try {
            WebOperations.scrollToElement("packagedealscard");
            WebOperations.clickElement("packagedealscard");
            ExtentTestManager.getTest().log(Status.INFO, "clicking the 'Package Deals Card' element");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click the 'Package Deals Card'. Error: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickRandomCardOnHomepage() {
        try {
            int totalCardsOnHomepage = WebOperations.getWebElements("homepagecards").size();
            if (totalCardsOnHomepage > 0) {
                int indexValue = WebOperations.getRandomIndex(totalCardsOnHomepage);
                WebOperations.scrollToElement("homepagecards");
                WebOperations.addScreenshotToExtentReport();
                String cardTitle = WebOperations.getWebElements("homepagecards").get(indexValue).getText();
                WebOperations.getWebElements("homepagecards").get(indexValue).click();
                ExtentTestManager.getTest().log(Status.INFO, "Clicking on a " + cardTitle + " card on the homepage.");
                return true;
            } else {
                ExtentTestManager.getTest().log(Status.WARNING, "No cards found on the homepage.");
                return false;
            }
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click a random card on the homepage. Error: " + e.getMessage());
            return false;
        }
    }


    public static int navigateBackFromBreadcrumbTrail() {
        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
        int breadcrumbTrailsSize = WebOperations.getWebElements("breadcrumbtrail").size();
        WebOperations.addScreenshotToExtentReport();
        WebOperations.getWebElements("breadcrumbtrail").get(breadcrumbTrailsSize - 2).click();
        ExtentTestManager.getTest().log(Status.INFO, "Clicking the second-to-last breadcrumb '" + WebOperations.getWebElements("breadcrumbtrail").get(breadcrumbTrailsSize - 2).getText() + "' to navigate back");
        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
        return breadcrumbTrailsSize;
    }


    public static boolean clickRejectAllCookiesSettings() {
        try {
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            WebOperations.clickElement("rejectallcookiessettings");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Reject all cookies");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click reject all cookies: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickOpenSearchIcon() {
        try {
            WebOperations.wait(5000);
            if(WebOperations.isElementPresent("newsletterclosebutton")){
                WebOperations.clickElement("newsletterclosebutton");
            }
            WebOperations.clickElement("opensearch");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Open search");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Open Search Icon: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickNextBannerButton() {
        try {

            WebOperations.clickElement("nextbannerbutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Next Banner Button");

            if (WebOperations.isElementPresent("newsletterclosebutton")) {
                WebOperations.clickElement("newsletterclosebutton");
                ExtentTestManager.getTest().log(Status.INFO, "Closed newsletter popup after clicking Next Banner Button");
            }
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Next Banner Button: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickPreviousBannerButton() {
        try {
            WebOperations.clickElement("previousbannerbutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Previous Banner Button");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Previous Banner Button: " + e.getMessage());
            return false;
        }
    }


    public static boolean clickAndTypeInSearchInput(String inputValue) {

        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking and typing " + inputValue + " into the search input box.");
            WebOperations.clickAndType("searchinputbox", inputValue);
            Homepage.submitSearch();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click and typing " + inputValue + " int the search input box: " + e.getMessage());
            return false;
        }
    }

    public static boolean submitSearchAndClickEvent() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the search submit button.");
            WebOperations.clickElement("searchsubmit");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the event link after search.");
            WebOperations.clickElement("eventstext");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to submit search and click event: " + e.getMessage());
            return false;
        }
    }

    public static boolean submitSearchAndClickShop() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the search submit button.");
            WebOperations.clickElement("searchsubmit");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the shops link after search.");
            WebOperations.clickElement("shopstext");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to submit search and click shop: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickNewsLetterFromFooter() {
        try {
            WebOperations.scrollToElement("newsletterfooterbutton");
            WebOperations.clickElement("newsletterfooterbutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Newsletter subscription from the footer.");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Newsletter subscription from the footer: " + e.getMessage());
            return false;
        }
    }



    public static boolean submitSearch() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking the search submit button.");
            WebOperations.clickElement("searchsubmit");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to submit search and click tenant: " + e.getMessage());
            return false;
        }
    }


    public static String clickHomepageBannerAndVerifyText() {
        try {
            String bannerText = WebOperations.getWebElements("bannerheading").get(0).getText();
            ExtentTestManager.getTest().log(Status.INFO, "Clicking on banner : " + bannerText);
            WebOperations.getWebElements("openbanner").get(0).click();
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return bannerText;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click the homepage banner: " + e.getMessage());
            return null;
        }
    }

    public static String clickBuyAtIKEAButtonAndReturnInitialWindow() {
        try{
            WebOperations.scrollToElement("buyatikeabtn");
            WebOperations.clickElement("buyatikeabtn");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking buy at ikea button");
            String initialWindow = driver.getWindowHandle();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            return initialWindow;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click the buy at IKEA button: " + e.getMessage());
            return null;
        }
    }

    public static String clickBuyAtIKEADetailPageButtonAndReturnInitialWindow() {
        try{
            WebOperations.scrollToElement("buyatikeabtndetailpage");
            WebOperations.clickElement("buyatikeabtndetailpage");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking buy at ikea button");
            String initialWindow = driver.getWindowHandle();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            return initialWindow;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click the buy at IKEA button: " + e.getMessage());
            return null;
        }
    }

    public static void navigateToMailinator(String userName) {
        try {
            WebOperations.addScreenshotToExtentReport();
            ExtentTestManager.getTest().log(Status.INFO, "Navigated to Mailinator inbox for user: " + userName);
            driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + userName);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Navigated to Mailinator inbox for user: " + userName);
            System.out.println("Failed to navigate to mailinator " + e.getMessage());
        }
    }

    public static boolean clickConfirmationMail() {
        try {
            WebOperations.clickElement("subscriptionconfirmationmail");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Confirmation mail in mailinator");
            WebOperations.wait(5000);
            WebElement iframe = driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iframe);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click confirmation mail: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickMainMenu() {
        try {
            clickElement("mainmenu");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Main Menu");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click Main Menu: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickHomePageCloseButton() {
        try {

            WebOperations.clickElement("homepageclosebutton");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click HomePage close button: " + e.getMessage());
            return false;
        }
    }

    public static boolean  clickNewsLetterCloseButton() {
        try {
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            WebOperations.clickElement("newsletterclosebutton");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click News Letter close button: " + e.getMessage());
            return false;
        }
    }
    public static void navigateToYopmail() {
        try {
            WebOperations.addScreenshotToExtentReport();
            ExtentTestManager.getTest().log(Status.INFO, "Navigated to yopmail inbox for user: " );
            //driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + userName);
            driver.get("https://yopmail.com/en/");
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Navigated to yopmail inbox for user: "  );
            System.out.println("Failed to navigate to yopmail " + e.getMessage());
        }
    }
}
