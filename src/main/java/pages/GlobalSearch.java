package pages;

import com.aventstack.extentreports.Status;
import report.ExtentTestManager;
import util.WebConstants;
import util.WebOperations;

import static webdriver.WebDriverSetup.driver;


public class GlobalSearch {

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


    public static boolean clickAndTypeInSearchInput(String inputValue) {

        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking and typing " + inputValue + " into the search input box.");
            WebOperations.clickAndType("searchinputbox", inputValue);
            GlobalSearch.submitSearch();
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


    public static boolean clickHomePageCloseButton() {
        try {

            WebOperations.clickElement("homepageclosebutton");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click HomePage close button: " + e.getMessage());
            return false;
        }
    }

}
